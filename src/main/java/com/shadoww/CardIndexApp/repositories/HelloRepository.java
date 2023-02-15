package com.shadoww.CardIndexApp.repositories;

import com.shadoww.CardIndexApp.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelloRepository extends JpaRepository<Catalog, Integer> {

    List<Catalog> findCatalogsByIspubIsFalseAndRank(int rank);
}
