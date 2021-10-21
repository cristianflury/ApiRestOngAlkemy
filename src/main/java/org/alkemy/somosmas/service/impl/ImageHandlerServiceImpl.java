package org.alkemy.somosmas.service.impl;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.exception.InvalidFileTypeException;
import org.alkemy.somosmas.exception.NoSuchFileException;
import org.alkemy.somosmas.service.ImageHandlerService;
import org.alkemy.somosmas.service.bo.Base64DecodedMultipartFile;
import org.alkemy.somosmas.settings.AwsProperties;
import org.alkemy.somosmas.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;


@EnableConfigurationProperties(AwsProperties.class)
@RequiredArgsConstructor
@Service
public class ImageHandlerServiceImpl implements ImageHandlerService {

    private static final Logger logger = LoggerFactory.getLogger(ImageHandlerServiceImpl.class);
    private static final String HTTPS = "https://";
    private static final String S_3_AMAZONAWS_COM = ".s3.amazonaws.com/";

    private final AmazonS3 s3Client;
    private final AwsProperties awsProperties;

    @Override
    public String decodeImageAndCreateUrl(String imageBase64, String fileType) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(imageBase64);
        String fileName = new StringBuilder().append("image_user").append(UUID.randomUUID()).toString();
        if (!fileType.startsWith(".")) fileType = new StringBuilder().append(".").append(fileType).toString();
        Base64DecodedMultipartFile multipartFile = new Base64DecodedMultipartFile(decodedBytes, fileName + fileType);
        return uploadFile(multipartFile, fileType);
    }

    private String uploadFile(final MultipartFile multipartFile, String fileType) throws Exception {
        List<String> validExtensions = Arrays.asList(".jpeg", ".jpg", ".png");
        String fileName = null;
        if (!validExtensions.contains(fileType)) {
            throw new InvalidFileTypeException();
        } else {
            logger.info("Carga de imagen en proceso.");
            try {
                final File file = convertMultipartFileToFile(multipartFile);
                fileName = uploadFileToS3Bucket(awsProperties.getBucket(), file);
                logger.info("Carga completa.");
                file.delete();
            } catch (Exception e) {
                logger.warn("Carga no completada.");
                logger.error("Error al cargar la imagen.", e);
                throw new IOException("Error al cargar la imagen");
            }
        }
        return new StringBuilder().append(HTTPS).append(awsProperties.getBucket()).append(S_3_AMAZONAWS_COM).append(fileName).toString();
    }

    private File convertMultipartFileToFile(final MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException e) {
            logger.error("Error al convertir multipart file a file.", e.getMessage());
        }
        return file;
    }

    private String uploadFileToS3Bucket(final String bucketName, final File file) {
        final String uniqueFileName = new StringBuilder().append(LocalDateTime.now()).append("_").append(file.getName().replace(" ", "_")).toString();
        logger.info("Cargando archivo con nombre= " + uniqueFileName);
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
        s3Client.putObject(putObjectRequest);
        return uniqueFileName;
    }

    //método para eliminar imágenes ya cargadas en aws
    @Override
    public void deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        if (!s3Client.doesObjectExist(awsProperties.getBucket(), fileName)) throw new NoSuchFileException();
        else {
            logger.info("Eliminación de imagen en proceso.");
            try {
                s3Client.deleteObject(new DeleteObjectRequest(awsProperties.getBucket(), fileName));
                logger.info("Imagen eliminada");
            } catch (Exception e) {
                logger.warn("Imagen no eliminada");
                logger.error("Error al eliminar la imagen.", e);
                throw new SdkClientException("Error al eliminar la imagen");
            }
        }
    }

}
