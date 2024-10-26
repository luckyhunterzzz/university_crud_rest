package luckyhunter.university.mapper;

import luckyhunter.university.dto.GroupDto;
import luckyhunter.university.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Маппер для преобразования объектов типа Group в GroupDto и обратно.
 */
@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);
    GroupDto groupToGroupDto(Group group);
}