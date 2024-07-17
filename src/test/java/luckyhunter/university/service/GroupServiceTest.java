package luckyhunter.university.service;

import luckyhunter.university.dto.GroupDto;
import luckyhunter.university.dto.GroupWithStudentsDto;
import luckyhunter.university.dto.StudentDto;
import luckyhunter.university.entity.Group;
import luckyhunter.university.entity.Student;
import luckyhunter.university.mapper.GroupMapper;
import luckyhunter.university.mapper.StudentMapper;
import luckyhunter.university.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private GroupMapper groupMapper;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private GroupService groupService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetGroupWithStudentsById() {
        Group group = mock(Group.class);
        Student student = mock(Student.class);
        when(groupRepository.getGroupWithStudentsById(1)).thenReturn(group);
        when(group.getStudents()).thenReturn(Collections.singletonList(student));
        when(studentMapper.studentToStudentDto(student)).thenReturn(new StudentDto());

        GroupWithStudentsDto result = groupService.getGroupWithStudentsById(1);

        verify(groupRepository).getGroupWithStudentsById(1);
        assertEquals(1, result.getStudents().size());
    }

    @Test
    public void testGetAllGroups() {
        Group group = mock(Group.class);
        when(groupRepository.getAllGroups()).thenReturn(Collections.singletonList(group));
        when(groupMapper.groupToGroupDto(group)).thenReturn(new GroupDto());

        List<GroupDto> result = groupService.getAllGroups();

        verify(groupRepository).getAllGroups();
        assertEquals(1, result.size());
    }
}