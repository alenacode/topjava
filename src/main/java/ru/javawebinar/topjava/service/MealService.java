package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Service
public class MealService {
    private static final Logger log = LoggerFactory.getLogger(MealService.class);

    private final MealRepository repository;

    public MealService (MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal) {
        log.info("CREATE {}", meal);
        return repository.save(meal);
    }

    public void update(Meal meal) {
        log.info("UPDATE {}", meal);
        checkNotFoundWithId(repository.save(meal), authUserId());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), authUserId());
    }

    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id), authUserId());
    }

    public List<Meal> getAll() {
        /*List<Meal> meals = repository.getAll().stream()
                                              .filter(meal -> meal.getUserId().equals(authUserId()))
                                              .collect(Collectors.toList());
        log.info("GET_ALL {}", meals);*/
        return checkNotFoundWithId(repository.getAll(), authUserId());
    }
}