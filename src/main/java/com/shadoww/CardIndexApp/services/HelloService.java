package com.shadoww.CardIndexApp.services;

import com.shadoww.CardIndexApp.dto.CatalogDto;
import com.shadoww.CardIndexApp.models.Catalog;
import com.shadoww.CardIndexApp.repositories.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloService {


    private HelloRepository rep;
    private CatalogsService catalogsService;

    @Autowired
    public HelloService(HelloRepository rep, CatalogsService catalogsService) {
        this.rep = rep;
        this.catalogsService = catalogsService;
    }

    public List<Catalog> findPrCatalogs() {
        return rep.findCatalogsByIspubIsFalseAndRank(0);
    }

    public void save(CatalogDto catalogDto) {
        Catalog c = new Catalog(catalogDto);
        c.setIspub(false);

        catalogsService.save(c);
    }
}
