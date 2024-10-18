package vn.hbtoan.laptopshop.dto;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String phone;
    private String avatar;
    private String role;
}
