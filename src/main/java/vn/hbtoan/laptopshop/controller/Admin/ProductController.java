package vn.hbtoan.laptopshop.controller.Admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import jakarta.validation.Valid;
import vn.hbtoan.laptopshop.domain.Product;
import vn.hbtoan.laptopshop.dto.Product.CreateProductDTO;
import vn.hbtoan.laptopshop.dto.Product.GetProductDTO;
import vn.hbtoan.laptopshop.service.Product.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model, @ModelAttribute GetProductDTO getProductDTO) {
        Page<Product> products = this.productService.searchProducts(getProductDTO);
        model.addAttribute("products", products.getContent());
        model.addAttribute("currentPage", getProductDTO.getPage());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("keyword", getProductDTO.getKeyword());
        model.addAttribute("sort", getProductDTO.getSort());

        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new CreateProductDTO());
        return "admin/product/create";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String getEditProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getById(id);
        model.addAttribute("product", product);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/save")
    public String saveProduct(@Valid CreateProductDTO newProduct, BindingResult bindingResult, Model model) {
        try {
             if (bindingResult.hasErrors()) {
                List<FieldError> errors = bindingResult.getFieldErrors();
                for (FieldError error : errors ) {
                    System.out.println (error.getField() + " - " + error.getDefaultMessage());
                }
    
                return "redirect:/admin/product/create";
            }

            Product product = this.productService.save(newProduct);

            if (product != null) {
                return "redirect:/admin/product";
            } else {
                return "redirect:/admin/product/create";
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create product: " + e.getMessage(), e);
        }
    }
}
