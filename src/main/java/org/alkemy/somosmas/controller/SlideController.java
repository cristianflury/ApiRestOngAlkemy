package org.alkemy.somosmas.controller;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.SlideDto;
import org.alkemy.somosmas.dto.SlideRequestDto;
import org.alkemy.somosmas.dto.SlideResponseDto;
import org.alkemy.somosmas.mapper.SlideMapper;
import org.alkemy.somosmas.model.Slide;
import org.alkemy.somosmas.service.SlideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
public class SlideController {

    private final SlideService slideService;
    private final SlideMapper slideMapper;

    @PutMapping("/{id}")
    public ResponseEntity<SlideResponseDto> updateSlide(@Valid @RequestBody SlideRequestDto slideBody, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(slideMapper.toDto(slideService.update(id,slideMapper.toEntity(slideBody), slideBody.getFileType())));
    }

    @PostMapping(path = "/")
    public ResponseEntity<SlideResponseDto> createSlide(@Valid @RequestBody SlideRequestDto slideBody) throws Exception {
        return new ResponseEntity<>(
                slideMapper.toDto(
                        slideService.create(slideMapper.toEntity(slideBody), slideBody.getFileType())),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlideDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(slideMapper.slideToDto(slideService.findById(id)));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSlide(@PathVariable Long id) throws Exception {
        slideService.delete(id);
    }

    @GetMapping
    public List<SlideDto> getSlides () {
        return slideService.findAll()
                .stream()
                .map(slideMapper::slideToDto)
                .collect(Collectors.toList());
    }

}
