package luckyhunter.university.dto;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ScheduleDto {
    private int id;
    private LocalDateTime date;
    private String groupName;
    private String teacherName;
    private String subjectName;

    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("id", id)
                .append("date", date)
                .append("groupName", groupName)
                .append("teacherName", teacherName)
                .append("subjectName", subjectName)
                .toString();
    }
}