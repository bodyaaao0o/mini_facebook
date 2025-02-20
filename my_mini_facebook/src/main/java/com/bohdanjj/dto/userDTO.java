package com.bohdanjj.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class userDTO {
    @NotNull(message = "ID cannot be null")
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Email must be valid")
    private String email;

    public @NotNull(message = "ID cannot be null") Integer getId() {
        return id;
    }

    public void setId(@NotNull(message = "ID cannot be null") Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Name cannot be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name cannot be blank") String name) {
        this.name = name;
    }

    public @Email(message = "Email must be valid") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email must be valid") String email) {
        this.email = email;
    }
}
