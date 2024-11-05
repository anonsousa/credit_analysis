package com.anonsousa.credit.controllers;

import com.anonsousa.credit.domain.dtos.user.RequestUserDto;
import com.anonsousa.credit.domain.dtos.user.ResponseUserDto;
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
    public ResponseEntity<ResponseUserDto> createUser(@RequestBody @Valid RequestUserDto user) {
        ResponseUserDto userDto = userService.createUser(user);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDto.getId())
                .toUri())
                .body(userDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseUserDto> updateUserById(@PathVariable Long userId,
                                                     @RequestBody @Valid RequestUserDto user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(userId, user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
