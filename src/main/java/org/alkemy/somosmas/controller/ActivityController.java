package org.alkemy.somosmas.controller;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.ActivityDto;
import org.alkemy.somosmas.mapper.ActivityMapper;
import org.alkemy.somosmas.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;
    private final ActivityMapper activityMapper;

    @PostMapping
    public ActivityDto registerActivity(@Valid @RequestBody ActivityDto activityDto){
        return activityMapper.toDto(activityService.create(activityMapper.toModel(activityDto)));
    }

    @PutMapping("/{id}")
    public ActivityDto updateActivity(@Valid @RequestBody ActivityDto activityDto, @PathVariable Long id) throws Exception {

        return activityMapper.toDto((activityService.update(id, activityMapper.toModel(activityDto), activityDto.getFileType())));
    }
}
