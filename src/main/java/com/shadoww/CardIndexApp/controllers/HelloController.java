package com.shadoww.CardIndexApp.controllers;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.shadoww.CardIndexApp.dto.CardDto;
import com.shadoww.CardIndexApp.dto.CatalogDto;
import com.shadoww.CardIndexApp.models.Card;
import com.shadoww.CardIndexApp.models.Catalog;
import com.shadoww.CardIndexApp.services.CardsService;
import com.shadoww.CardIndexApp.services.CatalogsService;
import com.shadoww.CardIndexApp.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/qpr")
public class HelloController {

    private HelloService service;
    private CatalogsService catalogsService;
    private CardsService cardsService;

    private String link = "qpr";
    @Autowired
    public HelloController(HelloService service, CatalogsService catalogsService, CardsService cardsService) {
        this.service = service;
        this.catalogsService = catalogsService;
        this.cardsService = cardsService;
    }

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("Link /qpr2 ======================================");

        List<Catalog> catalogs = service.findPrCatalogs();

        System.out.println(catalogs);
        model.addAttribute("catalogDto", new CatalogDto());
        model.addAttribute("catalogs", catalogs);

        model.addAttribute("link", "/" + link);

        System.out.println("End!===================================");
        return "catalogs/index";
    }



    @PostMapping()
    public String createprCatalog(@Valid @ModelAttribute("catalogDto") CatalogDto catalogDto) {
        service.save(catalogDto);

        return "redirect:/" + link + "/";
    }
//
//    @GetMapping("/tojson")
//    public String toJson() {
//
////        List<Catalog> catalogs = catalogsService.findMainCatalogs();
////
////        if(!catalogs.isEmpty()) {
////            try(FileWriter writer = new FileWriter("list_catalogs.json")){
////                String str = new GsonBuilder().registerTypeAdapter(Catalog.class, new MyTypeAdapter<Catalog>()).create().toJson(catalogs);
////
////                writer.write(str);
////                System.out.println("List: " + str);
////            }catch (IOException e) {
////
////            }
////        }
//
//        List<Catalog> catalogs = service.findPrCatalogs();
//
//        if(!catalogs.isEmpty()) {
//            try(FileWriter writer = new FileWriter("list_catalogs.json")) {
//                JsonMapper mapper = createMapper();
//
//                writer.write(mapper.writeValueAsString(catalogs));
//            }catch (IOException e) {
//                System.out.println("Error:" +  e.getCause() + ". Message: " + e.getMessage() );
//            }
//        }
//
//
//
//        return "redirect:/" + link + "/";
//    }


    public static JsonMapper createMapper() {

        class ObjectArrayInNewLinePrettyPrinter extends DefaultPrettyPrinter {

            public ObjectArrayInNewLinePrettyPrinter() {
                super();
            }

            public ObjectArrayInNewLinePrettyPrinter(DefaultPrettyPrinter base) {
                super(base);
            }

            @Override
            public void writeStartObject(JsonGenerator g) throws IOException {
                _objectIndenter.writeIndentation(g, _nesting);
                super.writeStartObject(g);
            }

            @Override
            public void writeStartArray(JsonGenerator g) throws IOException {
                _arrayIndenter.writeIndentation(g, _nesting);
                super.writeStartArray(g);
            }

            @Override
            public DefaultPrettyPrinter createInstance() {
                return new ObjectArrayInNewLinePrettyPrinter(this);
            }
        }


        DefaultPrettyPrinter printer = new ObjectArrayInNewLinePrettyPrinter();
        printer.indentArraysWith(new DefaultIndenter());


        return JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .defaultPrettyPrinter(printer)
                .build();
    }
}
