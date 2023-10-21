package com.example.projetoLoja.controller;

import com.example.projetoLoja.dto.ProductRecordDto;
import com.example.projetoLoja.mapper.ProductMapper;
import com.example.projetoLoja.model.ProductModel;
import com.example.projetoLoja.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        var productsList = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id){
        var productO = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }

    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        productService.save(productMapper.create(productRecordDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {
        productService.updateProduct(productMapper.update(productRecordDto), id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

}