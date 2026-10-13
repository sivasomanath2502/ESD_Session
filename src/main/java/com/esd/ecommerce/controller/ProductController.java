package com.esd.ecommerce.controller;

import com.esd.ecommerce.entity.Product;
import com.esd.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {

        Product product = productService.getProductById(id);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + " not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        Product savedProduct = productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        Product updatedProduct = productService.updateProduct(id, product);

        if (updatedProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + " not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product with ID " + id + " deleted successfully");
    }
}