package com.trung.userservice.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormRegister {
    private String password;
    @Email
    private String email;
    private String fullName;
    private String phone;
    private List<String> roles;
}
