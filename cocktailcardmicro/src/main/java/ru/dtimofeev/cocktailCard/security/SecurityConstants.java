package ru.dtimofeev.cocktailCard.security;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 900_000;
    public static final String SECRET = "mySecret";
    public static final String HTTP_TOKEN_AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ROLES_CLAIM = "roles";
}
