package ru.dtimofeev.cocktailCard.controller.dto.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instrument {

    @JsonProperty("name")
    private String name;
}
