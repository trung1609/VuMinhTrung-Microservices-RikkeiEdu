package com.trung.userservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormLogin {
    private String email;
    private String password;
}
