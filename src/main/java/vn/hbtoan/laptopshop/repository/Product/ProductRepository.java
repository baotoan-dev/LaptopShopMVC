package vn.hbtoan.laptopshop.repository.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hbtoan.laptopshop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    boolean existsById(Long id);
    long count();
    void deleteById(Long id);
    void delete(Product product);
    boolean existsByName(String name);
    Optional<Product> findByName(String name);
    List<Product> findByNameContaining(String name);
    
}