package ru.dtimofeev.cocktailCard.controller.dto.mapper;

import org.springframework.stereotype.Component;
import ru.dtimofeev.cocktailCard.controller.dto.node.Instrument;
import ru.dtimofeev.cocktailCard.controller.dto.relation.InstrUsedInRelation;
import ru.dtimofeev.cocktailCard.domain.node.InstrumentEntity;
import ru.dtimofeev.cocktailCard.domain.relation.InstrUsedInRelationEntity;

@Component
public class InstrUsedInRelationMapper {

    public InstrUsedInRelation toDto(InstrUsedInRelationEntity entity) {
        return new InstrUsedInRelation(entity.getId(), new Instrument(entity.getInstrumentEntity().getName()), entity.getValue(), entity.getValueType());
    }

    public InstrUsedInRelationEntity toEntity(InstrUsedInRelation dto) {
        return new InstrUsedInRelationEntity(dto.getId(), new InstrumentEntity(dto.getInstrument().getName()), dto.getValue(), dto.getValueType());
    }
}
