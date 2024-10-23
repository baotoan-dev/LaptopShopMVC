package vn.hbtoan.laptopshop.service.Product;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import vn.hbtoan.laptopshop.domain.Product;
import vn.hbtoan.laptopshop.dto.Product.CreateProductDTO;
import vn.hbtoan.laptopshop.repository.Product.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public Product save(CreateProductDTO newProduct) {
        
        Product product = modelMapper.map(newProduct, Product.class);

        product.setSold(0);

        return this.productRepository.save(product);
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product getById(long id) {
        return this.productRepository.findById(id).get();
    }
}
