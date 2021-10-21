package org.alkemy.somosmas.service;

import org.alkemy.somosmas.dto.NewsDto;
import org.alkemy.somosmas.model.News;
import org.alkemy.somosmas.service.bo.PageBO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {



    News update(Long id, News news, String fileType) throws Exception;

    News create(News news);

    void delete(Long id);

    News findById(Long id);

    PageBO<News> findAll(Pageable pageable, String url);

}
