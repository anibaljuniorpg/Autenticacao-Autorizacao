package com.Kipper.First_spring_app.controller;

import com.Kipper.First_spring_app.domain.product.Product;
import com.Kipper.First_spring_app.domain.product.ProductRequestDTO;
import com.Kipper.First_spring_app.domain.product.ProductResponseDTO;
import com.Kipper.First_spring_app.repository.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    //Criacao de um produto do tipo ProductRequestDTO e validado com @valid
    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequestDTO body){
        Product newProduct = new Product(body);

        this.productRepository.save(newProduct);
        return ResponseEntity.ok().build();
    }
    //Todos os produtos sao recuperados do banco de dados usando o repositorio. RETORNA UMA LISTA COMO RESPOSTA JSON
    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProductResponseDTO> productList;
        productList = this.productRepository.findAll().stream().map(ProductResponseDTO::new).toList();
        return ResponseEntity.ok(productList);
    }

}
