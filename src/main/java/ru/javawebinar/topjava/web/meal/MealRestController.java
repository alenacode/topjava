package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.util.DateTimeUtil.isBetweenHalfOpen;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private LocalDate dateFrom = LocalDate.MIN , dateTo = LocalDate.MAX;

    private LocalTime timeFrom = LocalTime.of(0, 0), timeTo = LocalTime.of(23, 59);

    private final MealService service;

    public MealRestController(@Qualifier("mealService") MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll(String ... dateTime) {
        log.info("getAll");
        Map<LocalDate, Integer> caloriesSumByDate = service.getAll().stream().collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        if(dateTime.length != 0) {
            timeFrom = dateTime[0] == null || dateTime[0].isEmpty() ? LocalTime.of(0, 0)   : LocalTime.parse(dateTime[0]);
            timeTo =   dateTime[1] == null || dateTime[1].isEmpty() ? LocalTime.of(23, 59) : LocalTime.parse(dateTime[1]);
            dateFrom = dateTime[2] == null || dateTime[2].isEmpty() ? LocalDate.MIN : LocalDate.parse(dateTime[2]);
            dateTo =   dateTime[3] == null || dateTime[3].isEmpty() ? LocalDate.MAX : LocalDate.parse(dateTime[3]);
        }

        return service.getAll()
            .stream()
            .filter(meal -> isBetweenHalfOpen(meal.getTime(), timeFrom, timeTo, meal.getDate(), dateFrom, dateTo))
            .map(meal -> new MealTo(meal.getId(),
                                    meal.getUserId(),
                                    meal.getDateTime(),
                                    meal.getDescription(),
                                    meal.getCalories(),
                                    caloriesSumByDate.get(meal.getDate()) > MealsUtil.DEFAULT_CALORIES_PER_DAY))
            .sorted(Comparator.comparing(MealTo::getDateTime))
            .collect(Collectors.toList());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Meal meal) {
        log.info("update {} with id={}", meal, meal.getId());
        assureIdConsistent(meal, meal.getId());
        service.update(meal);
    }
}