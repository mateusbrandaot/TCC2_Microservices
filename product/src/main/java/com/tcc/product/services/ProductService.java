package com.tcc.product.services;

import com.tcc.product.dtos.ProductDTO;
import com.tcc.product.exception.ResourceNotFoundException;
import com.tcc.product.models.Category;
import com.tcc.product.models.Product;
import com.tcc.product.repositories.CategoryRepository;
import com.tcc.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(ProductDTO productdto) {
        Category category = categoryService.findById(productdto.getIdCategory());
        Product product = productBuilder(productdto, category);
        productRepository.save(product);
        return product;

    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    Product productBuilder(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        return product;
    }
}
