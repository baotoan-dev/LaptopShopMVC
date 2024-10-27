package vn.hbtoan.laptopshop.dto.Product;

import lombok.Data;

@Data
public class GetProductDTO {
    
        private String keyword = "";
        private int page = 1;
        private int size = 10;
    }
