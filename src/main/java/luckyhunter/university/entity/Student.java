package luckyhunter.university.entity;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * Класс, представляющий сущность студента.
 */
@Getter
@Setter
@NoArgsConstructor
public class Student {
    /**
     * Уникальный идентификатор студента.
     */
    private int id;

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
     * Номер телефона студента.
     */
    private String phoneNumber;

    /**
     * Конструктор для создания студента с указанием всех полей.
     *
     * @param id          Уникальный идентификатор студента.
     * @param firstName   Имя студента.
     * @param lastName    Фамилия студента.
     * @param birthDate   Дата рождения студента.
     * @param phoneNumber Номер телефона студента.
     */
    public Student(int id, String firstName, String lastName, LocalDate birthDate, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Переопределенный метод toString для вывода информации о студенте в формате JSON.
     *
     * @return Строковое представление объекта студента.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("birthDate", birthDate)
                .append("phoneNumber", phoneNumber)
                .toString();
    }
}
