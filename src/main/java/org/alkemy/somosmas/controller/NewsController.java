package org.alkemy.somosmas.controller;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.NewsDto;
import org.alkemy.somosmas.dto.PageDto;
import org.alkemy.somosmas.mapper.NewsMapper;
import org.alkemy.somosmas.mapper.PageMapper;
import org.alkemy.somosmas.model.News;
import org.alkemy.somosmas.service.NewsService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsMapper newsMapper;
    private final NewsService newsService;
    private  final PageMapper pageMapper;

    @PostMapping
    public ResponseEntity<NewsDto> registerNews(@Valid @RequestBody NewsDto newsBody){
        return new ResponseEntity<>(newsMapper.toDto(newsService.create(newsMapper.toNews(newsBody))), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idNews}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable("idNews")Long id){
        newsService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> update(@PathVariable("id") Long id, @Valid @RequestBody NewsDto newsBody) throws Exception{
            NewsDto newsDto = newsMapper.toDto(newsService.update(id, newsMapper.toNews(newsBody), newsBody.getFileType()));

        return new ResponseEntity<>(newsDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> findById(@PathVariable Long id) {

    return new ResponseEntity(newsMapper.toDto(newsService.findById(id)), HttpStatus.OK);
    }

    @GetMapping
    public PageDto<NewsDto> getAll(@PageableDefault(size = 10) Pageable pageable, HttpServletRequest request){

        return pageMapper.toDto(newsService.findAll(pageable , request.getRequestURL().toString()), newsMapper ::toDto);
    }
}
