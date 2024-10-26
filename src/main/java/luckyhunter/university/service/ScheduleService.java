package luckyhunter.university.service;

import luckyhunter.university.dto.ScheduleDto;
import luckyhunter.university.mapper.ScheduleMapper;
import luckyhunter.university.repository.ScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервисный класс для работы с расписанием занятий.
 */
public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    private ScheduleMapper scheduleMapper;

    /**
     * Конструктор класса ScheduleService.
     *
     * @param scheduleRepository Репозиторий для работы с данными расписания
     * @param scheduleMapper     Маппер для преобразования объектов Schedule
     */
    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    /**
     * Возвращает список всех расписаний в виде DTO объектов.
     *
     * @return Список DTO объектов расписаний
     */
    public List<ScheduleDto> getAllSchedules() {
        return scheduleRepository.getAllSchedules().stream()
                .map(scheduleMapper::scheduleToScheduleDto)
                .collect(Collectors.toList());
    }
}