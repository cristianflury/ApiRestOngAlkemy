package org.alkemy.somosmas.mapper;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.NewsDto;
import org.alkemy.somosmas.model.News;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NewsMapper {


    public NewsDto toDto(News news){
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setContent(news.getContent());
        newsDto.setImage(news.getImage());
        newsDto.setName(news.getName());
        return newsDto;
    }

    public News toNews(NewsDto newsDto) {
        News news = new News();
        news.setName(newsDto.getName());
        news.setImage(newsDto.getImage());
        news.setContent(newsDto.getContent());
        return news;
    }
}
