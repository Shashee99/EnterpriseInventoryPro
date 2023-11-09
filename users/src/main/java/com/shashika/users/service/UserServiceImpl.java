package com.shashika.users.service;

import com.shashika.users.dto.request.UserRequestDto;
import com.shashika.users.dto.request.UserUpdateRequestDto;
import com.shashika.users.dto.response.LoginResponseDto;
import com.shashika.users.dto.response.UserResponseDto;
import com.shashika.users.entity.User;
import com.shashika.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void addUser(UserRequestDto user) {

        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);

    }

    @Override
    public LoginResponseDto login(String email, String password) {
        LoginResponseDto res = new LoginResponseDto();
        Optional<User> existinguser = userRepository.findByEmail(email);
        if (existinguser.isPresent()){
            if (existinguser.get().isPasswordValid(password)){
                existinguser.get().setIslogged(true);

                userRepository.save(existinguser.get());

                res.setFirstname(existinguser.get().getFirstname());
                res.setLastname(existinguser.get().getLastname());
                res.setEmail(existinguser.get().getEmail());
                res.setId(existinguser.get().getId());
                return res;

            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    @Override
    public boolean logout(Long id) {
        Optional<User> user = userRepository.findById(id);

       if(user.isPresent()){
           user.get().setIslogged(false);
           userRepository.save(user.get());
           return true;
       }
       else {
           return false;
       }



    }

    @Override
    public boolean updateUser(UserUpdateRequestDto user) {

        Optional<User> existingUser = userRepository.findById(user.getId());
        if(!existingUser.isPresent()){
            return false;
        }
        existingUser.get().setFirstname(user.getFirstname());
        existingUser.get().setLastname(user.getLastname());
        existingUser.get().setEmail(user.getEmail());

        userRepository.save(existingUser.get());

        return true;
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if(!existingUser.isPresent()){
            return false;
        }
        else {
            userRepository.delete(existingUser.get());
            return  true;
        }
    }
}
