package org.alkemy.somosmas.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

import org.alkemy.somosmas.model.Organization;

@Getter
@Setter
public class SlideDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String text;
    private String imageBase64;
    private Integer order;
    private Long organizationId;
}
