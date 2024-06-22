package br.com.univille.germoneys.service;

import br.com.univille.germoneys.entity.Catalog;

import java.util.List;

public interface CatalogService {
    void save(Catalog catalog);
    Catalog getById(long id);
    Catalog getByName(String name);
    List<Catalog> getAll();
    void delete(long id);

}
