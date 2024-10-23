package vn.hbtoan.laptopshop.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDTO {

    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
    @NotEmpty(message = "Password is required")
    private String password;

    @NotNull(message = "Full name is required")
    @Size(min = 6, max = 255, message = "Full name must be between 6 and 255 characters")
    @NotEmpty(message = "Full name is required")
    private String fullName;

    private String address;

    private String phone;

    private String avatar;

    @NotNull
    private String role;
}
