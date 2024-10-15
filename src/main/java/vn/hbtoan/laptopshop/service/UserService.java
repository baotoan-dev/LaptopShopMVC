package vn.hbtoan.laptopshop.service;

import org.springframework.stereotype.Service;

import vn.hbtoan.laptopshop.domain.User;
import vn.hbtoan.laptopshop.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        try {
            boolean checkUser = this.userRepository.existsByEmail(user.getEmail());

            if (checkUser) {
                throw new RuntimeException("Email is already in use");
            }

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
                userUpdate.setEmail(user.getEmail());
                userUpdate.setFullName(user.getFullName());
                userUpdate.setPhone(user.getPhone());
                userUpdate.setAddress(user.getAddress());

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
