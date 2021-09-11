package ru.dtimofeev.cocktailCard.domain.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import ru.dtimofeev.cocktailCard.domain.relation.DescriptionRelationEntity;
import ru.dtimofeev.cocktailCard.domain.relation.IngUsedInRelationEntity;
import ru.dtimofeev.cocktailCard.domain.relation.InstrUsedInRelationEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node(value = "Cocktail")
public final class CocktailEntity {
    @Id
    @JsonProperty("name")
    private String name;

    @JsonProperty("ingredients")
    @Relationship(type = "INGR_USED_IN", direction = Relationship.Direction.INCOMING)
    private List<IngUsedInRelationEntity> ingrUsedInRelations;

    @JsonProperty("instruments")
    @Relationship(type = "INSTR_USED_IN", direction = Relationship.Direction.INCOMING)
    private List<InstrUsedInRelationEntity> instrUsedInRelations;

    @JsonProperty("steps")
    @Relationship(type = "DESCRIPTION", direction = Relationship.Direction.OUTGOING)
    private List<DescriptionRelationEntity> steps;
}
