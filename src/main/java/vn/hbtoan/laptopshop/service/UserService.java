package vn.hbtoan.laptopshop.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hbtoan.laptopshop.domain.User;
import vn.hbtoan.laptopshop.dto.CreateUserDTO;
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
            boolean checkUser = this.userRepository.existsByEmail(createUserDTO.getEmail());

            if (checkUser) {
                throw new RuntimeException("Email is already in use");
            }

            String roleName = createUserDTO.getRole();

            if (roleName == null || roleName.isEmpty()) {
                throw new RuntimeException("Role is required");
            }

            Role role = this.roleService.findByName(roleName).get();

            if (role == null) {
                throw new RuntimeException("Role is not found");
            }

            String password = createUserDTO.getPassword();

            if (password == null || password.isEmpty()) {
                throw new RuntimeException("Password is required");
            }

            String encodePassword = this.bCryptPasswordEncoder.encode(password);

            if (encodePassword == null || encodePassword.isEmpty()) {
                throw new RuntimeException("Password is not encoded");
            }

            User user = new User();
            user.setEmail(createUserDTO.getEmail());
            user.setFullName(createUserDTO.getFullName());
            user.setPhone(createUserDTO.getPhone());
            user.setAddress(createUserDTO.getAddress());
            user.setPassword(encodePassword);
            user.setAvatar(createUserDTO.getAvatar());
            user.setRole(role);

            return this.userRepository.save(user);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
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

    public User update(User user, Long id) {
        try {
            User userUpdate = this.findById(id);

            if (userUpdate != null) {

                String roleName = user.getRole().getName();

                if (roleName == null || roleName.isEmpty()) {
                    throw new RuntimeException("Role is required");
                }
    
                Role role = this.roleService.findByName(roleName).get();
    
                System.out.println(role);
                if (role == null) {
                    throw new RuntimeException("Role is not found");
                }
    
                String password = user.getPassword();
    
                if (password == null || password.isEmpty()) {
                    throw new RuntimeException("Password is required");
                }

                String encodePassword = this.bCryptPasswordEncoder.encode(password);
    
                if (encodePassword == null || encodePassword.isEmpty()) {
                    throw new RuntimeException("Password is not encoded");
                }

                userUpdate.setEmail(user.getEmail());
                userUpdate.setFullName(user.getFullName());
                userUpdate.setPhone(user.getPhone());
                userUpdate.setAddress(user.getAddress());
                userUpdate.setPassword(encodePassword);
                userUpdate.setAvatar(user.getAvatar());
                userUpdate.setRole(role);

                return this.userRepository.save(userUpdate);
            }

            return null;

        } catch (Exception e) {
            throw e;
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
