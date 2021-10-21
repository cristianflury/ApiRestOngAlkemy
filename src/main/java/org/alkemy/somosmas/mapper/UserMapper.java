package org.alkemy.somosmas.mapper;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.UserDto;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.repository.OrganizationRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final OrganizationRepository organizationRepository;

    public User toUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoto(userDto.getPhoto());
        user.setOrganization(organizationRepository.findByIdAndDeletedFalse(userDto.getOrganizationId()).orElseThrow());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoto(user.getPhoto());
        if (user.getOrganization() != null) userDto.setOrganizationId(user.getOrganization().getId());
        return userDto;
    }

}
