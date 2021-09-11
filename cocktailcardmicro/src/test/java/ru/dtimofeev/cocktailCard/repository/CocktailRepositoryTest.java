package ru.dtimofeev.cocktailCard.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.dtimofeev.cocktailCard.domain.node.CocktailEntity;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dtimofeev.cocktailCard.repository.CocktailData.APEROL;
import static ru.dtimofeev.cocktailCard.repository.CocktailData.MOHITO;

@DataNeo4jTest
@Transactional(propagation = Propagation.NEVER)
@Testcontainers
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ActiveProfiles("test")
@DisplayName("Класс CocktailRepository должен")
class CocktailRepositoryTest {

    @Container
    static Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:4.0")
            .withStartupTimeout(Duration.ofMinutes(5));
    @Autowired
    private CocktailRepository cocktailRepository;

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", neo4jContainer::getAdminPassword);
    }

    @Test
    @DisplayName("корректно сохранять коктейль")
    void shouldCorrectSave() {
        CocktailEntity c = cocktailRepository.save(MOHITO);
        assertThat(c)
                .usingRecursiveComparison()
                .isEqualTo(MOHITO);
    }

    @Test
    @DisplayName("корректно находить коктейли, начинающихся на заданное название, игнорируя регистр")
    void shouldCorrectFindCocktailsStartWithIgnoreCase() {
        cocktailRepository.saveAll(List.of(MOHITO, APEROL));
        List<CocktailEntity> c1_list = cocktailRepository.findByNameStartingWithIgnoreCase("мОхИ");
        assertThat(c1_list.size()).isEqualTo(1);

        CocktailEntity c1 = c1_list.get(0);
        assertThat(c1.getName()).isEqualTo(MOHITO.getName());
        assertThat(c1.getIngrUsedInRelations()).containsExactlyInAnyOrderElementsOf(MOHITO.getIngrUsedInRelations());
        assertThat(c1.getInstrUsedInRelations()).containsExactlyInAnyOrderElementsOf(MOHITO.getInstrUsedInRelations());
        assertThat(c1.getSteps()).containsExactlyInAnyOrderElementsOf(MOHITO.getSteps());

        List<CocktailEntity> c2_list = cocktailRepository.findByNameStartingWithIgnoreCase("аПеР");
        assertThat(c2_list.size()).isEqualTo(1);

        CocktailEntity c2 = c2_list.get(0);
        assertThat(c2.getName()).isEqualTo(APEROL.getName());
        assertThat(c2.getIngrUsedInRelations()).containsExactlyInAnyOrderElementsOf(APEROL.getIngrUsedInRelations());
        assertThat(c2.getInstrUsedInRelations()).containsExactlyInAnyOrderElementsOf(APEROL.getInstrUsedInRelations());
        assertThat(c2.getSteps()).containsExactlyInAnyOrderElementsOf(APEROL.getSteps());
    }

    @Test
    @DisplayName("корректно возвращать коктейли по их ингредиентам")
    void shouldCorrectReturnCocktailsByIngredientName() {
        cocktailRepository.saveAll(List.of(MOHITO, APEROL));
        List<CocktailEntity> c = cocktailRepository.findByIngrUsedInRelationsIngredientEntityName("Содовая");
        assertThat(c.size()).isEqualTo(2);
        assertThat(c.stream().map(CocktailEntity::getName).collect(Collectors.toList()))
                .containsExactlyInAnyOrderElementsOf(List.of(MOHITO.getName(), APEROL.getName()));

        c = cocktailRepository.findByIngrUsedInRelationsIngredientEntityName("Просекко");
        assertThat(c.size()).isEqualTo(1);
        assertThat(c.stream().map(CocktailEntity::getName).collect(Collectors.toList()))
                .containsExactlyInAnyOrderElementsOf(List.of(APEROL.getName()));
    }

    @Test
    @DisplayName("корректно возвращать коктейли по их инструментам")
    void shouldCorrectReturnCocktailsByInstrumentName() {
        cocktailRepository.saveAll(List.of(MOHITO, APEROL));
        List<CocktailEntity> c = cocktailRepository.findByInstrUsedInRelationsInstrumentEntityName("Трубочки");
        assertThat(c.size()).isEqualTo(2);
        assertThat(c.stream().map(CocktailEntity::getName).collect(Collectors.toList()))
                .containsExactlyInAnyOrderElementsOf(List.of(MOHITO.getName(), APEROL.getName()));

        c = cocktailRepository.findByInstrUsedInRelationsInstrumentEntityName("Хайбол");
        assertThat(c.size()).isEqualTo(1);
        assertThat(c.stream().map(CocktailEntity::getName).collect(Collectors.toList()))
                .containsExactlyInAnyOrderElementsOf(List.of(MOHITO.getName()));
    }

}