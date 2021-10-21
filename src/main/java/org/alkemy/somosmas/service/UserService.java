package org.alkemy.somosmas.service;

import org.alkemy.somosmas.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User create(User userBody, String fileType) throws Exception;

    Optional<User> findByEmail(String email);

    User update(Long id, User user, String fileType) throws Exception;

    void delete(Long id) throws Exception;

}

