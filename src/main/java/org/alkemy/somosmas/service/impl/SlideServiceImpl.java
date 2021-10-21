package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.model.Organization;
import org.alkemy.somosmas.model.Slide;
import org.alkemy.somosmas.repository.SlideRepository;
import org.alkemy.somosmas.repository.UserRepository;
import org.alkemy.somosmas.service.ImageHandlerService;
import org.alkemy.somosmas.service.OrganizationService;
import org.alkemy.somosmas.service.SlideService;
import org.alkemy.somosmas.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

private final SlideRepository slideRepository;
    private final UserRepository userRepository;
    private final ImageHandlerService imageHandlerService;
    private final OrganizationService organizationService;

    @PreAuthorize("@authorizationService.loggedInUserHasAdminRole()")
    @Override
    public Slide create(Slide slide, String fileType) throws Exception {
		Organization org = organizationService.findTheUsersOrganization();

		slide.setOrganization(org);
		slide.setImageUrl(imageHandlerService.decodeImageAndCreateUrl(slide.getImageUrl(), fileType));
		if (slide.getOrder() == null) {
			slide.setOrder(findLastSlideOrder(org.getId()));
		}
		slideRepository.save(slide);
		return slide;
	}

    @Override
    @Transactional
    @PreAuthorize("@authorizationService.canAdministrateSlide(#id)")
    public Slide update(Long id, Slide newSlide, String fileType) throws Exception {
        Slide formerSlide = slideRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Slide no encontrada"));
        Slide updatedSlide = updateFields(formerSlide, newSlide, fileType);
        slideRepository.save(updatedSlide);
        return updatedSlide;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Slide> findAll() {
        Organization organization = organizationService.findTheUsersOrganization();
        List<Slide> slides = slideRepository.findByOrganizationId(organization.getId());
        return slides;
    }

    @PreAuthorize("@authorizationService.canAccessToSlide(#id)")
    public Slide findById(Long id) {
        return slideRepository.findById(id).orElseThrow();
    }

    private Slide updateFields(Slide formerSlide, Slide newSlide, String fileType) throws Exception {
        if (newSlide.getText() == null) newSlide.setText(formerSlide.getText());
        if (newSlide.getImageUrl() == null) newSlide.setImageUrl(formerSlide.getImageUrl());
        formerSlide.setText(newSlide.getText());
        formerSlide.setImageUrl(imageHandlerService.decodeImageAndCreateUrl(newSlide.getImageUrl(), fileType));
        if (newSlide.getOrder() == null) newSlide.setOrder(findLastSlideOrder(formerSlide.getOrganization().getId()));
        else formerSlide.setOrder(newSlide.getOrder());
        return formerSlide;
    }

    private Integer findLastSlideOrder(Long organizationId) {
        Optional<Slide> slideList = slideRepository.findTop1ByOrganizationIdOrderByOrderDesc(organizationId);
        return slideList.map(slide -> slide.getOrder() + 1).orElse(1);
    }
    @PreAuthorize("@authorizationService.canAdministrateSlide(#id)")
    @Override
    public void delete(Long id) throws Exception {
        Slide slide = slideRepository.getById(id);
        imageHandlerService.deleteFileFromS3Bucket(slide.getImageUrl());
        slideRepository.delete(slide);
    }



}