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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        List<MealTo> mealsTo = null;

        switch (action) {
            case "edit":
                //mealsTo = edit(request, response);
                //request.setAttribute("meals", mealsTo);
                break;
            case "delete":
                //delete(request, response);
                break;
            case "add":
                Meal meal = new Meal(LocalDateTime.of(2020, Month.JANUARY, 1, 10, 0), request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
                MealsUtil.meals.add(meal);
                mealsTo = MealsUtil.filteredByStreams(MealsUtil.meals, LocalTime.of(0, 0), LocalTime.of(23, 5), 2000);
                request.setAttribute("meals", mealsTo);
                break;
            case "meals":
                mealsTo = MealsUtil.filteredByStreams(MealsUtil.meals, LocalTime.of(0, 0), LocalTime.of(23, 5), 2000);
                request.setAttribute("meals", mealsTo);
                break;
        }
        request.setAttribute("meals", mealsTo);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
