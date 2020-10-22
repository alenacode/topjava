package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    // 5.1 Сделать тестовые данные MealTestData (точно такие же, как вставляем в populateDB.sql).
    public static final int NOT_FOUND = 100000;         // id of non existing meal
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static LocalDateTime ldt1 = LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0, 0);
    public static LocalDateTime ldt2 = LocalDateTime.of(2020, Month.JANUARY, 31, 20, 1, 0);
    public static final Meal MEAL_USER = new Meal(1, ldt1, "description_user", 300);
    public static final Meal MEAL_ADMIN = new Meal(2, ldt2, "description_admin", 500);

    public static Meal getNew() {
        return new Meal(ldt2.plusMinutes(1), "NEW Завтрак", 800);
    }

    public static Meal getUpdated() {
        Meal updated = MEAL_USER;
        updated.setDescription("UPDATED description_user");
        updated.setCalories(444);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).containsExactlyElementsOf(expected);
    }
}
