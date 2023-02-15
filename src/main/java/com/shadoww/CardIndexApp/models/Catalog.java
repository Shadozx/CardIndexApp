package com.shadoww.CardIndexApp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shadoww.CardIndexApp.dto.CatalogDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "catalogs")
@Getter
@Setter
@NoArgsConstructor
public class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title can not be empty")
    @Size(min=1, message = "The minimum number of catalog symbols must be 1")
    private String title;

    private String description;

    @Min(value = 0, message = "Minimum rank of the catalog must be 0")
    private int rank;

    @OneToMany(mappedBy = "catalog", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Card> cards;

    @OneToMany(mappedBy = "overCatalog", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Catalog> subCatalogs;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "over_catalog")
    private Catalog overCatalog;


//    @ColumnDefault("true")
    private boolean ispub = true;
    //
//    @OneToMany(mappedBy = "overCatalog")
//    private List<Catalog> subCatalogs;

    public Catalog(String title, String description, int rank) {
        this.title = title;
        this.description = description;
        this.rank = rank;
    }

    public Catalog(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public Catalog(CatalogDto catalogDto) {
        this.rank = 0;

        this.title = catalogDto.getTitle();
        this.description = catalogDto.getDescription();

    }

    public void setOverCatalog(Catalog catalog) {
        if(catalog != null) {
            this.rank = catalog.getRank() + 1;
            this.overCatalog = catalog;
        }else this.rank = 0;
    }

    public void setCardOne(Card card) {
        if(cards == null) cards = new ArrayList<>();


        cards.add(card);
    }

    public void setSubCatalogOne(Catalog catalog) {
        if(subCatalogs == null) subCatalogs = new ArrayList<>();

        if(this != catalog) subCatalogs.add(catalog);
    }

    @Override
    public String toString() {
        return "{ Id:" + id +
                ". Title: " + title +
                ". Description: " + description +
                ". Rank: " + rank +
                ". Cards: \n" + cards +
                ". SubCatalogs: \n\t\t" + subCatalogs +
                "}"; }
}
