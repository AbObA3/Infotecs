package DataManipulation;

import Students.Students;

import java.io.IOException;
import java.net.URISyntaxException;

public interface DataManipulation {

    String getAllStudentsByName() throws IOException;

    String getStudentById(int id);

    String addStudentToList(String name) throws IOException, URISyntaxException;

    String deleteStudentById(int id) throws IOException, URISyntaxException;

    Students initStudents() throws IOException;


}
