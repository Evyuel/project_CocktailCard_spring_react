package ru.dtimofeev.cocktailCard.service;

import ru.dtimofeev.cocktailCard.controller.dto.node.Cocktail;

import java.util.List;
import java.util.Optional;

public interface CocktailService {

    List<Cocktail> getCocktailsByParam(String name);

    Optional<Cocktail> getCocktailByFullName(String name);

    List<Cocktail> getCocktailByIngredientName(String name);

    List<Cocktail> getCocktailByInstrumentName(String name);

    void saveCocktail(Cocktail cocktail);

    void deleteCocktailByName(String name);
}
