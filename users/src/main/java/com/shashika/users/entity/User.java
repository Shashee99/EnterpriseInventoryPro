package com.shashika.users.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column( unique = true)
    private String email;
    private String firstname;
    private String lastname;
    private String password;

    private boolean islogged;

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isPasswordValid(String enteredPassword) {
        return BCrypt.checkpw(enteredPassword, this.password);
    }


}
