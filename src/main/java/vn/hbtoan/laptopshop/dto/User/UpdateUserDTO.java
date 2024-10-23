package vn.hbtoan.laptopshop.dto.User;

import lombok.Data;
import vn.hbtoan.laptopshop.domain.User;

@Data
public class UpdateUserDTO {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String phone;
    private String avatar;
    private String role;    

    public static UpdateUserDTO fromUser(User user) {
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setAddress(user.getAddress());
        dto.setPhone(user.getPhone());
        dto.setAvatar(user.getAvatar());
        dto.setRole(user.getRole().getName());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
