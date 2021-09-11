package ru.dtimofeev.cocktailCard.controller.dto.mapper;

import org.springframework.stereotype.Component;
import ru.dtimofeev.cocktailCard.controller.dto.node.Step;
import ru.dtimofeev.cocktailCard.controller.dto.relation.DescriptionRelation;
import ru.dtimofeev.cocktailCard.domain.node.StepEntity;
import ru.dtimofeev.cocktailCard.domain.relation.DescriptionRelationEntity;

@Component
public class DescriptionRelationMapper {

    public DescriptionRelation toDto(DescriptionRelationEntity entity) {
        return new DescriptionRelation(entity.getId(), new Step(entity.getStepEntity().getNumber()), entity.getValue());
    }

    public DescriptionRelationEntity toEntity(DescriptionRelation dto) {
        return new DescriptionRelationEntity(dto.getId(), new StepEntity(dto.getStep().getNumber()), dto.getValue());
    }
}
