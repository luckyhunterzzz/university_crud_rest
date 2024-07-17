package luckyhunter.university.service;

import luckyhunter.university.dto.TeacherDto;
import luckyhunter.university.entity.Teacher;
import luckyhunter.university.mapper.TeacherMapper;
import luckyhunter.university.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервисный класс для работы с данными преподавателей.
 */
public class TeacherService {
    private TeacherRepository teacherRepository;
    private TeacherMapper teacherMapper;

    /**
     * Конструктор класса TeacherService.
     *
     * @param teacherRepository Репозиторий для работы с данными преподавателей
     * @param teacherMapper     Маппер для преобразования объектов Teacher
     */
    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    /**
     * Возвращает DTO объект преподавателя по указанному идентификатору.
     *
     * @param id Идентификатор преподавателя
     * @return DTO объект преподавателя
     */
    public TeacherDto getTeacherById(int id) {
        Teacher teacher = teacherRepository.getTeacherById(id);
        return teacherMapper.teacherToTeacherDto(teacher);
    }

    /**
     * Возвращает список всех преподавателей в виде DTO объектов.
     *
     * @return Список DTO объектов преподавателей
     */
    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.getAllTeachers().stream()
                .map(teacherMapper::teacherToTeacherDto)
                .collect(Collectors.toList());
    }
}