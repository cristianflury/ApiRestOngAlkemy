package org.alkemy.somosmas.mapper;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.PublicOrganizationInfoDto;
import org.alkemy.somosmas.model.Organization;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PublicOrganizationMapper {

    private final SlideMapper SlideMapper;

    public Organization toEntity(PublicOrganizationInfoDto dto) {
        Organization organization = new Organization();
        organization.setPhone(dto.getPhone());
        organization.setName(dto.getName());
        organization.setImage(dto.getImage());
        organization.setAddress(dto.getAddress());
        organization.setFacebookUrl(dto.getFacebookUrl());
        organization.setInstagramUrl(dto.getInstagramUrl());
        organization.setTwitterUrl(dto.getTwitterUrl());
        organization.setLinkedinUrl(dto.getLinkedinUrl());
    return organization;
    }

    public PublicOrganizationInfoDto toDto(Organization organization) {
        PublicOrganizationInfoDto dto = new PublicOrganizationInfoDto();
        dto.setPhone(organization.getPhone());
        dto.setName(organization.getName());
        dto.setImage(organization.getImage());
        dto.setAddress(organization.getAddress());
        dto.setFacebookUrl(organization.getFacebookUrl());
        dto.setInstagramUrl(organization.getInstagramUrl());
        dto.setTwitterUrl(organization.getTwitterUrl());
        dto.setLinkedinUrl(organization.getLinkedinUrl());
        dto.setSlides(SlideMapper.slideSetToDtoList(organization.getSlides()));
        return dto;
    }
}
