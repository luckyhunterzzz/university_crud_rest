package luckyhunter.university.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private int id;
    private LocalDateTime date;
    private Group group;
    private Teacher teacher;
    private Subject subject;

}