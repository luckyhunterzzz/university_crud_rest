package luckyhunter.university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@Data
@AllArgsConstructor
public class GroupDto {
    private int id;
    private String name;

    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("id", id)
                .append("name", name)
                .toString();
    }
}