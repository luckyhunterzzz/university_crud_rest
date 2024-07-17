package luckyhunter.university.entity;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Класс, представляющий сущность расписания занятий.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    /**
     * Уникальный идентификатор расписания.
     */
    private int id;

    /**
     * Дата и время занятия.
     */
    private LocalDateTime date;

    /**
     * Группа, для которой составлено расписание.
     */
    private Group group;

    /**
     * Преподаватель, проводящий занятие.
     */
    private Teacher teacher;

    /**
     * Предмет, который изучается на занятии.
     */
    private Subject subject;

}