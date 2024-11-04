package com.anonsousa.credit.controllers;

import com.anonsousa.credit.domain.dtos.user.CreateUserDto;
import com.anonsousa.credit.domain.model.UserEntity;
import com.anonsousa.credit.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid CreateUserDto user) {
        UserEntity userEntity = userService.createUser(user);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userEntity.getId())
                .toUri())
                .body(userEntity);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserEntity> updateUserById(@PathVariable Long userId,
                                                     @RequestBody @Valid CreateUserDto user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(userId, user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
