package luckyhunter.university.dto;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * Объект передачи данных для расписания.
 * Этот класс представляет расписание с идентификатором, датой, именем группы, именем преподавателя и именем предмета.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ScheduleDto {
    /**
     * Уникальный идентификатор расписания.
     */
    private int id;
    /**
     * Дата и время расписания.
     */
    private LocalDateTime date;
    /**
     * Имя группы, для которой назначено расписание.
     */
    private String groupName;
    /**
     * Имя преподавателя, ведущего занятие по расписанию.
     */
    private String teacherName;
    /**
     * Имя предмета, который будет изучаться по расписанию.
     */
    private String subjectName;

    /**
     * Возвращает строковое представление расписания в формате JSON.
     *
     * @return строковое представление расписания в формате JSON
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("id", id)
                .append("date", date)
                .append("groupName", groupName)
                .append("teacherName", teacherName)
                .append("subjectName", subjectName)
                .toString();
    }
}