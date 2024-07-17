package luckyhunter.university.service;

import luckyhunter.university.dto.SubjectDto;
import luckyhunter.university.mapper.SubjectMapper;
import luckyhunter.university.repository.SubjectRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectService {
    private SubjectRepository subjectRepository;
    private SubjectMapper subjectMapper;

    public SubjectService(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.getAllSubjects().stream()
                .map(subjectMapper::subjectToSubjectDto)
                .collect(Collectors.toList());
    }
}
