package br.com.univille.germoneys.controller;

import br.com.univille.germoneys.entity.Product;
import br.com.univille.germoneys.service.product.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        var productList  = service.getAll();
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    @GetMapping("/theyCanBuy")
    public ResponseEntity<List<Product>> getProductsTheyCanBuy(@RequestParam BigDecimal money){
        var productList = service.getAll();
        if (money == null) {
            return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
        }
        List<Product> newProductList = new ArrayList<>();
        for(Product product : productList){
            if (money.compareTo(product.getPrice()) >= 0) {
                newProductList.add(product);
            }
        }
        return new ResponseEntity<>(newProductList, HttpStatus.OK);
    }

    @GetMapping("/theyCantBuy")
    public ResponseEntity<List<Product>> getProductsTheyCantBuy(@RequestParam BigDecimal money){
        var productList = service.getAll();
        if (money == null) {
            return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
        }
        List<Product> newProductList = new ArrayList<>();
        for(Product product : productList){
            if (money.compareTo(product.getPrice()) < 0) {
                newProductList.add(product);
            }
        }
        return new ResponseEntity<>(newProductList, HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Product product){
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        if (product.getId() != null) return ResponseEntity.badRequest().build();

        service.save(product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable long id,
                                       @RequestBody Product product){
        var oldProduct = service.getById(id);
        if(oldProduct == null){
            return ResponseEntity.notFound().build();
        }

        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setActive(product.getActive());
        oldProduct.setImageUrl(product.getImageUrl());


        service.save(oldProduct);
        return new ResponseEntity<Product>(oldProduct, HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable long id){
        var product = service.getById(id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }
}
