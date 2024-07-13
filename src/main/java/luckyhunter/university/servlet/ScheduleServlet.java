package luckyhunter.university.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import luckyhunter.university.dto.ScheduleDto;
import luckyhunter.university.service.ScheduleService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/schedules")
public class ScheduleServlet extends HttpServlet {
    private final ScheduleService scheduleService = new ScheduleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        List<ScheduleDto> schedules = scheduleService.getAllSchedules();

        PrintWriter writer = resp.getWriter();
        writer.write(schedules.toString());
    }
}