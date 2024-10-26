package luckyhunter.university.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import luckyhunter.university.dto.ScheduleDto;
import luckyhunter.university.service.GroupService;
import luckyhunter.university.service.ScheduleService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервлет для обработки запросов, связанных с расписанием занятий.
 */
@WebServlet("/schedules")
public class ScheduleServlet extends HttpServlet {
    private ScheduleService scheduleService;

    /**
     * Инициализация сервлета. Получает экземпляр ScheduleService из контекста сервлетов.
     *
     * @throws ServletException Исключение, возникающее при ошибках инициализации сервлета
     */
    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        this.scheduleService = (ScheduleService) ctx.getAttribute("scheduleService");
    }

    /**
     * Обрабатывает GET запросы для получения всех расписаний занятий.
     *
     * @param req  HTTP запрос
     * @param resp HTTP ответ
     * @throws ServletException Исключение, возникающее при ошибках сервлета
     * @throws IOException      Исключение, возникающее при ошибках ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        List<ScheduleDto> schedules = scheduleService.getAllSchedules();

        PrintWriter writer = resp.getWriter();
        writer.write(schedules.toString());
    }
}