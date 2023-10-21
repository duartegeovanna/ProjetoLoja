package com.example.projetoLoja.service.impl;

import com.example.projetoLoja.controller.ProductController;
import com.example.projetoLoja.exception.NotFoundException;
import com.example.projetoLoja.model.ProductModel;
import com.example.projetoLoja.repository.ProductRepository;
import com.example.projetoLoja.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        List<ProductModel> productsList = productRepository.findAll();
        if(!productsList.isEmpty()) {
            for(ProductModel product : productsList) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return productsList;
    }

    public Optional<ProductModel> getProduct(UUID id) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()) {
            throw new NotFoundException("Product not found.");
        }
       return productO;
    }

    public void save(ProductModel productModel) {
        productRepository.save(productModel);
    }

    public void updateProduct(ProductModel productModel, UUID id) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()) {
            throw new NotFoundException("Product not found.");
        }
        productModel = productO.get();
        productRepository.save(productModel);
    }

    public void deleteProduct(UUID id) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()) {
            throw new NotFoundException("Product not found.");
        }
        productRepository.delete(productO.get());
    }
}
