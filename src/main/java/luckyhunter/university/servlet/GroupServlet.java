package luckyhunter.university.servlet;

import jakarta.servlet.ServletContext;
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

/**
 * Сервлет для обработки запросов, связанных с группами студентов.
 */
@WebServlet("/groups")
public class GroupServlet extends HttpServlet {
    private GroupService groupService;

    /**
     * Инициализация сервлета. Получает экземпляр GroupService из контекста сервлетов.
     *
     * @throws ServletException Исключение, возникающее при ошибках инициализации сервлета
     */
    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        this.groupService = (GroupService) ctx.getAttribute("groupService");
    }

    /**
     * Обрабатывает GET запросы для получения информации о группах или конкретной группе по идентификатору.
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
            GroupWithStudentsDto groupWithStudents = groupService.getGroupWithStudentsById(id);
            writer.write(groupWithStudents.toString());
        } else {
            List<GroupDto> groups = groupService.getAllGroups();
            writer.write(groups.toString());
        }
    }
}