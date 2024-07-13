package luckyhunter.university.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import luckyhunter.university.dto.SubjectDto;
import luckyhunter.university.service.SubjectService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/subjects")
public class SubjectServlet extends HttpServlet {
    private final SubjectService subjectService = new SubjectService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        List<SubjectDto> subjects = subjectService.getAllSubjects();
        PrintWriter writer = resp.getWriter();

        writer.write(subjects.toString());
    }
}
