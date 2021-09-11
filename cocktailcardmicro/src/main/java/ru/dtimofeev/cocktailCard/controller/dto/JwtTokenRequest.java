package ru.dtimofeev.cocktailCard.controller.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public final class JwtTokenRequest implements Serializable {

    private String username;
    private String password;
}

