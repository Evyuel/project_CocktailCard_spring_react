package ru.dtimofeev.cocktailCard.controller.dto.relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dtimofeev.cocktailCard.controller.dto.node.Step;

@Data
@AllArgsConstructor
public class DescriptionRelation {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("step")
    private Step step;

    @JsonProperty("value")
    private String value;
}
