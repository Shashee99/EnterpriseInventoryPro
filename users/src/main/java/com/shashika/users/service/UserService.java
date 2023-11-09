package com.shashika.users.service;

import com.shashika.users.dto.request.UserRequestDto;
import com.shashika.users.dto.request.UserUpdateRequestDto;
import com.shashika.users.dto.response.LoginResponseDto;
import com.shashika.users.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void addUser(UserRequestDto user);
    boolean updateUser(UserUpdateRequestDto user);

    boolean deleteUser(Long id);

    LoginResponseDto login(String email, String password);

    boolean logout(Long id);



}
