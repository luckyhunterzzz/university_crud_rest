package luckyhunter.university.service;

import luckyhunter.university.dto.SubjectDto;
import luckyhunter.university.entity.Subject;
import luckyhunter.university.mapper.SubjectMapper;
import luckyhunter.university.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubjectServiceTest {
    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private SubjectMapper subjectMapper;

    @InjectMocks
    private SubjectService subjectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSubjects() {
        Subject subject = mock(Subject.class);
        when(subjectRepository.getAllSubjects()).thenReturn(Collections.singletonList(subject));
        when(subjectMapper.subjectToSubjectDto(subject)).thenReturn(new SubjectDto());

        List<SubjectDto> result = subjectService.getAllSubjects();

        verify(subjectRepository).getAllSubjects();
        assertEquals(1, result.size());
    }
}