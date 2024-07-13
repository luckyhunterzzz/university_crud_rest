package luckyhunter.university.entity;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private int id;
    private String name;
    private List<Student> students;

    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("id", id)
                .append("name", name)
                .append("students", students)
                .toString();
    }
}
