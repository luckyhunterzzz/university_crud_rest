package luckyhunter.university.dto;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeacherDto {
    private String teacherFirstName;
    private String teacherLastName;
    private int stage;
    private List<String> subjects;

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