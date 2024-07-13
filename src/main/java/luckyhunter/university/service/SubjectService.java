package luckyhunter.university.service;

import luckyhunter.university.dto.SubjectDto;
import luckyhunter.university.mapper.SubjectMapper;
import luckyhunter.university.repository.SubjectRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectService {
    private final SubjectRepository subjectRepository = new SubjectRepository();
    private final SubjectMapper subjectMapper = SubjectMapper.INSTANCE;

    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.getAllSubjects().stream()
                .map(subjectMapper::subjectToSubjectDto)
                .collect(Collectors.toList());
    }
}
