package luckyhunter.university.validator;

import lombok.Value;
import luckyhunter.university.dto.StudentModDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentValidatorTest {
    private StudentValidator studentValidator;

    @BeforeEach
    public void setUp() {
        studentValidator = new StudentValidator();
    }

    @Test
    public void testValidateFirstNameIsTrueIfNameIsCorrect() {
        studentValidator.validateFirstName("Иван");
        List<String> errors = studentValidator.getErrors();
        assertTrue(errors.isEmpty());
        assertEquals("Иван", studentValidator.getStudentModDto().getFirstName());
    }

    @Test
    public void testValidateFirstNameIsFalseIfNameIsNull() {
        studentValidator.validateFirstName(null);
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("First Name must contain only Cyrillic characters without spaces or special symbols.", errors.get(0));
    }

    @Test
    public void testValidateFirstNameIsFalseIfNameIsLatins() {
        studentValidator.validateFirstName("Ivan");
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("First Name must contain only Cyrillic characters without spaces or special symbols.", errors.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "|", ".", "@", "\n", ",", "234", "-100"})
    public void testValidateFirstNameIsFalseIfNameIsInvalid(String arg) {
        studentValidator.validateFirstName(arg);
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("First Name must contain only Cyrillic characters without spaces or special symbols.", errors.get(0));
    }

    @Test
    public void testValidatePhoneNumberIsCorrect() {
        studentValidator.validatePhoneNumber("+79089089999");
        List<String> errors = studentValidator.getErrors();
        assertTrue(errors.isEmpty());
        assertEquals("+79089089999", studentValidator.getStudentModDto().getPhoneNumber());
    }

    @Test
    public void testValidatePhoneNumberIsFalseIfNull() {
        studentValidator.validatePhoneNumber(null);
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Phone Number must start with +7 and contain exactly 10 digits after that.", errors.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"+779089089999", "79089089999", "+19089089999", "+89089089999", "9089089999", "+7908908999"})
    public void testValidatePhoneNumberIsFalseIfInvalidDigits(String arg) {
        studentValidator.validatePhoneNumber(arg);
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Phone Number must start with +7 and contain exactly 10 digits after that.", errors.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"+7 9089089999", "-79089089999", "+", "+79089089999 ", "+79089089999.", "+7          "})
    public void testValidatePhoneNumberIsFalseIfInvalidSymbols(String arg) {
        studentValidator.validatePhoneNumber(arg);
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Phone Number must start with +7 and contain exactly 10 digits after that.", errors.get(0));
    }

    @Test
    public void testValidateLastNameIsTrueIfNameIsCorrect() {
        studentValidator.validateLastName("Иванов");
        List<String> errors = studentValidator.getErrors();
        assertTrue(errors.isEmpty());
        assertEquals("Иванов", studentValidator.getStudentModDto().getLastName());
    }

    @Test
    public void testValidateLastNameIsFalseIfNameIsNull() {
        studentValidator.validateLastName(null);
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Last Name must contain only Cyrillic characters without spaces or special symbols.", errors.get(0));
    }

    @Test
    public void testValidateLastNameIsFalseIfNameIsLatins() {
        studentValidator.validateLastName("Ivanov");
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Last Name must contain only Cyrillic characters without spaces or special symbols.", errors.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "|", ".", "@", "\n", ",", "234", "-100"})
    public void testValidateLastNameIsFalseIfNameIsInvalid(String arg) {
        studentValidator.validateLastName(arg);
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Last Name must contain only Cyrillic characters without spaces or special symbols.", errors.get(0));
    }

    @Test
    public void testValidateBirthDayisTrueIfCorrect() {
        studentValidator.validateBirthDate("2000-12-31");
        List<String> errors = studentValidator.getErrors();
        assertTrue(errors.isEmpty());
        assertEquals(LocalDate.parse("2000-12-31"), studentValidator.getStudentModDto().getBirthDate());
    }

    @Test
    public void testValidateBirthDayisFalseIfNull() {
        studentValidator.validateBirthDate(null);
        List<String> errors = studentValidator.getErrors();
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidateBirthDayisFalseIfInvalid() {
        studentValidator.validateBirthDate(null);
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Birth Date must be in the format yyyy-MM-dd.", errors.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"****-**-**", "31-12-2024", "21 00-10-10", "24-11-30", "1999-31-12", "22-10-01"})
    public void testValidateBirthDayisFalseIfInvalidSymbols(String arg) {
        studentValidator.validateBirthDate(arg);
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Birth Date must be in the format yyyy-MM-dd.", errors.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2100-10-10", "2024-11-30"})
    public void testValidateBirthDayisFalseIfDateInFuture(String arg) {
        studentValidator.validateBirthDate(arg);
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Birth Date cannot be in the future.", errors.get(0));
    }

    @Test
    public void testGetErrorsReturnsCorrectErrors() {
        studentValidator.validateFirstName("Ivan");
        List<String> errors = studentValidator.getErrors();
        assertEquals(1, errors.size());
    }

    @Test
    public void testGetErrorsReturnsCorrectErrorsIfManyErrors() {
        studentValidator.validateFirstName("Ivan");
        studentValidator.validateLastName("Ivanov");
        studentValidator.validatePhoneNumber("+0");
        studentValidator.validateBirthDate("1111");
        List<String> errors = studentValidator.getErrors();
        assertEquals(4, errors.size());
    }

    @Test
    public void testGetStudentModDtoReturnsCorrectData() {
        studentValidator.validateFirstName("Иван");
        StudentModDto dto = studentValidator.getStudentModDto();
        assertEquals("Иван", dto.getFirstName());
    }

    @Test
    public void testToStringReturnsCorrectErrorMessage() {
        studentValidator.validateFirstName("Ivan");
        String expectedErrorMessage = "StudentValidator Errors:\n- First Name must contain only Cyrillic characters without spaces or special symbols.\n";
        assertEquals(expectedErrorMessage, studentValidator.toString());
    }
}