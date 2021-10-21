package org.alkemy.somosmas.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.settings.AwsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(AwsProperties.class)
@RequiredArgsConstructor
@Configuration
public class AWSS3Config {

    private final AwsProperties awsProperties;

    @Bean
    public AmazonS3 getAmazonS3Client() {
        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(awsProperties.getAccessKeyId(), awsProperties.getSecretAccessKey());
        //se obtiene el cliente AmazonS3 y se devuelve el objeto s3Client
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(awsProperties.getRegion()))
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }

}
