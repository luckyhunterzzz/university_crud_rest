package luckyhunter.university.service;

import luckyhunter.university.dto.TeacherDto;
import luckyhunter.university.entity.Teacher;
import luckyhunter.university.mapper.TeacherMapper;
import luckyhunter.university.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private TeacherMapper teacherMapper;

    @InjectMocks
    private TeacherService teacherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTeacherById() {
        Teacher teacher = mock(Teacher.class);
        when(teacherRepository.getTeacherById(1)).thenReturn(teacher);
        when(teacherMapper.teacherToTeacherDto(teacher)).thenReturn(new TeacherDto());

        TeacherDto result = teacherService.getTeacherById(1);

        verify(teacherRepository).getTeacherById(1);
        assertEquals(new TeacherDto(), result);
    }

    @Test
    public void testGetAllTeachers() {
        Teacher teacher = mock(Teacher.class);
        when(teacherRepository.getAllTeachers()).thenReturn(Collections.singletonList(teacher));
        when(teacherMapper.teacherToTeacherDto(teacher)).thenReturn(new TeacherDto());

        List<TeacherDto> result = teacherService.getAllTeachers();

        verify(teacherRepository).getAllTeachers();
        assertEquals(1, result.size());
    }
}