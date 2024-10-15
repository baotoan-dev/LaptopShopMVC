package vn.hbtoan.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/admin/user/update/{id}", method=RequestMethod.POST)
    public String requestMethodUpdate(Model model, @ModelAttribute User user, @PathVariable("id") Long id) {
        try {
            System.out.println("ID: " + id);
            User newUser = this.userService.update(user, id);
            
            if (newUser != null) {
                return "redirect:/admin/user";
            } else {
                return "redirect:/admin/user/edit/" + user.getId();
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @RequestMapping("/admin/user/delete/{id}")
    public String userDeletePage(Model model, @PathVariable("id") Long id) {
        try {
            this.userService.delete(id);
            return "redirect:/admin/user";
        } catch (Exception e) {
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

    // :id
    @RequestMapping("/admin/user/detail/{id}")
    public String userDetailPage(Model model, @PathVariable("id") Long id) {
        User user = this.userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    @RequestMapping("/admin/user/edit/{id}")
    public String userEditPage(Model model, @PathVariable("id") Long id) {
        User user = this.userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user/edit";
    }
}
