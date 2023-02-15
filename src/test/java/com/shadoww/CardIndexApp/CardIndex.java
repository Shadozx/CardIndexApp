package com.shadoww.CardIndexApp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.shadoww.CardIndexApp.models.Card;
import com.shadoww.CardIndexApp.models.Catalog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CardIndex {

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

    @Test
    public void firsttest() throws JsonProcessingException {


//        ObjectMapper mapper = new ObjectMapper();

        DefaultPrettyPrinter printer = new ObjectArrayInNewLinePrettyPrinter();
        printer.indentArraysWith(new DefaultIndenter());

        JsonMapper mapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .defaultPrettyPrinter(printer)
                .build();

        String str = mapper.writeValueAsString(List.of(createOverCatalog(), new Catalog("ygyf", "fiji")));

        System.out.println(str);

        List<Catalog> list = Arrays.asList(mapper.readValue(str, Catalog[].class));


        Catalog catalog = list.get(0);

        Card card = catalog.getCards().get(0);

        System.out.println(card);

    }

    public Catalog createOverCatalog() {

        Catalog catalog1 = new Catalog("title1", "description1", 0);

        Card card1 = new Card("title1", "description1", "addtional1");
        setOverAndCard(catalog1, card1);
        Catalog catalog2 = new Catalog("title2", "description2", 1);

        setOverAndSub(catalog1, catalog2);


        Catalog catalog3 = new Catalog("title3", "description3", 2);
        setOverAndSub(catalog2, catalog3);

        Card card2 = new Card("title2", "description2", "addtional2");

        setOverAndCard(catalog3, card2);
        Catalog catalog4 = new Catalog("title4", "description4", 3);
        setOverAndSub(catalog3, catalog4);

        return catalog1;
    }

    public void setOverAndSub(Catalog over, Catalog sub) {
        over.setSubCatalogOne(sub);
        sub.setOverCatalog(over);
    }


    public void setOverAndCard(Catalog over, Card card) {
        over.setCardOne(card);
        card.setCatalog(over);
    }

}
