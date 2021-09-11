package ru.dtimofeev.cocktailCard.controller.dto.relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dtimofeev.cocktailCard.controller.dto.node.Instrument;

@Data
@AllArgsConstructor
public class InstrUsedInRelation {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("instrument")
    private Instrument instrument;

    @JsonProperty("value")
    private int value;

    @JsonProperty("valueType")
    private String valueType;
}
