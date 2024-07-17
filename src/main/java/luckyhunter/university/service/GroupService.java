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

/**
 * Сервисный класс для работы с группами студентов.
 */
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final StudentMapper studentMapper;

    /**
     * Конструктор класса GroupService.
     *
     * @param groupRepository Репозиторий для работы с данными групп
     * @param groupMapper     Маппер для преобразования объектов Group
     * @param studentMapper   Маппер для преобразования объектов Student
     */
    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper, StudentMapper studentMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.studentMapper = studentMapper;
    }

    /**
     * Возвращает DTO объект группы с списком студентов по указанному идентификатору группы.
     *
     * @param id Идентификатор группы
     * @return DTO объект группы с данными о студентах
     */
    public GroupWithStudentsDto getGroupWithStudentsById(int id) {
        Group group = groupRepository.getGroupWithStudentsById(id);
        List<StudentDto> studentDtos = group.getStudents().stream()
                .map(studentMapper::studentToStudentDto)
                .collect(Collectors.toList());
        return new GroupWithStudentsDto(group.getId(), group.getName(), studentDtos);
    }

    /**
     * Возвращает список всех групп в виде DTO объектов.
     *
     * @return Список DTO объектов групп
     */
    public List<GroupDto> getAllGroups() {
        return groupRepository.getAllGroups().stream()
                .map(groupMapper::groupToGroupDto)
                .collect(Collectors.toList());
    }
}