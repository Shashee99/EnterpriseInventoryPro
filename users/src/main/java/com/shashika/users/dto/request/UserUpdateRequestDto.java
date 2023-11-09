package com.shashika.users.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;

}
