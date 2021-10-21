package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.model.Activity;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.repository.ActivityRepository;
import org.alkemy.somosmas.repository.UserRepository;
import org.alkemy.somosmas.service.ActivityService;
import org.alkemy.somosmas.service.ImageHandlerService;
import org.alkemy.somosmas.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final ImageHandlerService imageHandlerService;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Activity create(Activity activity) {
        User user = userRepository.findByEmail(SecurityUtils.getLoggedInUser().getUsername()).get();
        activity.setOrganization(user.getOrganization());
        return activityRepository.save(activity);
    }

    @Override
    @Transactional
    @PreAuthorize("@authorizationService.canAdministrateActivity(#id)")
    public Activity update(Long id, Activity newActivity, String fileType) throws Exception {

        Activity activityToUpdate = activityRepository.getById(id);

        return activityRepository.save(updateFields(activityToUpdate, newActivity, fileType));
    }

    private Activity updateFields(Activity activityToUpdate, Activity newActivity, String fileType) throws Exception {

        if (newActivity.getName() != null) {
            activityToUpdate.setName(newActivity.getName());
        }

        if (newActivity.getContent() != null) {
            activityToUpdate.setContent(newActivity.getContent());
        }

        if (newActivity.getImage() != null) {
            activityToUpdate.setImage(imageHandlerService.decodeImageAndCreateUrl(newActivity.getImage(), fileType));
        }

        return activityToUpdate;
    }
}
