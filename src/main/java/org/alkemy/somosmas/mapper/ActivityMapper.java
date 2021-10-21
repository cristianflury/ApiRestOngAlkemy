package org.alkemy.somosmas.mapper;


import org.alkemy.somosmas.dto.ActivityDto;
import org.alkemy.somosmas.model.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public ActivityDto toDto(Activity activity){
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setContent(activity.getContent());
        activityDto.setImage(activity.getImage());
        activityDto.setName(activity.getName());
        return activityDto;
    }

    public Activity toModel(ActivityDto activityDto){
        Activity activity = new Activity();
        activity.setContent(activityDto.getContent());
        activity.setImage(activityDto.getImage());
        activity.setName(activityDto.getName());
        return activity;
    }
}
