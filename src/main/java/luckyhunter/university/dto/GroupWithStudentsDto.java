package luckyhunter.university.dto;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * Объект передачи данных для группы с учащимися.
 * Этот класс представляет группу с идентификатором, именем и списком учащихся.
 */
@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GroupWithStudentsDto {
    /**
     * Уникальный идентификатор группы.
     */
    private int id;
    /**
     * Имя группы.
     */
    private String name;
    /**
     * Список учащихся в группе.
     */
    private List<StudentDto> students;

    /**
     * Возвращает строковое представление группы с учащимися в формате JSON.
     *
     * @return строковое представление группы с учащимися в формате JSON
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("id", id)
                .append("name", name)
                .append("students", students)
                .toString();
    }
}