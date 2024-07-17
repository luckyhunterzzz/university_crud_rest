package luckyhunter.university.dto;

import lombok.*;

import java.util.List;

/**
 * Объект передачи данных для изменения информации о преподавателе.
 * Этот класс представляет преподавателя с именем, фамилией, стажем и списком идентификаторов предметов.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeacherModDto {
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
     * Стаж преподавателя.
     */
    private int stage;

    /**
     * Список идентификаторов предметов, преподаваемых преподавателем.
     */
    private List<Integer> subjectIds;
}