package org.alkemy.somosmas.controller;


import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.ContactDto;
import org.alkemy.somosmas.mapper.ContactMapper;
import org.alkemy.somosmas.service.ContactService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;
    private final ContactMapper contactMapper;

    @PostMapping
    public ContactDto create(@Valid @RequestBody ContactDto contactDto) throws IOException {

        return contactMapper.toDto(contactService.create(contactMapper.toContact(contactDto)));
    }

    @GetMapping
    public List<ContactDto> getContactList(){
        return contactMapper.toDtoList(contactService.getContactList());
    }
}
