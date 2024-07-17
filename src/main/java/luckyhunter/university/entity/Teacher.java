package luckyhunter.university.entity;

import lombok.*;

import java.util.List;

/**
 * Класс, представляющий сущность преподавателя.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher {
    /**
     * Уникальный идентификатор преподавателя.
     */
    private int id;

    /**
     * Имя преподавателя.
     */
    private String teacherFirstName;

    /**
     * Фамилия преподавателя.
     */
    private String teacherLastName;

    /**
     * Стаж преподавательской деятельности.
     */
    private int stage;

    /**
     * Список предметов, которые ведет преподаватель.
     */
    private List<Subject> subjects;
}
