package com.supermarket.springbootcrudbackend.controller;

import com.supermarket.springbootcrudbackend.exception.ResourceNotFoundException;
import com.supermarket.springbootcrudbackend.model.Product;
import com.supermarket.springbootcrudbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // Get products
    @GetMapping("products")
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    // Get products by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        return ResponseEntity.ok().body(product);
    }

    // Save products
    @PostMapping("products")
    public Product createProduct(@RequestBody Product product) {
        return  this.productRepository.save(product);
    }

    // Update product
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
                                                 @Validated @RequestBody Product productDetails) throws ResourceNotFoundException {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        product.setName(productDetails.getName());
        product.setStock(productDetails.getStock());
        product.setPrice(productDetails.getPrice());
        product.setCreateDate(productDetails.getCreateDate());
        product.setUpdateDate(productDetails.getUpdateDate());
        product.setCreateUser(productDetails.getCreateUser());
        product.setUpdateUser(productDetails.getUpdateUser());

        return ResponseEntity.ok(this.productRepository.save(product));
    }

    // Delete product
    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new  ResourceNotFoundException("Product not found for this id :: " + productId));

        this.productRepository.delete(product);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}