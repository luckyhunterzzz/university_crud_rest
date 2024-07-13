package luckyhunter.university.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import luckyhunter.university.dto.GroupDto;
import luckyhunter.university.dto.GroupWithStudentsDto;
import luckyhunter.university.service.GroupService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/groups")
public class GroupServlet extends HttpServlet {
    private final GroupService groupService = new GroupService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            GroupWithStudentsDto groupWithStudents = groupService.getGroupWithStudentsById(id);
            writer.write(groupWithStudents.toString());
        } else {
            List<GroupDto> groups = groupService.getAllGroups();
            writer.write(groups.toString());
        }
    }
}