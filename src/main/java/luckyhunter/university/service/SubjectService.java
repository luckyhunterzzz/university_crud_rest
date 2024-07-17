package luckyhunter.university.service;

import luckyhunter.university.dto.SubjectDto;
import luckyhunter.university.mapper.SubjectMapper;
import luckyhunter.university.repository.SubjectRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервисный класс для работы с данными предметов (дисциплин).
 */
public class SubjectService {
    private SubjectRepository subjectRepository;
    private SubjectMapper subjectMapper;

    /**
     * Конструктор класса SubjectService.
     *
     * @param subjectRepository Репозиторий для работы с данными предметов
     * @param subjectMapper     Маппер для преобразования объектов Subject
     */
    public SubjectService(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    /**
     * Возвращает список всех предметов в виде DTO объектов.
     *
     * @return Список DTO объектов предметов
     */
    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.getAllSubjects().stream()
                .map(subjectMapper::subjectToSubjectDto)
                .collect(Collectors.toList());
    }
}
