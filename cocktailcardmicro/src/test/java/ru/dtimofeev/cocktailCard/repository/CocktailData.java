package ru.dtimofeev.cocktailCard.repository;

import ru.dtimofeev.cocktailCard.domain.node.CocktailEntity;
import ru.dtimofeev.cocktailCard.domain.node.IngredientEntity;
import ru.dtimofeev.cocktailCard.domain.node.InstrumentEntity;
import ru.dtimofeev.cocktailCard.domain.node.StepEntity;
import ru.dtimofeev.cocktailCard.domain.relation.DescriptionRelationEntity;
import ru.dtimofeev.cocktailCard.domain.relation.IngUsedInRelationEntity;
import ru.dtimofeev.cocktailCard.domain.relation.InstrUsedInRelationEntity;

import java.util.List;

public class CocktailData {

    public static CocktailEntity MOHITO = CocktailEntity.builder()
            .name("Мохито")
            .ingrUsedInRelations(List.of(
                    new IngUsedInRelationEntity(null, new IngredientEntity("Белый ром"), 50, "мл"),
                    new IngUsedInRelationEntity(null, new IngredientEntity("Сахарный сироп"), 15, "мл"),
                    new IngUsedInRelationEntity(null, new IngredientEntity("Содовая"), 100, "мл"),
                    new IngUsedInRelationEntity(null, new IngredientEntity("Лайм"), 80, "г"),
                    new IngUsedInRelationEntity(null, new IngredientEntity("Мята"), 3, "г"),
                    new IngUsedInRelationEntity(null, new IngredientEntity("Дробленый лёд"), 200, "г")
            ))
            .instrUsedInRelations(List.of(
                    new InstrUsedInRelationEntity(null, new InstrumentEntity("Хайбол"), 1, "шт"),
                    new InstrUsedInRelationEntity(null, new InstrumentEntity("Мадлер"), 1, "шт"),
                    new InstrUsedInRelationEntity(null, new InstrumentEntity("Джиггер"), 1, "шт"),
                    new InstrUsedInRelationEntity(null, new InstrumentEntity("Коктейльная ложка"), 1, "шт"),
                    new InstrUsedInRelationEntity(null, new InstrumentEntity("Трубочки"), 2, "шт")
            ))
            .steps(List.of(
                    new DescriptionRelationEntity(null, new StepEntity(1), "Положи в хайбол лайм 3 дольки и подави мадлером"),
                    new DescriptionRelationEntity(null, new StepEntity(2), "Возьми мяту 10 листиков в одну руку и хлопни по ним другой рукой"),
                    new DescriptionRelationEntity(null, new StepEntity(3), "Положи мяту в хайбол"),
                    new DescriptionRelationEntity(null, new StepEntity(4), "Наполни бокал дробленым льдом доверху"),
                    new DescriptionRelationEntity(null, new StepEntity(5), "Добавь сахарный сироп 15 мл и белый ром 50 мл"),
                    new DescriptionRelationEntity(null, new StepEntity(6), "Долей содовую доверху и аккуратно размешай коктейльной ложкой"),
                    new DescriptionRelationEntity(null, new StepEntity(7), "Досыпь немного дробленого льда"),
                    new DescriptionRelationEntity(null, new StepEntity(8), "Укрась веточкой мяты и долькой лайма")
            ))
            .build();

    public static CocktailEntity APEROL = CocktailEntity.builder()
            .name("Апероль шприц")
            .ingrUsedInRelations(List.of(
                    new IngUsedInRelationEntity(null, new IngredientEntity("Апероль Aperol"), 100, "мл"),
                    new IngUsedInRelationEntity(null, new IngredientEntity("Просекко"), 100, "мл"),
                    new IngUsedInRelationEntity(null, new IngredientEntity("Содовая"), 20, "мл"),
                    new IngUsedInRelationEntity(null, new IngredientEntity("Апельсин"), 40, "г"),
                    new IngUsedInRelationEntity(null, new IngredientEntity("Лед в кубиках"), 60, "г")
            ))
            .instrUsedInRelations(List.of(
                    new InstrUsedInRelationEntity(null, new InstrumentEntity("Бокал для вина"), 1, "шт"),
                    new InstrUsedInRelationEntity(null, new InstrumentEntity("Джиггер"), 1, "шт"),
                    new InstrUsedInRelationEntity(null, new InstrumentEntity("Коктейльная ложка"), 1, "шт"),
                    new InstrUsedInRelationEntity(null, new InstrumentEntity("Трубочки"), 2, "шт")
            ))
            .steps(List.of(
                    new DescriptionRelationEntity(null, new StepEntity(1), "Наполни бокал для вина льдом"),
                    new DescriptionRelationEntity(null, new StepEntity(2), "Налей в бокал просекко 100 мл и апероль 100 мл"),
                    new DescriptionRelationEntity(null, new StepEntity(3), "Добавь сплэш содовой и размешай коктейльной ложкой"),
                    new DescriptionRelationEntity(null, new StepEntity(4), "Укрась долькой апельсина")
            ))
            .build();
}
