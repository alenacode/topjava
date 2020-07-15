package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    private static Integer id = 1;

    public static void setId(Integer id) {
        SecurityUtil.id = id;
    }

    public static int authUserId() {
        return id;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}