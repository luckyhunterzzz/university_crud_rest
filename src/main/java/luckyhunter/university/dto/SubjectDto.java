package luckyhunter.university.dto;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * Объект передачи данных для информации о предмете.
 * Этот класс представляет предмет с идентификатором и названием.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubjectDto {
    /**
     * Идентификатор предмета.
     */
    private int id;
    /**
     * Название предмета.
     */
    private String subjectName;

    /**
     * Возвращает строковое представление объекта SubjectDto в формате JSON.
     *
     * @return строка с JSON-представлением объекта
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("id", id)
                .append("subjectName", subjectName)
                .toString();
    }
}
