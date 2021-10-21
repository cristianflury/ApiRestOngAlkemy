package org.alkemy.somosmas.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDto<T> {

    private List<T> content;
    private String next;
    private String previous;




}
