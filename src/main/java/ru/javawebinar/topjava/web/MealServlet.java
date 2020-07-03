package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryUserMealRepository;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MealServlet extends HttpServlet {
    private UserMealRepository repository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryUserMealRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        Meal meal;

        switch (action) {
            case "create":
            case "update":
                System.out.println("CREATE OR EDIT");
                meal = action.equals("create") ? new Meal(LocalDateTime.now(), "", 0) :
                                                 repository.get(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                return;
            case "read":
                System.out.println("MEALS");
                request.setAttribute("meals", MealsUtil.filteredByStreams(repository.getAll(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                return;
            case "delete":
                System.out.println("DELETE");
                repository.delete(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("meals?action=read");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, NullPointerException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        Meal meal = new Meal(id == null ? null : Integer.valueOf(id),
                            LocalDateTime.parse(request.getParameter("datetime")),
                            request.getParameter("description"),
                            Integer.parseInt(request.getParameter("calories")));
        repository.save(meal);
        response.sendRedirect("meals?action=read");
    }
}
