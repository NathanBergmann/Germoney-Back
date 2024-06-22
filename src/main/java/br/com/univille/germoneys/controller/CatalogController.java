package br.com.univille.germoneys.controller;

import br.com.univille.germoneys.entity.Catalog;
import br.com.univille.germoneys.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    @Autowired
    private CatalogService service;

    @GetMapping
    public ResponseEntity<List<Catalog>> getCatalogs(){
        var catalogList  = service.getAll();
        return new ResponseEntity<List<Catalog>>(catalogList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalog> getCatalogById(@PathVariable("id") Catalog catalog){
        return new ResponseEntity<Catalog>(catalog, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Catalog> save(@RequestBody Catalog catalog){
        if(catalog.getId() == 0){
            service.save(catalog);
            return new ResponseEntity<Catalog>(catalog, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catalog> update(@PathVariable long id,
                                          @RequestBody Catalog catalog){
        var oldCatalog = service.getById(id);
        if(oldCatalog == null){
            return ResponseEntity.notFound().build();
        }

        oldCatalog.setName(catalog.getName());
        oldCatalog.setActive(catalog.getActive());
        oldCatalog.setProducts(catalog.getProducts());

        service.save(oldCatalog);
        return new ResponseEntity<Catalog>(oldCatalog, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Catalog> delete(@PathVariable long id){
        var catalog = service.getById(id);
        if(catalog == null){
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return new ResponseEntity<Catalog>(catalog,HttpStatus.OK);
    }
}
