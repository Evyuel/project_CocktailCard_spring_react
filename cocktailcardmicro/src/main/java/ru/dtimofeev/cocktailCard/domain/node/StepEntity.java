package ru.dtimofeev.cocktailCard.domain.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node(value = "Step")
public final class StepEntity {
    @Id
    @JsonProperty("number")
    private int number;
}
