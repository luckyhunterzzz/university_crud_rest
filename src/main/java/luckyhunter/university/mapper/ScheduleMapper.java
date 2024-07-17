package luckyhunter.university.mapper;

import luckyhunter.university.dto.ScheduleDto;
import luckyhunter.university.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Маппер для преобразования объектов типа Schedule в ScheduleDto и обратно.
 */
@Mapper
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(source = "group.name", target = "groupName")
    @Mapping(source = "teacher.teacherFirstName", target = "teacherName")
    @Mapping(source = "subject.subjectName", target = "subjectName")
    ScheduleDto scheduleToScheduleDto(Schedule schedule);
}