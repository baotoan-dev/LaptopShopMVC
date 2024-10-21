package vn.hbtoan.laptopshop.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hbtoan.laptopshop.domain.User;
import vn.hbtoan.laptopshop.dto.CreateUserDTO;
import vn.hbtoan.laptopshop.dto.UpdateUserDTO;
import vn.hbtoan.laptopshop.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import vn.hbtoan.laptopshop.domain.Role;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public User save(CreateUserDTO createUserDTO) {
        try {
            validateUserCreation(createUserDTO);
    
            Role role = findRole(createUserDTO.getRole());
            
            String encodedPassword = encodePassword(createUserDTO.getPassword());
    
            User user = buildUser(createUserDTO, role, encodedPassword);
    
            return this.userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create user: " + e.getMessage(), e);
        }
    }
    
    private void validateUserCreation(CreateUserDTO createUserDTO) {
        if (this.userRepository.existsByEmail(createUserDTO.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }
    
        if (createUserDTO.getRole() == null || createUserDTO.getRole().isEmpty()) {
            throw new IllegalArgumentException("Role is required");
        }
    
        if (createUserDTO.getPassword() == null || createUserDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
    }
    
    private Role findRole(String roleName) {
        return this.roleService.findByName(roleName)
            .orElseThrow(() -> new IllegalArgumentException("Role is not found"));
    }
    
    private String encodePassword(String password) {
        String encodedPassword = this.bCryptPasswordEncoder.encode(password);
    
        if (encodedPassword == null || encodedPassword.isEmpty()) {
            throw new IllegalStateException("Password encoding failed");
        }
    
        return encodedPassword;
    }
    
    private User buildUser(CreateUserDTO createUserDTO, Role role, String encodedPassword) {
        User user = new User();
        user.setEmail(createUserDTO.getEmail());
        user.setFullName(createUserDTO.getFullName());
        user.setPhone(createUserDTO.getPhone());
        user.setAddress(createUserDTO.getAddress());
        user.setPassword(encodedPassword);
        user.setAvatar(createUserDTO.getAvatar());
        user.setRole(role);
        return user;
    }
    
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public List<User> findByEmail(String email) {
        return this.userRepository.findByEmailContaining(email);
    }

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }

        return null;
    }

    public User update(UpdateUserDTO updateUserDTO, Long id) {
        try {
            User userUpdate = this.findById(id);
    
            if (userUpdate != null) {
                boolean isModified = false;
    
                String roleName = updateUserDTO.getRole();
                if (roleName == null || roleName.isEmpty()) {
                    throw new RuntimeException("Role is required");
                }
    
                Role role = this.roleService.findByName(roleName).orElseThrow(() -> new RuntimeException("Role is not found"));
    
                if (!userUpdate.getRole().equals(role)) {
                    userUpdate.setRole(role);
                    isModified = true;
                }
    
                String password = updateUserDTO.getPassword();
                if (password == null || password.isEmpty()) {
                    throw new RuntimeException("Password is required");
                }
    
                String encodePassword = this.bCryptPasswordEncoder.encode(password);
                if (!userUpdate.getPassword().equals(encodePassword)) {
                    userUpdate.setPassword(encodePassword);
                    isModified = true;
                }
    
                if (!userUpdate.getEmail().equals(updateUserDTO.getEmail())) {
                    userUpdate.setEmail(updateUserDTO.getEmail());
                    isModified = true;
                }
    
                if (!userUpdate.getFullName().equals(updateUserDTO.getFullName())) {
                    userUpdate.setFullName(updateUserDTO.getFullName());
                    isModified = true;
                }
    
                if (!userUpdate.getPhone().equals(updateUserDTO.getPhone())) {
                    userUpdate.setPhone(updateUserDTO.getPhone());
                    isModified = true;
                }
    
                if (!userUpdate.getAddress().equals(updateUserDTO.getAddress())) {
                    userUpdate.setAddress(updateUserDTO.getAddress());
                    isModified = true;
                }
    
                if (!userUpdate.getAvatar().equals(updateUserDTO.getAvatar())) {
                    userUpdate.setAvatar(updateUserDTO.getAvatar());
                    isModified = true;
                }
    
                if (isModified) {
                    return this.userRepository.save(userUpdate);
                } else {
                    System.out.println("No changes detected");
                    return userUpdate;
                }
            }
    
            return null;
    
        } catch (Exception e) {
            throw new RuntimeException("Error updating user: " + e.getMessage(), e);
        }
    }
    

    public void delete(Long id) {
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
