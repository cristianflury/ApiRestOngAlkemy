package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.exception.EmailUnavailableException;
import org.alkemy.somosmas.model.Role;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.repository.RoleRepository;
import org.alkemy.somosmas.repository.UserRepository;
import org.alkemy.somosmas.service.ImageHandlerService;
import org.alkemy.somosmas.service.MailBOBuilderService;
import org.alkemy.somosmas.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder encoder;
	private final ImageHandlerService imageHandlerService;
    private final MailBOBuilderService mailBOBuilderService;

    @Override
    @Transactional
    @PreAuthorize("@authorizationService.isLoggedInUserOrAdmin(#id)")
    public User update(Long id, User user, String fileType) {
        user.setId(id);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User create(User userBody, String fileType) throws Exception {

        checkEmailAvailability(userBody.getEmail());

        userBody.setPhoto(imageHandlerService.decodeImageAndCreateUrl(userBody.getPhoto(), fileType));
        userBody.setPassword(encoder.encode(userBody.getPassword()));
        Role roleUser = roleRepository.findByName("ROLE_USER");
        userBody.setRole(roleUser);
        if (userBody.getOrganization() == null)
            throw new Exception("No se indicó una organización válida para el usuario");
        User newUser = userRepository.save(userBody);
        mailBOBuilderService.sendWelcome(userBody);

        return newUser;

	}

    private void checkEmailAvailability(String email) throws EmailUnavailableException {

        if (userRepository.existsByEmail(email)) {

            throw new EmailUnavailableException();

        }

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	@Override
    @Transactional
    @PreAuthorize("@authorizationService.isLoggedInUserOrAdmin(#id)")
    public void delete(Long id) throws Exception {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findAll() {
        List<User> users = userRepository.findByDeletedFalse();
        return users;
    }

}
