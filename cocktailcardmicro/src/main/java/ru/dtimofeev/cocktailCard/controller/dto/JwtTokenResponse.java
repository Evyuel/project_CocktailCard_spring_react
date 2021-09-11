package ru.dtimofeev.cocktailCard.controller.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public final class JwtTokenResponse implements Serializable {

    private final String token;
}