package org.alkemy.somosmas.controller;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.CategoryDto;
import org.alkemy.somosmas.dto.PageDto;
import org.alkemy.somosmas.dto.UpdateCategoryDto;
import org.alkemy.somosmas.mapper.CategoryMapper;
import org.alkemy.somosmas.mapper.PageMapper;
import org.alkemy.somosmas.model.Category;
import org.alkemy.somosmas.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.function.Function;


@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final PageMapper pageMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getDetails(@PathVariable("id") Long id){
        return new ResponseEntity<>(categoryMapper.toDto(categoryService.getById(id)), HttpStatus.FOUND);
    }

    @GetMapping()
	public PageDto<String> getCategories(@PageableDefault(size=10) Pageable pageable, HttpServletRequest request) {

		return pageMapper.toDto(categoryService.findAll(pageable, request.getRequestURL().toString()), Function.identity());

	}

    @PutMapping("/{id}")
    public CategoryDto update(@Valid @RequestBody UpdateCategoryDto updateCategoryDto, @PathVariable("id") Long id) throws Exception {

        return categoryMapper
                .toDto(categoryService
                        .update(id, categoryMapper.toModel(updateCategoryDto), updateCategoryDto.getFileType()));

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDto categoryDto) throws Exception {

        categoryMapper.toDto(categoryService.create(categoryMapper.toModel(categoryDto) , categoryDto.getFileType()));

        return new ResponseEntity<>( HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) throws Exception{
        categoryService.delete(id);
    }
}
