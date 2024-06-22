package br.com.univille.germoneys.repository;

import br.com.univille.germoneys.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
