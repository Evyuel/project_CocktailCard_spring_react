package ru.dtimofeev.cocktailCard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.cocktailCard.controller.dto.mapper.CocktailMapper;
import ru.dtimofeev.cocktailCard.controller.dto.node.Cocktail;
import ru.dtimofeev.cocktailCard.controller.dto.relation.DescriptionRelation;
import ru.dtimofeev.cocktailCard.repository.CocktailRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CocktailServiceImpl implements CocktailService {

    private final CocktailRepository cocktailRepository;
    private final CocktailMapper mapper;

    @Autowired
    public CocktailServiceImpl(CocktailRepository cocktailRepository, CocktailMapper mapper) {
        this.cocktailRepository = cocktailRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Cocktail> getCocktailsByParam(String name) {
        if (name != null) {
            return cocktailRepository.findByNameStartingWithIgnoreCase(name).stream().map(mapper::toDto).collect(Collectors.toList());
        } else {
            return cocktailRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
        }
    }

    @Override
    public Optional<Cocktail> getCocktailByFullName(String name) {
        Cocktail c = mapper.toDto(cocktailRepository.findById(name).get());
        List<DescriptionRelation> sortedSteps = c.getSteps().stream().sorted(Comparator.comparing(o -> o.getStep().getNumber())).collect(Collectors.toList());
        c.setSteps(sortedSteps);
        return Optional.ofNullable(c);
    }

    @Override
    public List<Cocktail> getCocktailByIngredientName(String name) {
        return cocktailRepository.findByIngrUsedInRelationsIngredientEntityName(name).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Cocktail> getCocktailByInstrumentName(String name) {
        return cocktailRepository.findByInstrUsedInRelationsInstrumentEntityName(name).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void saveCocktail(Cocktail cocktail) {
        cocktailRepository.save(mapper.toEntity(cocktail));
    }

    @Override
    public void deleteCocktailByName(String name) {
        cocktailRepository.deleteById(name);
    }
}
