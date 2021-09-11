package ru.dtimofeev.cocktailCard.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ru.dtimofeev.cocktailCard.domain.AppUserDetails;


public interface AppUserDetailsRepository extends Neo4jRepository<AppUserDetails, String> {
}
