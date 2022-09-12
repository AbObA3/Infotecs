package DataManipulation;

import Client.FTPClient;
import Students.Student;
import Students.StudentComparator;
import Students.Students;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DataManipulationImpl implements DataManipulation {

    Students students = null;
    FTPClient ftpClient;


    public DataManipulationImpl(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }


    @Override
    public String getAllStudentsByName() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        students.getStudents().stream().sorted(new StudentComparator()).
                forEach(s -> stringBuilder.append(s.getName()).append("\n"));
        return stringBuilder.toString();
    }

    @Override
    public String getStudentById(int id) {

        StringBuilder stringBuilder = new StringBuilder();
        students.getStudents().forEach(s -> {
            if (s.getId() == id) {
                stringBuilder.append("Имя: ").append(s.getName()).append("\n");
            }
        });

        return stringBuilder.toString();
    }

    @Override
    public String addStudentToList(String name) throws IOException, URISyntaxException {
        students.getStudents().add(new Student(students.getStudents().size() + 1, name));
        writeStudents();
        return "Студент добавлен";
    }

    @Override
    public String deleteStudentById(int id) throws IOException, URISyntaxException {
        if (id > students.getStudents().size()) {
            return "id не существует";
        }
        students.setStudents(students.getStudents().stream().filter(s -> s.getId() != id).collect(Collectors.toList()));
        students.getStudents().forEach(s -> {
            if (s.getId() > id) {
                s.setId(s.getId() - 1);
            }
        });
        writeStudents();
        return "Студент удален";
    }

    @Override
    public Students initStudents() throws IOException {
        ftpClient.downloadFile();
        final ObjectMapper objectMapper = new ObjectMapper();
        students = objectMapper.readValue(new File("students.json"), Students.class);
        return students;
    }

    void writeStudents() throws IOException, URISyntaxException, DatabindException {
        Map<String, Object> map = new HashMap<>();
        map.put("students", students.getStudents());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(Paths.get("students.json").toFile(), map);
        ftpClient.UploadFile();
    }
}
