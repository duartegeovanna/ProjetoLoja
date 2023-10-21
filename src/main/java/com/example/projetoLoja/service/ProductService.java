package com.example.projetoLoja.service;

import com.example.projetoLoja.model.ProductModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    List<ProductModel> getAllProducts();

    Optional<ProductModel> getProduct(UUID id);

    void save(ProductModel productModel);

    void deleteProduct(UUID id);

    void updateProduct(ProductModel productModel, UUID id);
}
