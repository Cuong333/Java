package Asm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortStudent {
    // Sort list of students by name
    public static List<Student> sortByName(List<Student> students) {
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
        return sortedStudents;
    }

    // Sort list of students by ID
    public static List<Student> sortById(List<Student> students) {
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getId().compareTo(s2.getId());
            }
        });
        return sortedStudents;
    }

    // Sort list of students by GPA
    public static List<Student> sortByGpa(List<Student> students) {
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s1.getGpa(), s2.getGpa());
            }
        });
        return sortedStudents;
    }

    // Sort list of students by major
    public static List<Student> sortByMajor(List<Student> students) {
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getMajor().compareTo(s2.getMajor());
            }
        });
        return sortedStudents;
    }

    // Filter list of students by name with a given keyword
    public static List<Student> filterByName(List<Student> students, String keyword) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().contains(keyword)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    // Filter list of students by ID with a given keyword
    public static List<Student> filterById(List<Student> students, String keyword) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getId().contains(keyword)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    // Filter list of students by major with a given keyword
    public static List<Student> filterByMajor(List<Student> students, String keyword) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getMajor().contains(keyword)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    // Filter list of students by GPA within a given range
    public static List<Student> filterByGpa(List<Student> students, double minGpa, double maxGpa) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            double gpa = student.getGpa();
            if (gpa >= minGpa && gpa <= maxGpa) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }
}