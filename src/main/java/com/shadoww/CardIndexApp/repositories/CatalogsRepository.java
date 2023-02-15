package com.shadoww.CardIndexApp.repositories;

import com.shadoww.CardIndexApp.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CatalogsRepository extends JpaRepository<Catalog, Integer> {

    List<Catalog> findCatalogByIspubIsTrueAndRank(int rank);

    void deleteCatalogByTitle(String title);

//    void deleteCatalogById(int id);

    void deleteAllByTitle(String title);
}
