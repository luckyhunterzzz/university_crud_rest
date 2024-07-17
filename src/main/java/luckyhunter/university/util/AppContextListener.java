package luckyhunter.university.util;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import luckyhunter.university.mapper.*;
import luckyhunter.university.repository.*;
import luckyhunter.university.service.*;

/**
 * Класс контекста приложения для инициализации сервисов и добавления их в контекст сервлетов.
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    /**
     * Инициализация контекста приложения. Создает экземпляры репозиториев, мапперов и сервисов,
     * а затем добавляет сервисы в контекст сервлетов.
     *
     * @param sce Событие контекста сервлетов, инициирующее инициализацию
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        StudentRepository studentRepository = new StudentRepository();
        GroupRepository groupRepository = new GroupRepository();
        ScheduleRepository scheduleRepository = new ScheduleRepository();
        SubjectRepository subjectRepository = new SubjectRepository();
        TeacherRepository teacherRepository = new TeacherRepository();


        StudentMapper studentMapper = StudentMapper.INSTANCE;
        GroupMapper groupMapper = GroupMapper.INSTANCE;
        ScheduleMapper scheduleMapper = ScheduleMapper.INSTANCE;
        SubjectMapper subjectMapper = SubjectMapper.INSTANCE;
        TeacherMapper teacherMapper = TeacherMapper.INSTANCE;

        StudentService studentService = new StudentService(studentRepository, studentMapper);
        GroupService groupService = new GroupService(groupRepository, groupMapper, studentMapper);
        ScheduleService scheduleService = new ScheduleService(scheduleRepository, scheduleMapper);
        SubjectService subjectService = new SubjectService(subjectRepository, subjectMapper);
        TeacherService teacherService = new TeacherService(teacherRepository, teacherMapper);

        ctx.setAttribute("studentService", studentService);
        ctx.setAttribute("groupService", groupService);
        ctx.setAttribute("scheduleService", scheduleService);
        ctx.setAttribute("subjectService", subjectService);
        ctx.setAttribute("teacherService", teacherService);

        ServletContextListener.super.contextInitialized(sce);
    }
}