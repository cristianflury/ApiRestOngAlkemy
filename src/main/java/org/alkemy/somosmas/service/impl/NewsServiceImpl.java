package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.NewsDto;
import org.alkemy.somosmas.exception.NewsNotFoundException;
import org.alkemy.somosmas.mapper.NewsMapper;
import org.alkemy.somosmas.model.News;
import org.alkemy.somosmas.model.Organization;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.repository.CategoryRepository;
import org.alkemy.somosmas.repository.NewsRepository;
import org.alkemy.somosmas.repository.UserRepository;
import org.alkemy.somosmas.service.ImageHandlerService;
import org.alkemy.somosmas.service.NewsService;
import org.alkemy.somosmas.service.OrganizationService;
import org.alkemy.somosmas.service.bo.PageBO;
import org.alkemy.somosmas.util.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ImageHandlerService imageHandlerService;
    private  final OrganizationService organizationService;
    private  final NewsMapper newsMapper;



    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public News create(News news) {
        news.setCategory(categoryRepository.findByName("CATEGORY_NEWS"));
        User user = userRepository.findByEmail(SecurityUtils.getLoggedInUser().getUsername()).get();
        news.setOrganization(user.getOrganization());
        return newsRepository.save(news);

    }


    @Override
    @Transactional
    @PreAuthorize("@authorizationService.canAdministrateNews(#id)")
    public News update(Long id, News news, String fileType) throws Exception {

            news.setId(id);
            news.setImage(imageHandlerService.decodeImageAndCreateUrl(news.getImage(), fileType));
            news.setCategory(categoryRepository.findByName("CATEGORY_NEWS"));
            User user = userRepository.findByEmail(SecurityUtils.getLoggedInUser().getUsername()).get();
            news.setOrganization(user.getOrganization());
            return newsRepository.save(news);

    }

    @Override
    @Transactional
    @PreAuthorize("@authorizationService.canAdministrateNews(#id)")
    public void delete(Long id) {
        if(!newsRepository.existsById(id) || newsRepository.findByIdAndDeletedTrue(id).isPresent()){
            throw new NewsNotFoundException("The news with id " +id + " was not found");
        }
        newsRepository.deleteById(id);
    }
    public News findById(Long id) {
        return newsRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    @PreAuthorize("@authorizationService.isLoggedInUserOrAdmin(#id)")
    public PageBO<News> findAll(Pageable pageable, String url) {
        Organization organization = organizationService.findTheUsersOrganization();
        Page<News> pageResult =newsRepository.findAllByOrganizationId( pageable ,organization.getId());
        List<News> newsList = pageResult.getContent();
        Page<News> page = new PageImpl(newsList, pageable,  pageResult.getTotalElements());
        return PageBO.build(page, url);
    }
}
