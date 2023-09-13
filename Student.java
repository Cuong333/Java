package Asm;
import java.util.*;
// The Student class defines the properties and methods related to a student
public class Student {
    private String name;    // The name of the student
    private String id;      // The ID of the student
    private double gpa;     // The GPA of the student
    private String major;   // The major of the student

    // Constructor to initialize the properties with the values passed in
    public Student(String name, String id, double gpa, String major) {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
        this.major = major;
    }

    // Getter methods to access the properties
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getGpa() {
        return gpa;
    }

    public String getMajor() {
        return major;
    }

    // Setter methods to modify the properties
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    
    // Returns a string representation of the Student object
    // This method is useful for displaying the information of a student on a UI
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", gpa=" + gpa +
                ", major='" + major + '\'' +
                '}';
    }
}