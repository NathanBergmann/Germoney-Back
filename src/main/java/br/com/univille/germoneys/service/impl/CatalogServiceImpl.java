package br.com.univille.germoneys.service.impl;

import br.com.univille.germoneys.entity.Catalog;
import br.com.univille.germoneys.repository.CatalogRepository;
import br.com.univille.germoneys.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl
        implements CatalogService {

    @Autowired
    private CatalogRepository repository;

    @Override
    public void save(Catalog catalog) {
        repository.save(catalog);
    }

    @Override
    public Catalog getById(long id) {
        return repository.getById(id);
    }

    @Override
    public Catalog getByName(String name) {
        for (Catalog catalog : repository.findAll()) {
            if (catalog.getName().equals(name)) {
                return catalog;
            }
        }
        return null;
    }


    @Override
    public List<Catalog> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        var catalog = repository.getById(id);
        repository.delete(catalog);
    }
}
