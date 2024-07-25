package com.java.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String about;
}
