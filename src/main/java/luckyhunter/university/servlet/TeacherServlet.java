package luckyhunter.university.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import luckyhunter.university.dto.TeacherDto;
import luckyhunter.university.service.TeacherService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/teachers")
public class TeacherServlet extends HttpServlet {
    private final TeacherService teacherService = new TeacherService();

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