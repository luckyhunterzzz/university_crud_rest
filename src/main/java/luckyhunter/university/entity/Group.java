package luckyhunter.university.entity;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * Класс, представляющий сущность группы студентов.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    /**
     * Уникальный идентификатор группы.
     */
    private int id;

    /**
     * Название группы.
     */
    private String name;

    /**
     * Список студентов, состоящих в группе.
     */
    private List<Student> students;

    /**
     * Конструктор для создания группы с указанием id и названия.
     *
     * @param id   Уникальный идентификатор группы.
     * @param name Название группы.
     */
    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Переопределенный метод toString для вывода информации о группе в формате JSON.
     *
     * @return Строковое представление объекта группы.
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
