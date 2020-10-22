package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    // Сделать тесты на чужую еду (delete, get, update) с тем, чтобы получить NotFoundException и duplicateDateTimeCreate, аналогичный duplicateMailCreate.
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private JdbcMealRepository repository;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_USER.getId(), USER_ID);
        assertMatch(meal, MEAL_USER);
    }

    @Test
    public void delete() {
        service.delete(MEAL_USER.getId(), USER_ID);
        assertNull(repository.get(MEAL_USER.getId(), USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> all = service.getBetweenInclusive(LocalDate.of(2020, Month.JANUARY, 31), LocalDate.of(2020, Month.JANUARY, 31), USER_ID);
        assertMatch(all, MEAL_ADMIN, MEAL_USER);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEAL_ADMIN, MEAL_USER);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(updated.getId(), USER_ID), updated);
    }

    @Test
    public void create() {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(ldt1, "DUBLICATE description", 400), USER_ID));
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_USER.getId(), ADMIN_ID));
    }

    @Test
    public void updateNotFound() throws Exception {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(updated.getId(), ADMIN_ID));
    }

    @Test
    public void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }
}