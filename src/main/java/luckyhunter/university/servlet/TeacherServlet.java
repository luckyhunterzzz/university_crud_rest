package luckyhunter.university.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import luckyhunter.university.dto.TeacherDto;
import luckyhunter.university.service.SubjectService;
import luckyhunter.university.service.TeacherService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервлет для обработки запросов, связанных с преподавателями.
 */
@WebServlet("/teachers")
public class TeacherServlet extends HttpServlet {
    private TeacherService teacherService;

    /**
     * Инициализация сервлета. Получает экземпляр TeacherService из контекста сервлетов.
     *
     * @throws ServletException Исключение, возникающее при ошибках инициализации сервлета
     */
    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        this.teacherService = (TeacherService) ctx.getAttribute("teacherService");
    }

    /**
     * Обрабатывает GET запросы для получения информации о преподавателях или конкретном преподавателе по идентификатору.
     *
     * @param req  HTTP запрос
     * @param resp HTTP ответ
     * @throws ServletException Исключение, возникающее при ошибках сервлета
     * @throws IOException      Исключение, возникающее при ошибках ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            TeacherDto teacher = teacherService.getTeacherById(id);
            writer.write(teacher.toString());
        } else {
            List<TeacherDto> teachers = teacherService.getAllTeachers();
            writer.write(teachers.toString());
        }
    }
}