package vn.hbtoan.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import vn.hbtoan.laptopshop.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role save(Role role);
    List<Role> findAll();
    Optional<Role> findById(Long id);
    boolean existsById(Long id);
    long count();
    void deleteById(Long id);
    void delete(Role role);
    boolean existsByName(String name);
    Optional<Role> findByName(String name);
    List<Role> findByNameContaining(String name);
    
}
