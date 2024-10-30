package vn.hbtoan.laptopshop.repository.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import vn.hbtoan.laptopshop.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    boolean existsById(Long id);
    long count();
    void deleteById(Long id);
    void delete(User user);
    boolean existsByEmail(String email);
    User findByEmail(String email);
    List<User> findByEmailContaining(String email);
    Page<User> findByEmailOrFullNameContaining(String email, String fullName, Pageable pageable);
    Page<User> findByEmailContaining(String email, Pageable pageable);
    Page<User> findByFullNameContaining(String fullName, Pageable pageable);
}