package br.com.univille.germoneys.service.impl;

import br.com.univille.germoneys.entity.Product;
import br.com.univille.germoneys.repository.ProductRepository;
import br.com.univille.germoneys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl
        implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public Product getById(long id) {
        return repository.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        var product = repository.getById(id);
        repository.delete(product);

    }
}
