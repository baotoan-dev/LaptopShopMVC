package vn.hbtoan.laptopshop.dto.User;

import lombok.Data;

@Data
public class GetUserDTO {
    private String keyword = "";
    private String sort = "";
    private int page = 1;
    private int size = 10;
}
