package vn.hbtoan.laptopshop.dto.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hbtoan.laptopshop.service.validator.StrongPassword;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotNull(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Email is required")
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    @StrongPassword(message = "Password must be a combination of uppercase letters, lowercase letters, numbers, and special characters.")
    private String password;

    @NotNull(message = "Confirm password is required")
    @Size(min = 6, max = 50, message = "Confirm password must be between 6 and 50 characters")
    private String confirmPassword;
}
