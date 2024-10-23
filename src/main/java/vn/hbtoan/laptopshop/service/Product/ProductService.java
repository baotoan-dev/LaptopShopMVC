package vn.hbtoan.laptopshop.service.Product;

import org.springframework.stereotype.Service;

import vn.hbtoan.laptopshop.domain.Product;
import vn.hbtoan.laptopshop.dto.Product.CreateProductDTO;
import vn.hbtoan.laptopshop.repository.Product.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(CreateProductDTO newProduct) {
        Product product = new Product();
        return this.productRepository.save(product);
    }
}
