package luckyhunter.university.dto;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * Объект передачи данных для информации о преподавателе.
 * Этот класс представляет преподавателя с именем, фамилией, стажем и списком предметов.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeacherDto {
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
     * Список предметов, преподаваемых преподавателем.
     */
    private List<String> subjects;

    /**
     * Возвращает строковое представление объекта TeacherDto в формате JSON.
     *
     * @return строка с JSON-представлением объекта
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("teacherFirstName", teacherFirstName)
                .append("teacherLastName", teacherLastName)
                .append("stage", stage)
                .append("subjects", subjects)
                .toString();
    }
}