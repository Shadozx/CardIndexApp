package com.shadoww.CardIndexApp.services;


import com.shadoww.CardIndexApp.models.Card;
import com.shadoww.CardIndexApp.models.Catalog;
import com.shadoww.CardIndexApp.repositories.CardsRepository;
import com.shadoww.CardIndexApp.repositories.CatalogsRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CatalogsService {

    private CatalogsRepository catalogsRepository;



    @Autowired
    public CatalogsService(CatalogsRepository catalogsRepository) {
        this.catalogsRepository = catalogsRepository;
    }

    public List<Catalog> findMainCatalogs() {
        return catalogsRepository.findCatalogByIspubIsTrueAndRank(0);
//        return catalogsRepository.findCatalogByRank(0);
    }


    public List<Catalog> findAll() {return catalogsRepository.findAll();}


    public Optional<Catalog> findOne(int id) {return catalogsRepository.findById(id);}

    @Transactional
    public void save(Catalog catalog) {
        catalogsRepository.save(catalog);
    }


    @Transactional
    public void saveSubCatalog(int id, Catalog subCatalog) {
        Optional<Catalog> overCatalog = findOne(id);



        if(overCatalog.isPresent()) {
//            overCatalog.get().setId(id);
            subCatalog.setOverCatalog(overCatalog.get());
            overCatalog.get().setSubCatalogOne(subCatalog);

            save(overCatalog.get());
            save(subCatalog);
        } else {
            System.out.println("Такого старшого каталогу не існує...");
        }
    }
    @Transactional
    public void delete(Catalog catalog) {

        catalogsRepository.delete(catalog);
    }



    @Transactional
    public void update(int id, Catalog updatedCatalog) {
        Optional<Catalog> forUpdateCatalog = findOne(id);


        if(forUpdateCatalog.isPresent()) {

            forUpdateCatalog.get().setTitle(updatedCatalog.getTitle());
            forUpdateCatalog.get().setDescription(updatedCatalog.getDescription());


            System.out.println(forUpdateCatalog);
            save(forUpdateCatalog.get());

        } else {
            System.out.println("Такого каталогу не існує...");
        }
    }

    @Transactional
    public void deleteOne(int id) { catalogsRepository.deleteById(id);}


    @Transactional
    public void deleteTitle(String title) { catalogsRepository.deleteCatalogByTitle(title);}


    @Transactional
    public void deleteAllByTitle(String title) {
        catalogsRepository.deleteAllByTitle(title);
    }

}
