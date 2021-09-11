package ru.dtimofeev.cocktailCard.domain.relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import ru.dtimofeev.cocktailCard.domain.node.InstrumentEntity;

@RelationshipProperties
@Data
@AllArgsConstructor
public final class InstrUsedInRelationEntity {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;

    @TargetNode
    @JsonProperty("instrument")
    private InstrumentEntity instrumentEntity;

    @JsonProperty("value")
    private int value;
    @JsonProperty("valueType")
    private String valueType;
}
