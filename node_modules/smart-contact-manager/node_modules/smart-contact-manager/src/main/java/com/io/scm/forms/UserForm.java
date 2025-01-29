package com.io.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Apply hibernate validation before signup
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

   @NotBlank(message = "Username is required")
   @Size(min = 3, message = "Min 3 characters is required")
   private String name;

   @Email(message = "Invalid email address")
   @NotBlank(message = "Email address is required")
   private String email;

   @NotBlank(message = "Password is required")
   @Size(min = 6, message = "Min 6 characters is required")
   private String password;

   @NotBlank(message = "About is required")
   private String about;

   @NotBlank(message = "Phone number is required")
   @Size(min = 8, max = 12, message = "Invalid phone number")
   private String phoneNumber;

}
