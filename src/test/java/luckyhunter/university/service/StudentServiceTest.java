package luckyhunter.university.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import luckyhunter.university.dto.StudentDto;
import luckyhunter.university.dto.StudentModDto;
import luckyhunter.university.entity.Student;
import luckyhunter.university.mapper.StudentMapper;
import luckyhunter.university.repository.StudentRepository;
import luckyhunter.university.validator.StudentValidator;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllStudents() {
        Student student = mock(Student.class);
        when(studentRepository.getAllStudents()).thenReturn(Collections.singletonList(student));
        when(studentMapper.studentToStudentDto(student)).thenReturn(new StudentDto());

        List<StudentDto> result = studentService.getAllStudents();

        verify(studentRepository).getAllStudents();
        assertEquals(1, result.size());
    }

    @Test
    public void testGetStudentById() {
        Student student = mock(Student.class);
        when(studentRepository.getStudentById(1)).thenReturn(student);
        when(studentMapper.studentToStudentDto(student)).thenReturn(new StudentDto());

        StudentDto result = studentService.getStudentById(1);

        verify(studentRepository).getStudentById(1);
        assertEquals(new StudentDto(), result);
    }

    @Test
    public void testGetStudentsByFirstName() {
        Student student = mock(Student.class);
        when(studentRepository.getStudentsByFirstName("Екатерина")).thenReturn(Collections.singletonList(student));
        when(studentMapper.studentToStudentDto(student)).thenReturn(new StudentDto());

        List<StudentDto> result = studentService.getStudentsByFirstName("Екатерина");

        verify(studentRepository).getStudentsByFirstName("Екатерина");
        assertEquals(1, result.size());
    }

    @Test
    public void testDeleteStudentById() {
        studentService.deleteStudentById(1);

        verify(studentRepository).deleteStudentById(1);
    }
}