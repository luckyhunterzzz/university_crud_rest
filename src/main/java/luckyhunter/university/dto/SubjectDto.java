package luckyhunter.university.dto;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubjectDto {
    private int id;
    private String subjectName;

    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("id", id)
                .append("subjectName", subjectName)
                .toString();
    }
}
