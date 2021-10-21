package org.alkemy.somosmas.service;

public interface ImageHandlerService {
    String decodeImageAndCreateUrl(String imageBase64, String fileType) throws Exception;

    void deleteFileFromS3Bucket(String fileUrl) throws Exception;
}
