package ru.dtimofeev.cocktailCard.controller.dto.relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dtimofeev.cocktailCard.controller.dto.node.Ingredient;

@Data
@AllArgsConstructor
public class IngUsedInRelation {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("ingredient")
    private Ingredient ingredient;

    @JsonProperty("value")
    private int value;

    @JsonProperty("valueType")
    private String valueType;
}
