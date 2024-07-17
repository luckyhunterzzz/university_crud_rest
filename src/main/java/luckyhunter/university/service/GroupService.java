package luckyhunter.university.service;

import luckyhunter.university.dto.GroupDto;
import luckyhunter.university.dto.GroupWithStudentsDto;
import luckyhunter.university.dto.StudentDto;
import luckyhunter.university.entity.Group;
import luckyhunter.university.mapper.GroupMapper;
import luckyhunter.university.mapper.StudentMapper;
import luckyhunter.university.repository.GroupRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final StudentMapper studentMapper;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper, StudentMapper studentMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.studentMapper = studentMapper;
    }

    public GroupWithStudentsDto getGroupWithStudentsById(int id) {
        Group group = groupRepository.getGroupWithStudentsById(id);
        List<StudentDto> studentDtos = group.getStudents().stream()
                .map(studentMapper::studentToStudentDto)
                .collect(Collectors.toList());
        return new GroupWithStudentsDto(group.getId(), group.getName(), studentDtos);
    }

    public List<GroupDto> getAllGroups() {
        return groupRepository.getAllGroups().stream()
                .map(groupMapper::groupToGroupDto)
                .collect(Collectors.toList());
    }
}