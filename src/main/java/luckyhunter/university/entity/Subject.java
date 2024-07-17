package luckyhunter.university.entity;

import lombok.*;

/**
 * Класс, представляющий сущность предмета.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject {
    /**
     * Уникальный идентификатор предмета.
     */
    private int id;

    /**
     * Название предмета.
     */
    private String subjectName;
}
