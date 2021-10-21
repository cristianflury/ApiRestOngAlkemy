package org.alkemy.somosmas.service;

import org.alkemy.somosmas.model.Category;
import org.alkemy.somosmas.service.bo.PageBO;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Category getById(Long id);

    PageBO<String> findAll(Pageable pageable, String url);

    Category create(Category category, String fileType) throws Exception;

    Category update(Long id, Category category, String fileType) throws Exception;

    void delete (Long id) throws Exception;
}