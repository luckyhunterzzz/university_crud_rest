package luckyhunter.university.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@Data
@AllArgsConstructor
public class GroupWithStudentsDto {
    private int id;
    private String name;
    private List<StudentDto> students;


    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("id", id)
                .append("name", name)
                .append("students", students)
                .toString();
    }
}