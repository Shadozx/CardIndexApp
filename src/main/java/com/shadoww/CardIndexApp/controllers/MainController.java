package com.shadoww.CardIndexApp.controllers;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.shadoww.CardIndexApp.dto.CardDto;
import com.shadoww.CardIndexApp.dto.CatalogDto;
import com.shadoww.CardIndexApp.models.Card;
import com.shadoww.CardIndexApp.models.Catalog;
import com.shadoww.CardIndexApp.services.CardsService;
import com.shadoww.CardIndexApp.services.CatalogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {

    private CatalogsService catalogsService;
    private CardsService cardsService;

    @Autowired
    public MainController(CatalogsService catalogsService, CardsService cardsService) {
        this.catalogsService = catalogsService;
        this.cardsService = cardsService;
    }

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("Link / ======================================");

        List<Catalog> catalogs = catalogsService.findMainCatalogs();

        System.out.println(catalogs);
        model.addAttribute("catalogDto", new CatalogDto());
        model.addAttribute("catalogs", catalogs);

        model.addAttribute("link", "/catalogs");

        System.out.println("End!===================================");
        return "catalogs/index";
    }

    @GetMapping("/{id}")
    public String showCatalog(@PathVariable int id, Model model) {
            Optional<Catalog> foundCatalog = catalogsService.findOne(id);

        System.out.println(foundCatalog);

        System.out.println("===========================/" + id + "=========================================");
            if(foundCatalog.isPresent()) {
                model.addAttribute("catalog", foundCatalog.get());


                model.addAttribute("subCatalogs", foundCatalog.get().getSubCatalogs());
                model.addAttribute("cards", foundCatalog.get().getCards());
                model.addAttribute("link", "/catalogs/" + id);

                model.addAttribute("catalogDto", new CatalogDto());
                model.addAttribute("cardDto", new CardDto());

                return "catalogs/id";
            }else {

                model.addAttribute("message", "Такого каталогу не було знайдено....");

                return "errors/notFound";
            }
    }


    @GetMapping("/{id}/edit")
    public String updateCatalog(@PathVariable int id, Model model) {

        Optional<Catalog> foundCatalog = catalogsService.findOne(id);

        if(foundCatalog.isPresent()) {

            model.addAttribute("catalog", foundCatalog.get());

            return "catalogs/edit";
        }
        else {
            model.addAttribute("message", "Такого каталогу не було знайдено....");

            return "errors/notFound";
        }


    }
    @GetMapping("/card/{id}")
    public String showCard(@PathVariable int id, Model model) {
        Optional<Card> foundCard = cardsService.findOne(id);

        System.out.println(foundCard);
        System.out.println("===========================/" + id + "=========================================");
        if(foundCard.isPresent()) {
            model.addAttribute("card", foundCard.get());
            return "cards/edit";
        }else {
            model.addAttribute("message", "Такої картки не було знайдено....");

            return "errors/notFound";
        }
    }

    class MyTypeAdapter<T> extends TypeAdapter<T> {
        public T read(JsonReader reader) throws IOException {
            return null;
        }

        public void write(JsonWriter writer, T obj) throws IOException {
            if (obj == null) {
                writer.nullValue();
                return;
            }
//            writer.(obj);
        }
    }

//    @GetMapping("/tojson")
//    public String toJson()  {
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
//        List<Catalog> catalogs = catalogsService.findMainCatalogs();
//
//        try (FileWriter writer = new FileWriter("list_pub_catalogs.json")) {
//
//
//            JsonMapper mapper = createMapper();
//
//            writer.write(mapper.writeValueAsString(catalogs));
//        }catch (IOException e) {
//
//        }
//        return "redirect:/";
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
