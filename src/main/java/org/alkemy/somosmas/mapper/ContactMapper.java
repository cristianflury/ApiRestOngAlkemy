package org.alkemy.somosmas.mapper;


import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.ContactDto;
import org.alkemy.somosmas.model.Contact;
import org.alkemy.somosmas.repository.OrganizationRepository;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContactMapper {

    private  final OrganizationRepository organizationRepository;

    public ContactDto toDto(Contact contact){
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setName(contact.getName());
        contactDto.setEmail(contact.getEmail());
        contactDto.setPhone(contact.getPhone());
        contactDto.setMessage(contact.getMessage());
        contactDto.setOrganizationId(contact.getOrganization().getId());
        return contactDto;

    }

    public Contact toContact(ContactDto contactDto){
        Contact contact = new Contact();
        contact.setId(contactDto.getId());
        contact.setName(contactDto.getName());
        contact.setEmail(contactDto.getEmail());
        contact.setPhone(contactDto.getPhone());
        contact.setMessage(contactDto.getMessage());
        contact.setOrganization(organizationRepository.getById(contactDto.getOrganizationId()));
        return contact;
    }

    public List<ContactDto> toDtoList(List<Contact> contactList){
        return contactList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
