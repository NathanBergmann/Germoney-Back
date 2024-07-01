package br.com.univille.germoneys.service.product;

import br.com.univille.germoneys.entity.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    Product getById(long id);
    List<Product> getAll();
    void delete(long id);
}
