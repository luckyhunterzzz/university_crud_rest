package luckyhunter.university.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import luckyhunter.university.dto.StudentDto;
import luckyhunter.university.dto.StudentModDto;
import luckyhunter.university.entity.Student;
import luckyhunter.university.mapper.StudentMapper;
import luckyhunter.university.repository.StudentRepository;
import luckyhunter.university.validator.StudentValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {
    private final StudentRepository studentRepository = new StudentRepository();
    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    public List<StudentDto> getAllStudents() {
        return studentRepository.getAllStudents().stream()
                .map(studentMapper::studentToStudentDto)
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(int id) {
        Student student = studentRepository.getStudentById(id);
        return studentMapper.studentToStudentDto(student);
    }

    public List<StudentDto> getStudentsByFirstName(String firstName) {
        List<Student> students = studentRepository.getStudentsByFirstName(firstName);
        return students.stream()
                .map(studentMapper::studentToStudentDto)
                .collect(Collectors.toList());
    }

    public void addStudent(StudentModDto studentModDto) {
        Student student = StudentMapper.INSTANCE.studentModDtoToStudent(studentModDto);
        studentRepository.addStudent(student);
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteStudentById(id);
    }

    public StudentValidator parseJson(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(body);
        StudentValidator studentValidator = new StudentValidator();

        String firstName = jsonNode.get("firstName").asText();
        String lastName = jsonNode.get("lastName").asText();
        String birthDateString = jsonNode.get("birthDate").asText();
        String phoneNumber = jsonNode.get("phoneNumber").asText();

        studentValidator.validateFirstName(firstName);
        studentValidator.validateLastName(lastName);
        studentValidator.validateBirthDate(birthDateString);
        studentValidator.validatePhoneNumber(phoneNumber);

        return studentValidator;
    }
}
