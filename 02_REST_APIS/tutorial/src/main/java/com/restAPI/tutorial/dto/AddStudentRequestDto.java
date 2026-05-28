package com.restAPI.tutorial.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentRequestDto {

    @NotBlank(message = "name required")
    @Size(min = 1, max = 100, message = "Name should be of length 1 to 30 characters")
    private String name;

    @Email
    @NotBlank(message = "email required")
    private String email;
}