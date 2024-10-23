package vn.hbtoan.laptopshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDTO {

    @NotNull
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty
    private String email;

    @NotNull
    @Size(min = 6, max = 255)
    @NotEmpty
    private String password;

    @NotNull
    @Size(min = 6, max = 255)
    @NotEmpty
    private String fullName;

    private String address;

    private String phone;

    private String avatar;

    @NotNull
    private String role;
}
