package com.shashika.users.controller;

import com.shashika.users.dto.request.UserRequestDto;
import com.shashika.users.dto.request.UserUpdateRequestDto;
import com.shashika.users.dto.response.LoginResponseDto;
import com.shashika.users.dto.response.UserResponseDto;
import com.shashika.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private  UserService userService;


    @PostMapping()
    ResponseEntity<String> addUser(@RequestBody UserRequestDto user){
        if(user.getFirstname() == null){
            return ResponseEntity.badRequest().body("First name not provided");
        }
        else if(user.getLastname() == null){
            return ResponseEntity.badRequest().body("Last name not provided");
        }
        else if(user.getEmail() == null){
            return ResponseEntity.badRequest().body("Email not provided");
        }
        else if(user.getPassword() == null){
            return ResponseEntity.badRequest().body("Password not provided");
        }
        else{

            userService.addUser(user);

            return ResponseEntity.ok().body("User Created Successfully");
        }

    }

    @PutMapping()
    ResponseEntity<String> updateUser(@RequestBody UserUpdateRequestDto user){

        if(user.getFirstname() == null){
            return ResponseEntity.badRequest().body("First name not provided");
        }
        else if(user.getLastname() == null){
            return ResponseEntity.badRequest().body("Last name not provided");
        }
        else if(user.getEmail() == null){
            return ResponseEntity.badRequest().body("Email not provided");
        }
        else{

         boolean saveresult = userService.updateUser(user);
            if (!saveresult) {
                return ResponseEntity.badRequest().body("user not found");
            }
            else {
                return ResponseEntity.ok().body("User updated Successfully");
            }

        }

    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id){

        boolean result = userService.deleteUser(id);
        if (result){
            return ResponseEntity.ok("User deleted succesfully");
        }
        else {
            return ResponseEntity.badRequest().body("User deleted succesfully");
        }


    }


    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody UserRequestDto user){
        LoginResponseDto res = new LoginResponseDto();
        if(user.getEmail() == null){

            res.setEmail("email is not provided");
            res.setPassword(user.getPassword());


            return ResponseEntity.badRequest().body(res);
        }
        else if(user.getPassword() == null){
            res.setEmail(user.getEmail());
            res.setPassword("password is not provided");
            return ResponseEntity.badRequest().body(res);
        }
        else{

         res = userService.login(
                 user.getEmail(), user.getPassword()
         );
         if (res != null){
             return ResponseEntity.ok().body(res);
         }
         else {
             res = new LoginResponseDto();
             res.setEmail("Not found the user");
             res.setPassword(" ");
             return ResponseEntity.badRequest().body(res);
         }


        }

    }
    @GetMapping("/logout/{id}")
    ResponseEntity<String> logout(@PathVariable Long id){

        userService.logout(id);
        return ResponseEntity.ok().body("Log out succesfully");
    }









}
