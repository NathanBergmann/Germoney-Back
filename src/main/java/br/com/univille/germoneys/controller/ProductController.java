package br.com.univille.germoneys.controller;

import br.com.univille.germoneys.entity.Product;
import br.com.univille.germoneys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getClientes(){
        var productList  = service.getAll();
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Product product){
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> post(@RequestBody Product product){
        if(product.getId() == 0){
            service.save(product);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> put(@PathVariable long id,
                                       @RequestBody Product product){
        var oldProduct = service.getById(id);
        if(oldProduct == null){
            return ResponseEntity.notFound().build();
        }

        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());


        service.save(oldProduct);
        return new ResponseEntity<Product>(oldProduct, HttpStatus.OK);
    }

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
