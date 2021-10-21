package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.exception.NoContactsFoundException;
import org.alkemy.somosmas.model.Contact;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.repository.ContactRepository;
import org.alkemy.somosmas.repository.UserRepository;
import org.alkemy.somosmas.service.ContactService;
import org.alkemy.somosmas.service.MailBOBuilderService;
import org.alkemy.somosmas.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final MailBOBuilderService mailBOBuilderService;
    private final UserRepository userRepository;

    @Override
    public Contact create(Contact contact) throws IOException {
        contactRepository.save(contact);
        mailBOBuilderService.sendContactResponse(contact);
        return contact;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Contact> getContactList(){
        User user = userRepository.findByEmail(SecurityUtils.getLoggedInUser().getUsername()).get();
        return contactRepository.findAll().stream()
                .filter(c -> c.getOrganization().getId().equals(user.getOrganization().getId()))
                .collect(Collectors.toList());
    }
}
