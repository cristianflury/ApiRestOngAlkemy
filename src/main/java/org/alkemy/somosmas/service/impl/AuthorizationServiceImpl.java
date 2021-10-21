package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.model.Slide;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.repository.SlideRepository;
import org.alkemy.somosmas.exception.CategoryNotFoundException;
import org.alkemy.somosmas.model.Category;
import org.alkemy.somosmas.model.News;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.repository.CategoryRepository;
import org.alkemy.somosmas.repository.NewsRepository;
import org.alkemy.somosmas.repository.UserRepository;
import org.alkemy.somosmas.security.UserDetailsImpl;
import org.alkemy.somosmas.service.AuthorizationService;
import org.alkemy.somosmas.util.SecurityUtils;

import java.util.NoSuchElementException;
import org.alkemy.somosmas.model.Activity;
import org.alkemy.somosmas.repository.ActivityRepository;


@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserRepository userRepository;
    private final SlideRepository slideRepository;
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final ActivityRepository activityRepository;


    @Override
    public boolean canAdministrateOrganization(Long organizationId) {
        UserDetailsImpl currentUser = SecurityUtils.getLoggedInUser();
        return userRepository.findByIdAndOrganizationId(currentUser.getId(), organizationId).isPresent()
                && loggedInUserHasAdminRole();
    }

    @Override
    public boolean isLoggedInUserOrAdmin(Long id) {
        UserDetailsImpl currentUser = SecurityUtils.getLoggedInUser();
        return currentUser.getId().equals(id) || loggedInUserHasAdminRole();
    }

    @Override
    public boolean canAdministrateNews(Long newsId) {

        if (newsRepository.existsById(newsId)) {
            News news = newsRepository.getById(newsId);
            User user = userRepository.getById(SecurityUtils.getLoggedInUser().getId());
            return user.getOrganization().getId().equals(news.getOrganization().getId());

        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean canAdministrateCategory(Long catId) {
        if (!categoryRepository.existsById(catId)) throw new CategoryNotFoundException();
        Category category = categoryRepository.getById(catId);
        User user = userRepository.getById(SecurityUtils.getLoggedInUser().getId());
        return user.getOrganization().getId().equals(category.getOrganization().getId()) &&
                loggedInUserHasAdminRole();
    }

    @Override
    public  boolean canAdministrateSlide(Long slideId){
        Slide slide =  slideRepository.findById(slideId).orElseThrow();
        User user =  userRepository.getById(SecurityUtils.getLoggedInUser().getId());

        return  slide.getOrganization().getId().equals(user.getOrganization().getId())
                &&  loggedInUserHasAdminRole();
    }
    @Override
    public boolean loggedInUserHasAdminRole() {
        return userRepository.getById(
                SecurityUtils.getLoggedInUser().getId()).getRole().getName()
                .equalsIgnoreCase("ROLE_ADMIN");
    }

    @Override
    public boolean canAdministrateActivity(Long activityId) {
        
        if (activityRepository.existsById(activityId)) {
            Activity activity = activityRepository.getById(activityId);
            User user = userRepository.getById(SecurityUtils.getLoggedInUser().getId());
            return user.getOrganization().getId().equals(activity.getOrganization().getId());
        } else {
            throw new NoSuchElementException();
        }
    }
    
}
