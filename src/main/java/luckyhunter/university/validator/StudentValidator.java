package luckyhunter.university.validator;

import luckyhunter.university.dto.StudentModDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StudentValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[А-ЯЁа-яё]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+7\\d{10}$");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private List<String> errors = new ArrayList<>();
    private StudentModDto studentModDto = new StudentModDto();

    public void validateFirstName(String name) {
        if (name != null && NAME_PATTERN.matcher(name).matches()) {
            studentModDto.setFirstName(name);
        } else if (name == null) {
            errors.add("First Name can not be NULL");
        } else {
            errors.add("First Name must contain only Cyrillic characters without spaces or special symbols.");
        }
    }

    public void validateLastName(String name) {
        if (name != null && NAME_PATTERN.matcher(name).matches()) {
            studentModDto.setLastName(name);
        } else if (name == null) {
            errors.add("Last Name can not be NULL");
        } else {
            errors.add("Last Name must contain only Cyrillic characters without spaces or special symbols.");
        }
    }

    public void validateBirthDate(String date) {
        LocalDate parsedDate = null;
        if (date == null) {
            errors.add("Birth Date must be in the format yyyy-MM-dd.");
        } else {
            try {
                parsedDate = LocalDate.parse(date, DATE_FORMATTER);
                if (parsedDate.isBefore(LocalDate.now())) {
                    studentModDto.setBirthDate(parsedDate);
                } else {
                    errors.add("Birth Date cannot be in the future.");
                }
            } catch (DateTimeParseException e) {
                errors.add("Birth Date must be in the format yyyy-MM-dd.");
            }
        }
    }

    public void validatePhoneNumber(String number) {
        if (number != null && PHONE_PATTERN.matcher(number).matches()) {
            studentModDto.setPhoneNumber(number);
        } else if (number == null) {
            errors.add("Phone Number can not be NULL");
        } else {
            errors.add("Phone Number must start with +7 and contain exactly 10 digits after that.");
        }
    }

    public List<String> getErrors() {
        return errors;
    }

    public StudentModDto getStudentModDto() {
        return studentModDto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StudentValidator Errors:\n");
        for (String error : errors) {
            sb.append("- ").append(error).append("\n");
        }
        return sb.toString();
    }
}


