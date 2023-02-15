package com.shadoww.CardIndexApp.services;

import com.shadoww.CardIndexApp.models.Card;
import com.shadoww.CardIndexApp.models.Catalog;
import com.shadoww.CardIndexApp.repositories.CardsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CardsService {

    private CatalogsService catalogsService;
    private CardsRepository cardsRepository;


    public CardsService(CatalogsService catalogsService, CardsRepository cardsRepository) {
        this.catalogsService = catalogsService;
        this.cardsRepository = cardsRepository;
    }


    public List<Card> findAll() {
        return cardsRepository.findAll();
    }

    public Optional<Card> findOne(int id) {return cardsRepository.findById(id);}

    @Transactional
    public void save(int id, Card card) {
        Optional<Catalog> foundCatalog = catalogsService.findOne(id);

        if(foundCatalog.isPresent()) {
            card.setCatalog(foundCatalog.get());

            foundCatalog.get().setCardOne(card);

            catalogsService.save(foundCatalog.get());
            save(card);
        } else {
            System.out.println("Невдалося зберегти картку(так як каталог з ід " + id + " не існує...");
        }
    }

    @Transactional
    public void save(Card card) {
        cardsRepository.save(card);
    }

    @Transactional
    public void update(int id, Card card) {
        Optional<Card> forUpdateCard = findOne(id);


        System.out.println("Перед обновленням: " + forUpdateCard);

        if(forUpdateCard.isPresent()) {

            forUpdateCard.get().setTitle(card.getTitle());
            forUpdateCard.get().setDescription(card.getDescription());
            forUpdateCard.get().setAddition(card.getAddition());

            System.out.println("Після оновлення: " + forUpdateCard);
            save(forUpdateCard.get());

        }
        else {
            System.out.println("Такої картки не існує...");
        }
    }

    @Transactional
    public void deleteOne(int id) {
        var forDelete = findOne(id);
        if(forDelete.isPresent()) {
            forDelete.get().setCatalog(null);
            cardsRepository.delete(forDelete.get());
        }

    }
}
