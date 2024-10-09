package vn.hbtoan.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hbtoan.laptopshop.domain.User;
import vn.hbtoan.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class UserController {

    private UserService userService;        

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/admin/user/create")
    public String adminUserPage(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/save", method=RequestMethod.POST)
    public String requestMethodName(Model model, @ModelAttribute User user) {
        try {
            User newUser = this.userService.save(user);
            
            if (newUser != null) {
                return "redirect:/admin/user";
            } else {
                return "redirect:/admin/user/create";
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    
    @RequestMapping("/admin/user")
    public String userPage(Model model, @RequestParam(value = "email", required = false) String email) {
        if (email != null) {
            List<User> users = this.userService.findByEmail(email);
            model.addAttribute("users", users);
            model.addAttribute("email", email);
            return "admin/user/list";
        }
        else {
            List<User> users = this.userService.findAll();
            model.addAttribute("users", users);
            return "admin/user/list";
        }
    }
}
