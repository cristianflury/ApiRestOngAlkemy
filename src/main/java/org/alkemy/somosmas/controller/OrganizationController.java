package org.alkemy.somosmas.controller;

import lombok.AllArgsConstructor;
import org.alkemy.somosmas.dto.PublicOrganizationInfoDto;
import org.alkemy.somosmas.mapper.PublicOrganizationMapper;
import org.alkemy.somosmas.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/organization")
@Validated
public class OrganizationController {
    private final OrganizationService organizationService;
    private final PublicOrganizationMapper publicOrganizationMapper;

    @PutMapping(path = "/public/{id}")
    public ResponseEntity<PublicOrganizationInfoDto> updateOrganization(
            @Valid @RequestBody PublicOrganizationInfoDto organizationBody,
            @PathVariable Long id)
            throws NoSuchElementException, Exception {

        PublicOrganizationInfoDto publicOrganizationInfoDto = publicOrganizationMapper
                .toDto(organizationService.updatePublicOrganizationInfo(
                        id, publicOrganizationMapper.toEntity(organizationBody), organizationBody.getFileType()));


        return ResponseEntity.ok(publicOrganizationInfoDto);
    }

    @GetMapping("/public/{id}")
    public PublicOrganizationInfoDto getOrganization(@PathVariable("id") Long id) {
        return publicOrganizationMapper.toDto(organizationService.findById(id));
    }
}