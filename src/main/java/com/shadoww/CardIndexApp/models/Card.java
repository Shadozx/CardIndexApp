package com.shadoww.CardIndexApp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shadoww.CardIndexApp.dto.CardDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title can not be empty")
    @Size(min = 1, message = "The minimum number of card symbols must be 1")
    private String title;


    private String description;

    private String addition;

    @Min(value = 0, message = "Minimum rank of the card must be 0")
    private int rank;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "catalog_id", referencedColumnName = "id")
    private Catalog catalog;


    public Card(CardDto cardDto) {
        this.title = cardDto.getTitle();
        this.description = cardDto.getDescription();
        this.addition = cardDto.getAddition();
    }


    public Card(String title, String description, String addition) {
        this.title = title;
        this.description = description;
        this.addition = addition;
    }

    public void setCatalog(Catalog catalog) {
        if(catalog != null) {
            this.rank = catalog.getRank() + 1;
            this.catalog = catalog;
        }
    }

    @Override
    public String toString() {
        return "{ Id:" + id +
                ". Title: " + title +
                ". Description: " + description +
                ". Additional: " + addition +
                ". Rank: " + rank +
                "}"; }
}
