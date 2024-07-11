package luckyhunter.university.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentModDto {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
}
