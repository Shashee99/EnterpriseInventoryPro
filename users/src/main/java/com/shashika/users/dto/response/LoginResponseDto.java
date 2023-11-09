package com.shashika.users.dto.response;

import lombok.Data;

@Data
public class LoginResponseDto {
    private Long id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;

}
