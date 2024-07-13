package luckyhunter.university.mapper;

import luckyhunter.university.dto.TeacherDto;
import luckyhunter.university.dto.TeacherModDto;
import luckyhunter.university.entity.Subject;
import luckyhunter.university.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "subjectsToSubjectNames")
    TeacherDto teacherToTeacherDto(Teacher teacher);

    @Named("subjectsToSubjectNames")
    default List<String> subjectsToSubjectNames(List<Subject> subjects) {
        return subjects.stream().map(Subject::getSubjectName).collect(Collectors.toList());
    }

    Teacher teacherModDtoToTeacher(TeacherModDto teacherModDto);
}