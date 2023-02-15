package com.shadoww.CardIndexApp.controllers;


import com.shadoww.CardIndexApp.dto.CatalogDto;
import com.shadoww.CardIndexApp.models.Card;
import com.shadoww.CardIndexApp.models.Catalog;
import com.shadoww.CardIndexApp.services.CardsService;
import com.shadoww.CardIndexApp.services.CatalogsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

import java.util.List;
//import org.springframework.ui.Model;

@Controller
@RequestMapping("/catalogs")
public class CatalogsController {

    private CatalogsService catalogsService;
    private CardsService cardsService;

    @Autowired
    public CatalogsController(CatalogsService catalogsService, CardsService cardsService) {
        this.catalogsService = catalogsService;
        this.cardsService = cardsService;
    }



    // Adding new catalog
    @PostMapping()
    public String createCatalog(@ModelAttribute("catalogDto") CatalogDto catalogDto) {


        catalogsService.save(new Catalog(catalogDto));

        System.out.println("========== New catalog was successful added!==========");
        return "redirect:/";
    }


    // Add new catalog to another catalog
    @PostMapping("/{id}")
    public String addCatalogToAnother(@PathVariable int id, @ModelAttribute("catalogDto") CatalogDto catalogDto) {

        catalogsService.saveSubCatalog(id, new Catalog(catalogDto));
        System.out.println("========== Catalog where another catalog #" + id + " was successful added!==========");

        return "redirect:/" + id;
    }

    // Deleting catalog with id
    @DeleteMapping("/{id}")
    @ResponseBody
    public void  deleteCatalog(@PathVariable int id) {


        catalogsService.deleteOne(id);
        System.out.println("========== Catalog #" + id + " was successful deleted!==========");

//        return "{message:/"+overId + "}";
    }

    @PutMapping("/{id}")
    public String updateCard(@ModelAttribute("catalog") @Valid Catalog catalog, BindingResult result, @PathVariable int id, Model model) {

        System.out.println("====================== PUT method /catalog/" + id + "=============================");
        System.out.println(catalog);

        if(!result.hasErrors()) {
            catalogsService.update(id, catalog);
            return "redirect:/" + catalog.getId();
        } else {
            System.out.println("Catalog has errors...");

            model.addAttribute("message", "Було введені неправильні дані....");

            return "errors/notFound";
        }
    }

}
