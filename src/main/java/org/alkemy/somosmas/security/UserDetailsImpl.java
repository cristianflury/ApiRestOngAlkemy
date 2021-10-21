package org.alkemy.somosmas.security;

import org.alkemy.somosmas.model.Role;
import org.alkemy.somosmas.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String photo;
    private final String password;
    private final Role role;
    private final SimpleGrantedAuthority authorities;

    public UserDetailsImpl(Long id, String email, String firstName, String lastName, String password, String photo, Role role,
                           SimpleGrantedAuthority authorities) {
        this.id = id;
        this.username = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.photo = photo;
        this.role = role;
        this.authorities = authorities;
    }

    public static UserDetails build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getPhoto(),
                user.getRole(),
                new SimpleGrantedAuthority(user.getRole().getName()));
    }

    public Long getId() {return id;}
    public String getUserName(){return username;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPhoto() {return photo;}
    public Role getRole() {return role;}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authorities);
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}
