package com.anonsousa.credit.domain.service;

import com.anonsousa.credit.domain.dtos.user.CreateUserDto;
import com.anonsousa.credit.domain.enums.user.Status;
import com.anonsousa.credit.domain.mappers.UserMapper;
import com.anonsousa.credit.domain.model.UserEntity;
import com.anonsousa.credit.domain.repository.UserRepository;
import com.anonsousa.credit.infra.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public UserEntity createUser(CreateUserDto user) {
        UserEntity userEntity = UserMapper.INSTANCE.userDtoToUserEntity(user);
        userEntity.setCreatedAt(LocalDateTime.now().withNano(0));
        userEntity.setStatus(Status.ACTIVE);

        return userRepository.save(userEntity);
    }

    @Transactional(readOnly = true)
    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    @Transactional
    public UserEntity updateUserById(Long userId, CreateUserDto user) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());

        return userRepository.save(userEntity);
    }

    @Transactional
    public void deleteUserById(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        user.setStatus(Status.DISABLED);
        userRepository.save(user);
    }
}
