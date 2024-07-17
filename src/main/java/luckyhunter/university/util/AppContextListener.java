package luckyhunter.university.util;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import luckyhunter.university.mapper.*;
import luckyhunter.university.repository.*;
import luckyhunter.university.service.*;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        // Initialize repositories
        StudentRepository studentRepository = new StudentRepository();
        GroupRepository groupRepository = new GroupRepository();
        ScheduleRepository scheduleRepository = new ScheduleRepository();
        SubjectRepository subjectRepository = new SubjectRepository();
        TeacherRepository teacherRepository = new TeacherRepository();


        // Initialize mappers
        StudentMapper studentMapper = StudentMapper.INSTANCE;
        GroupMapper groupMapper = GroupMapper.INSTANCE;
        ScheduleMapper scheduleMapper = ScheduleMapper.INSTANCE;
        SubjectMapper subjectMapper = SubjectMapper.INSTANCE;
        TeacherMapper teacherMapper = TeacherMapper.INSTANCE;

        // Initialize services
        StudentService studentService = new StudentService(studentRepository, studentMapper);
        GroupService groupService = new GroupService(groupRepository, groupMapper, studentMapper);
        ScheduleService scheduleService = new ScheduleService(scheduleRepository, scheduleMapper);
        SubjectService subjectService = new SubjectService(subjectRepository, subjectMapper);
        TeacherService teacherService = new TeacherService(teacherRepository, teacherMapper);

        // Add services to context
        ctx.setAttribute("studentService", studentService);
        ctx.setAttribute("groupService", groupService);
        ctx.setAttribute("scheduleService", scheduleService);
        ctx.setAttribute("subjectService", subjectService);
        ctx.setAttribute("teacherService", teacherService);

        ServletContextListener.super.contextInitialized(sce);
    }
}