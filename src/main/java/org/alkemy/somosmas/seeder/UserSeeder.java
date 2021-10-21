package org.alkemy.somosmas.seeder;

import lombok.AllArgsConstructor;
import org.alkemy.somosmas.model.Organization;
import org.alkemy.somosmas.model.Role;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.repository.OrganizationRepository;
import org.alkemy.somosmas.repository.RoleRepository;
import org.alkemy.somosmas.repository.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Component
public class UserSeeder {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final OrganizationRepository organizationRepository;

    private final PasswordEncoder passwordEncoder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUserData();
    }

    public void seedUserData() {
        List<String> adminUserList = adminUserList();
        Role roleAdmin = (roleRepository.existsByName("ROLE_ADMIN"))
                ? roleRepository.findByName("ROLE_ADMIN") : createRole("ROLE_ADMIN");
        loadUserData(adminUserList, roleAdmin,organizationsAdminList());

        List<String> regularUserList = regularUserList();
        Role roleUser = (roleRepository.existsByName("ROLE_USER"))
                ? roleRepository.findByName("ROLE_USER") : createRole("ROLE_USER");
        loadUserData(regularUserList, roleUser,organizationsUserList());
    }

    private void loadUserData(List<String> usersList, Role role,List<String> organizationsList) {
        for (String email : usersList) {
            Optional<User> searchedUser = userRepository.findByEmail(email);
            if (searchedUser.isEmpty()) {
                createUser(email, role,organizationsList);
            }
        }
    }

    private List<String> adminUserList() {
        return List.of("noah_smith@gmail.com",
                "jacob_williams@gmail.com",
                "emma_johnson@gmail.com",
                "ava_brown@gmail.com",
                "emily_jones@gmail.com",
                "james_garcia@gmail.com",
                "liam_miller@gmail.com",
                "daniel_davis@gmail.com",
                "elizabeth_rodriguez@gmail.com",
                "sofia_martinez@gmail.com");
    }

    private List<String> regularUserList() {
        return List.of("elijah_lopez@gmail.com",
                "aiden_gonzalez@gmail.com",
                "logan_wilson@gmail.com",
                "grace_anderson@gmail.com",
                "natalie_taylor@gmail.com",
                "victoria_moore@gmail.com",
                "andrew_jackson@gmail.com",
                "gabriel_martin@gmail.com",
                "homero_thompson@gmail.com",
                "felipe_hernandez@gmail.com");
    }

    private String getOrganization(List<String> list){
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(5);
        return list.get(index);

    }

    private List<String> organizationsAdminList(){
        return List.of("World Vision",
                "Medicos sin fronteras",
                "Greenpeace",
                "Awid",
                "Amnistía internacional");
    }

    private List<String> organizationsUserList(){
        return List.of("Google",
                "Samsung",
                "Mars",
                "ORAL B",
                "Telefonica");
    }

    private void createUser(String email, Role role, List<String> organizationsList) {
        String[] partsOfEmail = email.split("_");
        String name = partsOfEmail[0];
        String[] partsOfLastname = partsOfEmail[1].split("@");
        String lastname = partsOfLastname[0];
        String organizationName = getOrganization(organizationsList);

        User user = new User();
        user.setEmail(email);
        user.setFirstName(name);
        user.setLastName(lastname);
        user.setPassword(passwordEncoder.encode(name + "123"));
        user.setPhoto("src//img/" + name + ".jpg");
        user.setDeleted(false);
        user.setRole(role);
        user.setOrganization(organizationRepository.existsByName(organizationName)
                ? organizationRepository.findByName(organizationName) : createOrganization(organizationName));

        userRepository.save(user);

    }

    private Organization createOrganization(String organizationName){
        Organization organization = new Organization();

        organization.setName(organizationName);
        organization.setDeleted(false);
        organization.setFacebookUrl("facebook.com");
        organization.setTwitterUrl("twitter.com");
        organization.setInstagramUrl("instagram.com");
        organization.setLinkedinUrl("linkedin.com");
        organizationRepository.save(organization);
        return organization;
    }

    private Role createRole(String typeRole) {
        String[] partsOfTypeRole = typeRole.split("_");
        String userRole = partsOfTypeRole[1];

        Role role = new Role();
        role.setName(typeRole);
        role.setDescription("This role is for the use of users of the type: " + userRole);
        roleRepository.save(role);

        return role;
    }

}
