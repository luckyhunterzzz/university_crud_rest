package luckyhunter.university.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * Объект передачи данных для создания и(или) модификации информации о студенте.
 * Этот класс представляет студента с идентификатором, именем, фамилией, датой рождения и номером телефона.
 */
@Data
public class StudentModDto {
    /**
     * Идентификатор студента.
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
}
