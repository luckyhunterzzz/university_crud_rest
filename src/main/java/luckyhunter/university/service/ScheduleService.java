package luckyhunter.university.service;

import luckyhunter.university.dto.ScheduleDto;
import luckyhunter.university.mapper.ScheduleMapper;
import luckyhunter.university.repository.ScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    private ScheduleMapper scheduleMapper;

    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    public List<ScheduleDto> getAllSchedules() {
        return scheduleRepository.getAllSchedules().stream()
                .map(scheduleMapper::scheduleToScheduleDto)
                .collect(Collectors.toList());
    }
}