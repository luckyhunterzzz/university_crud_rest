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

/**
 * Сервисный класс для работы с данными студентов.
 */
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    /**
     * Конструктор класса StudentService.
     *
     * @param studentRepository Репозиторий для работы с данными студентов
     * @param studentMapper     Маппер для преобразования объектов Student
     */
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    /**
     * Возвращает список всех студентов в виде DTO объектов.
     *
     * @return Список DTO объектов студентов
     */
    public List<StudentDto> getAllStudents() {
        return studentRepository.getAllStudents().stream()
                .map(studentMapper::studentToStudentDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает DTO объект студента по указанному идентификатору.
     *
     * @param id Идентификатор студента
     * @return DTO объект студента
     */
    public StudentDto getStudentById(int id) {
        Student student = studentRepository.getStudentById(id);
        return studentMapper.studentToStudentDto(student);
    }

    /**
     * Возвращает список DTO объектов студентов с указанным именем.
     *
     * @param firstName Имя студента
     * @return Список DTO объектов студентов
     */
    public List<StudentDto> getStudentsByFirstName(String firstName) {
        List<Student> students = studentRepository.getStudentsByFirstName(firstName);
        return students.stream()
                .map(studentMapper::studentToStudentDto)
                .collect(Collectors.toList());
    }

    /**
     * Добавляет нового студента на основе переданных данных.
     *
     * @param studentModDto DTO объект данных нового студента
     */
    public void addStudent(StudentModDto studentModDto) {
        Student student = StudentMapper.INSTANCE.studentModDtoToStudent(studentModDto);
        studentRepository.addStudent(student);
    }

    /**
     * Удаляет студента по указанному идентификатору.
     *
     * @param id Идентификатор студента
     */
    public void deleteStudentById(int id) {
        studentRepository.deleteStudentById(id);
    }

    /**
     * Парсинг JSON строку с данными студента и возвращает объект валидатора.
     *
     * @param body JSON строка с данными студента
     * @return Объект валидатора с данными о студенте
     * @throws JsonProcessingException Возникает при ошибке обработки JSON
     */
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
