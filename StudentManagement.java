package Asm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentManagement {
    private Map<String, Student> studentMap;

    public StudentManagement() {
        // Initialize a new HashMap to store students
        this.studentMap = new HashMap<>();
    }

    // Add a student to the HashMap
    public void addStudent(Student student) {
        this.studentMap.put(student.getId(), student);
    }

    // Delete a student from the HashMap by ID
    public void deleteStudent(String id) {
        this.studentMap.remove(id);
    }

    // Update a student's information by ID
    public Student updateStudent(String id, String name, double gpa, String major) {
        Student student = this.studentMap.get(id);
        if (student != null) {
            student.setName(name);
            student.setGpa(gpa);
            student.setMajor(major);
            this.studentMap.put(id, student);
        }
        return student;
    }

    // Search for students by keyword (either name or ID)
    public List<Student> searchStudents(String keyword, boolean searchById) {
        List<Student> students = new ArrayList<>();
        for (Map.Entry<String, Student> entry : this.studentMap.entrySet()) {
            Student student = entry.getValue();
            if (searchById && student.getId().equals(keyword)) {
                // If searching by ID and student ID matches the keyword, add to list
                students.add(student);
            } else if (!searchById && student.getName().contains(keyword)) {
                // If searching by name and student name contains the keyword, add to list
                students.add(student);
            }
        }
        return students;
    }

    // Get all students in the HashMap as a list
    public List<Student> getAllStudents() {
        return new ArrayList<>(this.studentMap.values());
    }
}