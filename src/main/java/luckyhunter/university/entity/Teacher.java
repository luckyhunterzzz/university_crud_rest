package luckyhunter.university.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher {
    private int id;
    private String teacherFirstName;
    private String teacherLastName;
    private int stage;
    private List<Subject> subjects;
}
