package luckyhunter.university.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import luckyhunter.university.dto.StudentDto;
import luckyhunter.university.service.StudentService;
import luckyhunter.university.validator.StudentValidator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервлет для обработки запросов, связанных со студентами.
 */
@Slf4j
@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentService studentService;

    /**
     * Инициализация сервлета. Получает экземпляр StudentService из контекста сервлетов.
     *
     * @throws ServletException Исключение, возникающее при ошибках инициализации сервлета
     */
    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        this.studentService = (StudentService) ctx.getAttribute("studentService");
    }

    /**
     * Обрабатывает GET запросы для получения информации о студентах или конкретном студенте по идентификатору или имени.
     *
     * @param req  HTTP запрос
     * @param resp HTTP ответ
     * @throws ServletException Исключение, возникающее при ошибках сервлета
     * @throws IOException      Исключение, возникающее при ошибках ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String firstNameParam = req.getParameter("firstName");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            StudentDto student = studentService.getStudentById(id);
            writer.write(student.toString());
        } else if (firstNameParam != null) {
            List<StudentDto> students = studentService.getStudentsByFirstName(firstNameParam);
            writer.write(students.toString());
        } else {
            List<StudentDto> students = studentService.getAllStudents();
            writer.write(students.toString());
        }
    }

    /**
     * Обрабатывает POST запросы для добавления нового студента на основе переданных данных JSON.
     *
     * @param req  HTTP запрос
     * @param resp HTTP ответ
     * @throws ServletException Исключение, возникающее при ошибках сервлета
     * @throws IOException      Исключение, возникающее при ошибках ввода/вывода
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type", "application/json");

        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        StudentValidator studentValidator = studentService.parseJson(body);
        if (studentValidator.getErrors().isEmpty()) {
            try {
                studentService.addStudent(studentValidator.getStudentModDto());
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"message\":\"Student created successfully\"}");
            } catch (IllegalArgumentException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
                log.error("Got IllegalArgumentException "+ e.getMessage());
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"" + studentValidator.toString() + "\"}");
        }
    }

    /**
     * Обрабатывает DELETE запросы для удаления студента по указанному идентификатору.
     *
     * @param req  HTTP запрос
     * @param resp HTTP ответ
     * @throws ServletException Исключение, возникающее при ошибках сервлета
     * @throws IOException      Исключение, возникающее при ошибках ввода/вывода
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                studentService.deleteStudentById(id);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"message\":\"Student deleted successfully\"}");
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Invalid ID parameter\"}");
                log.error("Got NumberFormatException "+ e.getMessage());
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"ID parameter is required\"}");
        }
    }
}
