package org.alkemy.somosmas.mapper;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.CategoryDto;
import org.alkemy.somosmas.dto.UpdateCategoryDto;
import org.alkemy.somosmas.model.Category;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    public Category toModel(CategoryDto categoryDto) {

        Category category = new Category();

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImage(categoryDto.getImage());

        return category;
    }

    public Category toModel(UpdateCategoryDto updateCategoryDto) {

        Category category = new Category();

        category.setName(updateCategoryDto.getName());
        category.setDescription(updateCategoryDto.getDescription());
        category.setImage(updateCategoryDto.getImage());

        return category;
    }

    public CategoryDto toDto(Category category) {

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setImage(category.getImage());

        return categoryDto;
    }

}
