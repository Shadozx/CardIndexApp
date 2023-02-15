package com.shadoww.CardIndexApp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardDto {

    @NotBlank(message = "Title can not be empty")
    @Size(min = 1, message = "The minimum number of card symbols must be 1")
    private String title;

    private String description;

    private String addition;


    public CardDto(String title, String description, String addition) {
        this.title = title;
        this.description = description;
        this.addition = addition;
    }
}
