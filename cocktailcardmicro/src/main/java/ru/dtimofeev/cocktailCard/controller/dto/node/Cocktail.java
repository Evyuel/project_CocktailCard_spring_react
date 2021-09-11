package ru.dtimofeev.cocktailCard.controller.dto.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dtimofeev.cocktailCard.controller.dto.relation.DescriptionRelation;
import ru.dtimofeev.cocktailCard.controller.dto.relation.IngUsedInRelation;
import ru.dtimofeev.cocktailCard.controller.dto.relation.InstrUsedInRelation;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cocktail {

    @JsonProperty("name")
    private String name;

    @JsonProperty("ingredients")
    private List<IngUsedInRelation> ingrUsedInRelations;

    @JsonProperty("instruments")
    private List<InstrUsedInRelation> instrUsedInRelations;

    @JsonProperty("steps")
    private List<DescriptionRelation> steps;

}
