package com.shadoww.CardIndexApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatalogDto {

    @NotBlank(message = "Title can not be empty")
    @Size(min=1, message = "The minimum number of catalog symbols must be 1")
    private String title;


    private String description;


//    private String action = "/catalogs";


//    public CatalogDto(String action) {
//        this.action += action;
//    }

    public CatalogDto(String title, String description) {
        this.title = title;
        this.description = description;

    }
}
