package org.alkemy.somosmas.mapper;

import org.alkemy.somosmas.dto.PageDto;
import org.alkemy.somosmas.service.bo.PageBO;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class PageMapper {

    public <T, R> PageDto<R> toDto(PageBO<T> pageBo, Function<T, R> pageElementMapper) {
        PageDto<R> pageDto = new PageDto<>();
        pageDto.setContent(pageBo.getContent().stream().map(pageElementMapper).collect(Collectors.toList()));
        pageDto.setNext(pageBo.getNext());
        pageDto.setPrevious(pageBo.getPrevious());
        return pageDto;
    }
}
