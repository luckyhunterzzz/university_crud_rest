package luckyhunter.university.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeacherModDto {
    private int id;
    private String teacherFirstName;
    private String teacherLastName;
    private int stage;
    private List<Integer> subjectIds;
}