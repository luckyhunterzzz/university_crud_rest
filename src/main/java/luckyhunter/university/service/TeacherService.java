package luckyhunter.university.service;

import luckyhunter.university.dto.TeacherDto;
import luckyhunter.university.entity.Teacher;
import luckyhunter.university.mapper.TeacherMapper;
import luckyhunter.university.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherService {
    private TeacherRepository teacherRepository;
    private TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public TeacherDto getTeacherById(int id) {
        Teacher teacher = teacherRepository.getTeacherById(id);
        return teacherMapper.teacherToTeacherDto(teacher);
    }

    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.getAllTeachers().stream()
                .map(teacherMapper::teacherToTeacherDto)
                .collect(Collectors.toList());
    }
}