package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.model.Organization;
import org.alkemy.somosmas.repository.OrganizationRepository;
import org.alkemy.somosmas.repository.UserRepository;
import org.alkemy.somosmas.service.ImageHandlerService;
import org.alkemy.somosmas.service.OrganizationService;
import org.alkemy.somosmas.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ImageHandlerService imageHandlerService;
    private final UserRepository userRepository;

    @Override
    @PreAuthorize("@authorizationService.canAdministrateOrganization(#id)")
    public Organization updatePublicOrganizationInfo(Long id, Organization updatedOrganizationInfo, String fileType) throws Exception {
        Organization formerOrganization = organizationRepository.findById(id).orElseThrow();
        updatedOrganizationInfo.setImage(imageHandlerService.decodeImageAndCreateUrl(updatedOrganizationInfo.getImage()
                , fileType));
        Organization updatedOrganization = updatePublicFields(formerOrganization, updatedOrganizationInfo);
        organizationRepository.save(updatedOrganization);
        return updatedOrganization;
    }

    private Organization updatePublicFields(Organization formerOrganization, Organization updatedOrganizationInfo) {
        formerOrganization.setName(updatedOrganizationInfo.getName());
        formerOrganization.setPhone(updatedOrganizationInfo.getPhone());
        formerOrganization.setAddress(updatedOrganizationInfo.getAddress());
        formerOrganization.setImage(updatedOrganizationInfo.getImage());
        return formerOrganization;
    }

    @Override
    public Organization findById(Long id) {
        return organizationRepository.findByIdAndDeletedFalse(id).orElseThrow();
    }

    public Organization findTheUsersOrganization() {
        Long userId = SecurityUtils.getLoggedInUser().getId();
        return userRepository.findById(userId).orElseThrow().getOrganization();
    }
}