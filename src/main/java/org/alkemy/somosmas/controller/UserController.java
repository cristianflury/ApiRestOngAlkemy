package org.alkemy.somosmas.controller;

import org.alkemy.somosmas.dto.UserDto;
import org.alkemy.somosmas.mapper.UserMapper;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") Long id, @Valid @RequestBody UserDto userBody) throws Exception {
        UserDto userDto = userMapper.toDto(userService.update(id, userMapper.toUser(userBody), userBody.getFileType()));

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        userService.delete(id);

    }


	@GetMapping()
	public ResponseEntity<User> getUsers() {

		return new ResponseEntity(userService.findAll()
				.stream()
				.map(userMapper::toDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}
}


