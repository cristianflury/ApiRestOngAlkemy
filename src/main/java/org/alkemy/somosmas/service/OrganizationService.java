package org.alkemy.somosmas.service;

import org.alkemy.somosmas.model.Organization;

public interface OrganizationService {

    Organization updatePublicOrganizationInfo(Long id, Organization updatedOrganization, String fileType) throws Exception;
    Organization findById(Long id);
    Organization findTheUsersOrganization();

}
