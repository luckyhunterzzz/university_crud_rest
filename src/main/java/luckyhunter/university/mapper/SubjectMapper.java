package luckyhunter.university.mapper;

import luckyhunter.university.dto.SubjectDto;
import luckyhunter.university.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Маппер для преобразования объектов типа Subject в SubjectDto и обратно.
 */
@Mapper
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectDto subjectToSubjectDto(Subject subject);
}
