package vn.hbtoan.laptopshop.controller.Client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vn.hbtoan.laptopshop.domain.Product;
import vn.hbtoan.laptopshop.dto.User.RegisterDTO;
import vn.hbtoan.laptopshop.service.Product.ProductService;
import vn.hbtoan.laptopshop.service.User.UserService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.List;

@Controller
public class HomePageController {
    private final String HOME_PAGE = "client/homepage/show";
    private final String REGISTER_PAGE = "client/auth/register";
    private final String LOGIN_PAGE = "client/auth/login";
    private final String REDIRECT_HOME = "redirect:/";

    private UserService userService;
    private ProductService productService;

    public HomePageController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }
    
    @RequestMapping("/")
    public String homePage(Model model) {
        List<Product> products = this.productService.getAll();
        model.addAttribute("products", products);
        return HOME_PAGE;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "client/auth/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return REGISTER_PAGE;
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDTO registerDTO, BindingResult bindingResult, Model model) {
        System.out.println(registerDTO);
          if (bindingResult.hasErrors()) {
                List<FieldError> errors = bindingResult.getFieldErrors();
                for (FieldError error : errors ) {
                    System.out.println (error.getField() + " - " + error.getDefaultMessage());
                }
    
                return REGISTER_PAGE;
            }
    
        this.userService.registerDTOtoUser(registerDTO);

        return "redirect:/";
    }
}
