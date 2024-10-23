package vn.hbtoan.laptopshop.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hbtoan.laptopshop.domain.User;
import vn.hbtoan.laptopshop.dto.CreateUserDTO;
import vn.hbtoan.laptopshop.dto.UpdateUserDTO;
import vn.hbtoan.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;


@Controller
public class UserController {

    private UserService userService;  
    
    @Value("${PREFIX_CLOUDINARY}")
    private String cloudinaryPrefixUrl;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/admin/user/create")
    public String adminUserPage(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/save", method=RequestMethod.POST)
    public String requestMethodName(
        Model model,
        @Valid @ModelAttribute("userDTO") CreateUserDTO createUserDTO,
        BindingResult bindingResult
    ) {
        try {
            //validate user
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }

            User newUser = this.userService.save(createUserDTO);
            
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

    @RequestMapping(value = "/admin/user/update/{id}", method = RequestMethod.POST)
    public String requestMethodUpdate(Model model, @ModelAttribute UpdateUserDTO updateUserDTO, @PathVariable("id") Long id) {
        try {    
            User updatedUser = this.userService.update(updateUserDTO, id);
            
            if (updatedUser != null) {
                return "redirect:/admin/user";
            } else {
                model.addAttribute("errorMessage", "Update failed. Please try again.");
                return "redirect:/admin/user/edit/" + id;
            }
        } catch (Exception e) {
            System.err.println("Error occurred during user update: " + e.getMessage());
            e.printStackTrace();
            
            model.addAttribute("errorMessage", "An error occurred while updating the user: " + e.getMessage());
            
            return "redirect:/admin/user/edit/" + id;
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
        UpdateUserDTO updateUserDTO = UpdateUserDTO.fromUser(user);
        model.addAttribute("user", updateUserDTO);
        return "admin/user/edit";
    }
}
