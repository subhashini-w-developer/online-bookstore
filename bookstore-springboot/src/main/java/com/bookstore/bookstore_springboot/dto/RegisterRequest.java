
package com.bookstore.bookstore_springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Name kudukkanum")
    private String name;

    @NotBlank(message = "Email kudukkanum")
    @Email(message = "Email format thappu")
    private String email;

    @NotBlank(message = "Password kudukkanum")
    @Size(min = 6, message = "Password kuraindhadhu 6 characters irukkanum")
    private String password;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}