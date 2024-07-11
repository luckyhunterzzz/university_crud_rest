package luckyhunter.university.entity;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@Getter
@Setter
@NoArgsConstructor
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;

    public Student(int id, String firstName, String lastName, LocalDate birthDate, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("birthDate", birthDate)
                .append("phoneNumber", phoneNumber)
                .toString();
    }
}
