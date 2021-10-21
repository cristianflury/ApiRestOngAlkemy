package org.alkemy.somosmas.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Slide implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String imageUrl;

    private Integer order;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Organization organization;
}
