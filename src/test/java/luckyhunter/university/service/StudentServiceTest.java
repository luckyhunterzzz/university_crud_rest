package luckyhunter.university.service;

import luckyhunter.university.dto.StudentDto;
import luckyhunter.university.entity.Student;
import luckyhunter.university.mapper.StudentMapper;
import luckyhunter.university.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;
    @Mock
    StudentMapper studentMapper;
    @InjectMocks
    StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStudentById() {
        int studentId = 1;
        Student student = new Student(studentId, "Алина", "Могунова", LocalDate.of(2007, 1, 31), "+79876541230");
        StudentDto expectedStudentDto = new StudentDto("Алина", "Могунова", LocalDate.of(2007, 1, 31));


        when(studentRepository.getStudentById(studentId)).thenReturn(student);
        when(studentMapper.studentToStudentDto(student)).thenReturn(expectedStudentDto);

        StudentDto actualStudentDto = studentService.getStudentById(studentId);

        assertEquals(expectedStudentDto, actualStudentDto);

        verify(studentRepository, times(1)).getStudentById(studentId);
        verify(studentMapper, times(1)).studentToStudentDto(student);
    }
    
//    public StudentDto getStudentById(int id) {
//        Student student = studentRepository.getStudentById(id);
//        return studentMapper.studentToStudentDto(student);
//    }

    @Test
    void getAllStudents() {

    }

    @Test
    void getStudentsByFirstName() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void deleteStudentById() {
    }

    @Test
    void parseJson() {
    }
}