package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private List<Meal> meals = MealsUtil.meals;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        try{
            switch (action) {
                case "/form":
                    edit(request, response);
                    break;
                case "/delete":
                    delete(request, response);
                    break;
                case "/add":
                    add(request, response);
                    break;
                default:
                    show(request, response);
                    System.out.println(request.getServletPath());
                    break;
            }
        } catch (Exception e) {
            log.debug("SQL_ERROR");
        }
    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealTo> mealsTo = MealsUtil.filteredByStreams(meals, LocalTime.of(0, 0), LocalTime.of(23, 5), 2000);
        request.setAttribute("meals", mealsTo);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Meal meal = new Meal(LocalDateTime.of(2020, Month.JANUARY, 1, 10, 0), request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
        meals.add(meal);
        List<MealTo> mealsTo = MealsUtil.filteredByStreams(meals, LocalTime.of(0, 0), LocalTime.of(23, 5), 2000);
        request.setAttribute("meals", mealsTo);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {

    }

    private void add(HttpServletRequest request, HttpServletResponse response) {

    }
}
