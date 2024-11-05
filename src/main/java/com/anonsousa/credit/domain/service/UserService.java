package com.anonsousa.credit.domain.service;

import com.anonsousa.credit.domain.dtos.user.RequestUserDto;
import com.anonsousa.credit.domain.dtos.user.ResponseUserDto;
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
    public ResponseUserDto createUser(RequestUserDto user) {
        UserEntity userEntity = UserMapper.INSTANCE.userDtoToUserEntity(user);
        userEntity.setCreatedAt(LocalDateTime.now().withNano(0));
        userEntity.setStatus(Status.ACTIVE);

        userEntity = userRepository.save(userEntity);

        return UserMapper.INSTANCE.userEntityToUserDto(userEntity);
    }

    @Transactional(readOnly = true)
    public ResponseUserDto getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        return UserMapper.INSTANCE.userEntityToUserDto(userEntity);
    }

    @Transactional
    public ResponseUserDto updateUserById(Long userId, RequestUserDto user) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());

        return UserMapper.INSTANCE.userEntityToUserDto(userRepository.save(userEntity));
    }

    @Transactional
    public void deleteUserById(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        user.setStatus(Status.DISABLED);
        userRepository.save(user);
    }
}
