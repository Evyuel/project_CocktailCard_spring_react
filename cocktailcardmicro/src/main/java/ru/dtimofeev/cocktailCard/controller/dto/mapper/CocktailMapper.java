package ru.dtimofeev.cocktailCard.controller.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.cocktailCard.controller.dto.node.Cocktail;
import ru.dtimofeev.cocktailCard.domain.node.CocktailEntity;

import java.util.stream.Collectors;

@Component
public class CocktailMapper {

    private final DescriptionRelationMapper descriptionRelationMapper;
    private final IngUsedInRelationMapper ingUsedInRelationMapper;
    private final InstrUsedInRelationMapper instrUsedInRelationMapper;

    @Autowired
    public CocktailMapper(DescriptionRelationMapper descriptionRelationMapper, IngUsedInRelationMapper ingUsedInRelationMapper, InstrUsedInRelationMapper instrUsedInRelationMapper) {
        this.descriptionRelationMapper = descriptionRelationMapper;
        this.ingUsedInRelationMapper = ingUsedInRelationMapper;
        this.instrUsedInRelationMapper = instrUsedInRelationMapper;
    }

    public Cocktail toDto(CocktailEntity entity) {
        Cocktail c = Cocktail.builder().name(entity.getName()).build();
        if (!entity.getIngrUsedInRelations().isEmpty()) {
            c.setIngrUsedInRelations(entity.getIngrUsedInRelations().stream().map(ingUsedInRelationMapper::toDto).collect(Collectors.toList()));
        }
        if (!entity.getInstrUsedInRelations().isEmpty()) {
            c.setInstrUsedInRelations(entity.getInstrUsedInRelations().stream().map(instrUsedInRelationMapper::toDto).collect(Collectors.toList()));
        }
        if (!entity.getSteps().isEmpty()) {
            c.setSteps(entity.getSteps().stream().map(descriptionRelationMapper::toDto).collect(Collectors.toList()));
        }
        return c;
    }

    public CocktailEntity toEntity(Cocktail dto) {
        CocktailEntity c = CocktailEntity.builder().name(dto.getName()).build();
        if (dto.getIngrUsedInRelations() != null) {
            c.setIngrUsedInRelations(dto.getIngrUsedInRelations().stream().map(ingUsedInRelationMapper::toEntity).collect(Collectors.toList()));
        }
        if (dto.getInstrUsedInRelations() != null) {
            c.setInstrUsedInRelations(dto.getInstrUsedInRelations().stream().map(instrUsedInRelationMapper::toEntity).collect(Collectors.toList()));
        }
        if (dto.getSteps() != null) {
            c.setSteps(dto.getSteps().stream().map(descriptionRelationMapper::toEntity).collect(Collectors.toList()));
        }
        return c;
    }
}
