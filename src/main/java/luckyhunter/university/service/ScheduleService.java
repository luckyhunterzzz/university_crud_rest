package luckyhunter.university.service;

import luckyhunter.university.dto.ScheduleDto;
import luckyhunter.university.mapper.ScheduleMapper;
import luckyhunter.university.repository.ScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduleService {
    private final ScheduleRepository scheduleRepository = new ScheduleRepository();
    private final ScheduleMapper scheduleMapper = ScheduleMapper.INSTANCE;

    public List<ScheduleDto> getAllSchedules() {
        return scheduleRepository.getAllSchedules().stream()
                .map(scheduleMapper::scheduleToScheduleDto)
                .collect(Collectors.toList());
    }
}