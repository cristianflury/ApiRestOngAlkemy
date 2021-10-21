package org.alkemy.somosmas.service;


public interface AuthorizationService {
    boolean isLoggedInUserOrAdmin(Long id);
    boolean canAdministrateOrganization(Long organizationId);
    boolean canAdministrateNews(Long newsId);
    boolean canAdministrateSlide(Long slideId);
    boolean canAdministrateCategory(Long catId);
    boolean canAdministrateActivity(Long activityId);
    boolean loggedInUserHasAdminRole();
}
