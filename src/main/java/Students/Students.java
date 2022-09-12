package Students;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Students {

    @JsonProperty("students")
    private List<Student> students;

    public Students() {
    }

    public Students(List<Student> students) {
        this.students = students;
    }

    @JsonProperty("students")
    public List<Student> getStudents() {
        return students;
    }

    @JsonProperty("students")
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Students{" +
                "students=" + students +
                '}';
    }
}
