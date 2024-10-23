package vn.hbtoan.laptopshop.dto.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CreateProductDTO {

    @NotNull(message = "Name is required")
    @Size(min = 6, max = 255, message = "Name must be between 6 and 255 characters")
    private String name;

    @NotNull(message = "Price is required")
    @Size(min = 1, message = "Price must be a number")
    private double price;

    private String image;

    private String detailDesc;

    private String shortDesc;

    private long quantity;

    private long sold;

    private String factory;

    private String target;
}
