package org.alkemy.somosmas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
public class PublicOrganizationInfoDto implements Serializable {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    private String image;

    @NotBlank
    @Size(max = 255)
    private String address;

    @NotBlank
    @Size(max = 255)
    private String phone;
    @NotBlank
    private String fileType;

    private String facebookUrl;

    private String instagramUrl;

    private String twitterUrl;

    private String linkedinUrl;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<SlideDto> slides;

}
