package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealServlet extends HttpServlet {
    public List<Meal> meals = new ArrayList(Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    ));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        List<MealTo> mealsLocal = null;

        switch (action) {
            case "edit":
                System.out.println("EEEEEEEEEDIT");
                Meal meal = new Meal(LocalDateTime.parse(request.getParameter("datetime")), request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
                request.setAttribute("meal", meal);
                break;
            case "delete":
                //delete(request, response);
                break;
            case "add":
                System.out.println("AAAAAAAAAADD");
                meal = new Meal(LocalDateTime.parse(request.getParameter("datetime")), request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
                meals.add(meal);
                mealsLocal = MealsUtil.filteredByStreams(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
                break;
            case "meals":
                System.out.println("MMMMMMMMMMEALS");
                mealsLocal = MealsUtil.filteredByStreams(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
                request.setAttribute("meals", mealsLocal);
                break;
            default:
                System.out.println("DDDDDDDDEFAULT");
                break;
        }

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  РЕАЛИЗОВАТЬ РАЗБОР ПО ID : ЕСЛИ УЖЕ ЕСТЬ ТАКОЙ ID ТО ИЗМЕНЯЕМ ДАННЫЕ, ЕСЛИ НЕТ СОЗДАЕМ
        request.setCharacterEncoding("UTF-8");
        List<MealTo> mealsLocal;
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("datetime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        meals.add(meal);
        mealsLocal = MealsUtil.filteredByStreams(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);

        request.setAttribute("meals", mealsLocal);
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", "meals?action=meals");
        response.flushBuffer();

        //  request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
