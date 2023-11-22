package com.shashika.users.controller;

import com.shashika.users.dto.request.UserRequestDto;
import com.shashika.users.dto.request.UserUpdateRequestDto;
import com.shashika.users.dto.response.LoginResponseDto;
import com.shashika.users.dto.response.ResponseDto;
import com.shashika.users.dto.response.UserResponseDto;
import com.shashika.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private  UserService userService;


    @PostMapping()
    ResponseEntity<?> addUser(@RequestBody UserRequestDto user){
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

            return ResponseEntity.ok().body(new ResponseDto("User created!"));
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
            return ResponseEntity.badRequest().body( "User deleted succesfully");
        }


    }


    @PatchMapping()
    public ResponseEntity<LoginResponseDto> setState(
            @RequestParam(required = false) String operation,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) Long userid

            ) {

        if(Objects.equals(operation, "LOGIN")){
            LoginResponseDto res = new LoginResponseDto();
            if(email == null){

                res.setEmail("email is not provided");
                res.setPassword(password);


                return ResponseEntity.badRequest().body(res);
            }
            else if(password == null){
                res.setEmail(email);
                res.setPassword("password is not provided");
                return ResponseEntity.badRequest().body(res);
            }
            else{

                res = userService.login(
                        email, password
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

        } else if (Objects.equals(operation, "LOGOUT")) {
            userService.logout(userid);
            LoginResponseDto res = new LoginResponseDto();
            res.setMessage("Logout succesfully");
            return ResponseEntity.ok().body(res);
        }
        else {
            LoginResponseDto res = new LoginResponseDto();
            res.setMessage("User not found");
            return ResponseEntity.ok().body(res);
        }

    }







}
