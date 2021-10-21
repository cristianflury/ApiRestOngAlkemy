package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.model.Category;
import org.alkemy.somosmas.repository.CategoryRepository;
import org.alkemy.somosmas.service.CategoryService;
import org.alkemy.somosmas.service.ImageHandlerService;
import org.alkemy.somosmas.service.OrganizationService;
import org.alkemy.somosmas.service.bo.PageBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ImageHandlerService imageHandlerService;
    private final OrganizationService organizationService;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category create(Category category, String fileType) throws Exception {

        category.setOrganization(organizationService.findTheUsersOrganization());
        category.setImage(imageHandlerService.decodeImageAndCreateUrl(category.getImage(), fileType));
        categoryRepository.save(category);
        return category;

    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PageBO<String> findAll(Pageable pageable, String url) {

        Page<Category> resultPage = categoryRepository.findAll(pageable);
        List<String> listNames = resultPage.stream().map(Category::getName).collect(Collectors.toList());
        Page<String> page = new PageImpl<>(listNames, pageable, resultPage.getTotalElements());

        return PageBO.build(page, url);
    }

    @Override
    @PreAuthorize("@authorizationService.canAdministrateCategory(#id)")
    public Category getById(Long id){

        return categoryRepository.getById(id);

    }

    @Override
    @Transactional
    @PreAuthorize("@authorizationService.canAdministrateCategory(#id)")
    public Category update(Long id, Category categoryUpdated, String fileType) throws Exception {

        Category categoryToUpdate = categoryRepository.getById(id);

        return categoryRepository.save(updateFields(categoryToUpdate, categoryUpdated, fileType));

    }

    private Category updateFields(Category categoryToUpdate, Category categoryUpdated, String fileType) throws Exception {

        if (categoryUpdated.getDescription() == null || categoryUpdated.getDescription().isBlank()) {

            categoryUpdated.setDescription(categoryToUpdate.getDescription());

        }

        if (categoryUpdated.getImage() == null || categoryUpdated.getImage().isBlank()) {

            categoryUpdated.setImage(categoryToUpdate.getImage());

        } else {

            categoryUpdated.setImage(imageHandlerService.decodeImageAndCreateUrl(categoryUpdated.getImage(), fileType));

            if (categoryToUpdate.getImage() != null) {
                imageHandlerService.deleteFileFromS3Bucket(categoryToUpdate.getImage());
            }

        }

        categoryUpdated.setOrganization(categoryToUpdate.getOrganization());
        categoryUpdated.setId(categoryToUpdate.getId());

        return categoryUpdated;
    }

    @Override
    @Transactional
    @PreAuthorize("@authorizationService.canAdministrateCategory(#id)")
    public void delete(Long id) throws Exception {
        Category category = categoryRepository.getById(id);
        categoryRepository.delete(category);
    }
}
