package com.anonsousa.credit.domain.dtos.user;

import com.anonsousa.credit.domain.enums.user.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDto {
        private Long id;
        private String name;
        private String email;
        private LocalDateTime createdAt;
        private Status status;
}

