package luckyhunter.university.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import luckyhunter.university.dto.SubjectDto;
import luckyhunter.university.service.ScheduleService;
import luckyhunter.university.service.SubjectService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервлет для обработки запросов, связанных с предметами.
 */
@WebServlet("/subjects")
public class SubjectServlet extends HttpServlet {
    private SubjectService subjectService;


    /**
     * Инициализация сервлета. Получает экземпляр SubjectService из контекста сервлетов.
     *
     * @throws ServletException Исключение, возникающее при ошибках инициализации сервлета
     */
    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        this.subjectService = (SubjectService) ctx.getAttribute("subjectService");
    }

    /**
     * Обрабатывает GET запросы для получения всех предметов.
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

        List<SubjectDto> subjects = subjectService.getAllSubjects();
        PrintWriter writer = resp.getWriter();

        writer.write(subjects.toString());
    }
}
