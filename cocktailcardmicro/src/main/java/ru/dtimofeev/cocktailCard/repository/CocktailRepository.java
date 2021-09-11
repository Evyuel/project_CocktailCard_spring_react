package ru.dtimofeev.cocktailCard.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ru.dtimofeev.cocktailCard.domain.node.CocktailEntity;

import java.util.List;

public interface CocktailRepository extends Neo4jRepository<CocktailEntity, String> {

    List<CocktailEntity> findByNameStartingWithIgnoreCase(String name);

    List<CocktailEntity> findByIngrUsedInRelationsIngredientEntityName(String name);

    List<CocktailEntity> findByInstrUsedInRelationsInstrumentEntityName(String name);

}
