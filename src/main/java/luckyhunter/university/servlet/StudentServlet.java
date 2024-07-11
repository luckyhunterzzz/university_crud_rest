package luckyhunter.university.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import luckyhunter.university.dto.StudentDto;
import luckyhunter.university.service.StudentService;
import luckyhunter.university.validator.StudentValidator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();


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
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"" + studentValidator.toString() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            studentService.deleteStudentById(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            resp.getWriter().write("{\"message\":\"Student deleted successfully\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"ID parameter is required\"}");
        }
    }


}
