package luckyhunter.university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * Объект передачи данных для группы.
 * Этот класс представляет группу с идентификатором и именем.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    /**
     * Уникальный идентификатор группы.
     */
    private int id;
    /**
     * Имя группы.
     */
    private String name;

    /**
     * Возвращает строковое представление группы в формате JSON.
     *
     * @return строковое представление группы в формате JSON
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("id", id)
                .append("name", name)
                .toString();
    }
}