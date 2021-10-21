package org.alkemy.somosmas.settings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws.s3")
@Getter
@Setter
public class AwsProperties {

    private String bucket;
    private String accessKeyId;
    private String secretAccessKey;
    private String region;
}
