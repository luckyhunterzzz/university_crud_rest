package luckyhunter.university.mapper;

import luckyhunter.university.dto.StudentDto;
import luckyhunter.university.dto.StudentModDto;
import luckyhunter.university.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto studentToStudentDto(Student student);

    StudentModDto studentToStudentModDto(Student student);

    Student studentModDtoToStudent(StudentModDto studentModDto);


}