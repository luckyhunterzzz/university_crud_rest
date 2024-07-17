package luckyhunter.university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * Объект передачи данных для студента.
 * Этот класс представляет студента с именем, фамилией и датой рождения.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    /**
     * Имя студента.
     */
    private String firstName;
    /**
     * Фамилия студента.
     */
    private String lastName;
    /**
     * Дата рождения студента.
     */
    private LocalDate birthDate;

    /**
     * Возвращает строковое представление студента в формате JSON.
     *
     * @return строковое представление студента в формате JSON
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("birthDate", birthDate)
                .toString();
    }
}

