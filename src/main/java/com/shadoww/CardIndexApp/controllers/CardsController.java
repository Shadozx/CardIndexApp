package com.shadoww.CardIndexApp.controllers;


import com.shadoww.CardIndexApp.dto.CardDto;
import com.shadoww.CardIndexApp.models.Card;
import com.shadoww.CardIndexApp.services.CardsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cards")
public class CardsController {
    private CardsService cardsService;

    @Autowired
    public CardsController(CardsService cardsService) {
        this.cardsService = cardsService;
    }


    // Adding new card to catalog with id
    @PostMapping("/{id}")
    public String addCard(@PathVariable int id,  @ModelAttribute()CardDto cardDto) {
        cardsService.save(id, new Card(cardDto));

        return "redirect:/" + id;
    }



    // Deleting a card with id
    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteCard(@PathVariable int id) {
        cardsService.deleteOne(id);
        System.out.println("========== Card " + id + " was successful deleted!==========");
//        return "redirect:/";
    }

    @PutMapping("/{id}")
    public String updateCard(@Valid @ModelAttribute("card") Card card, BindingResult result, @PathVariable int id, Model model) {

        System.out.println("====================== PUT method /cards/" + id + "=============================");
        System.out.println(card);

        if(!result.hasErrors()) {
            cardsService.update(id, card);
            return "redirect:/card/" + id;
        } else {
            System.out.println("Card has errors...");

            model.addAttribute("message", "Було введені неправильні дані....");

            return "errors/notFound";
        }
    }
}
