package vn.hbtoan.laptopshop.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    @RequestMapping ("/admin/product")
    public String adminProduct() {
        return "admin/product/show";
    }
}
