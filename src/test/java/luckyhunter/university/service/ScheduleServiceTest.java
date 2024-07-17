package luckyhunter.university.service;

import luckyhunter.university.dto.ScheduleDto;
import luckyhunter.university.entity.Schedule;
import luckyhunter.university.mapper.ScheduleMapper;
import luckyhunter.university.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScheduleServiceTest {
    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private ScheduleMapper scheduleMapper;

    @InjectMocks
    private ScheduleService scheduleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSchedules() {
        Schedule schedule = mock(Schedule.class);
        when(scheduleRepository.getAllSchedules()).thenReturn(Collections.singletonList(schedule));
        when(scheduleMapper.scheduleToScheduleDto(schedule)).thenReturn(new ScheduleDto());

        List<ScheduleDto> result = scheduleService.getAllSchedules();

        verify(scheduleRepository).getAllSchedules();
        assertEquals(1, result.size());
    }
}