package ru.dtimofeev.cocktailCard.controller.dto.mapper;

import org.springframework.stereotype.Component;
import ru.dtimofeev.cocktailCard.controller.dto.node.Ingredient;
import ru.dtimofeev.cocktailCard.controller.dto.relation.IngUsedInRelation;
import ru.dtimofeev.cocktailCard.domain.node.IngredientEntity;
import ru.dtimofeev.cocktailCard.domain.relation.IngUsedInRelationEntity;

@Component
public class IngUsedInRelationMapper {

    public IngUsedInRelation toDto(IngUsedInRelationEntity entity) {
        return new IngUsedInRelation(entity.getId(), new Ingredient(entity.getIngredientEntity().getName()), entity.getValue(), entity.getValueType());
    }

    public IngUsedInRelationEntity toEntity(IngUsedInRelation dto) {
        return new IngUsedInRelationEntity(dto.getId(), new IngredientEntity(dto.getIngredient().getName()), dto.getValue(), dto.getValueType());
    }
}
