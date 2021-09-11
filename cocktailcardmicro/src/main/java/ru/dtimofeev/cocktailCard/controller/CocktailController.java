package ru.dtimofeev.cocktailCard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dtimofeev.cocktailCard.controller.dto.node.Cocktail;
import ru.dtimofeev.cocktailCard.logging.Loggable;
import ru.dtimofeev.cocktailCard.service.CocktailService;

import java.util.List;

@Loggable
@RestController
public class CocktailController {

    private final CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("/api/cocktails")
    public ResponseEntity<List<Cocktail>> getCocktailsByParam(@RequestParam(value = "name", required = false) String name) {
        return ResponseEntity.status(HttpStatus.OK).body(cocktailService.getCocktailsByParam(name));
    }

    @GetMapping("/api/cocktail/{name}")
    public ResponseEntity<Cocktail> getCocktailByFullName(@PathVariable(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(cocktailService.getCocktailByFullName(name).get());
    }

    @GetMapping("/api/cocktail/ingredient/{name}")
    public ResponseEntity<List<Cocktail>> getCocktailByIngredientName(@PathVariable(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(cocktailService.getCocktailByIngredientName(name));
    }

    @GetMapping("/api/cocktail/instrument/{name}")
    public ResponseEntity<List<Cocktail>> getCocktailByInstrumentName(@PathVariable(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(cocktailService.getCocktailByInstrumentName(name));
    }

    @PostMapping("/api/cocktail")
    public ResponseEntity<HttpStatus> saveCocktail(@RequestBody Cocktail cocktail) {
        cocktailService.saveCocktail(cocktail);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/api/cocktail/{name}")
    public ResponseEntity<HttpStatus> deleteCocktailByName(@PathVariable String name) {
        cocktailService.deleteCocktailByName(name);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
