package com.tcc.product.services;

import com.tcc.product.dtos.CategoryDTO;
import com.tcc.product.exception.ResourceNotFoundException;
import com.tcc.product.models.Category;
import com.tcc.product.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(CategoryDTO category) {
        return categoryRepository.save(buildCategory(category));
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    Category buildCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
}
