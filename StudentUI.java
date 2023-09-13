package Asm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class StudentUI extends JFrame {
    private StudentManagement studentManagement;
    private JTable table;
    private JTextField nameTextField, idTextField, gpaTextField, majorTextField;

    public StudentUI(StudentManagement studentManagement) {
        this.studentManagement = studentManagement;
        initUI();
    }
    // Set up UI components and event listeners
    private void initUI() {
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create input panel for adding/updating students
        JLabel nameLabel = new JLabel("Name:");
        JLabel idLabel = new JLabel("ID:");
        JLabel gpaLabel = new JLabel("GPA:");
        JLabel majorLabel = new JLabel("Major:");

        nameTextField = new JTextField(20);
        idTextField = new JTextField(20);
        gpaTextField = new JTextField(20);
        majorTextField = new JTextField(20);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(idLabel);
        inputPanel.add(idTextField);
        inputPanel.add(gpaLabel);
        inputPanel.add(gpaTextField);
        inputPanel.add(majorLabel);
        inputPanel.add(majorTextField);
        // Create button panel for performing operations on students
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton searchButton = new JButton("Search");
        searchButton.setToolTipText("Search students");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
     // Add new student
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameTextField.getText();
                    String id = idTextField.getText();
                    double gpa = Double.parseDouble(gpaTextField.getText());
                    String major = majorTextField.getText();
                    Student newStudent = new Student(name, id, gpa, major);
                    studentManagement.addStudent(newStudent);
                    updateStudentTable();
                    clearInputFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(StudentUI.this, "Invalid input for GPA. Please enter a number.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StudentUI.this, "An error occurred while adding the student: " + ex.getMessage());
                }
            }
        });

        // Update student
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int rowIndex = table.getSelectedRow();
                    if (rowIndex == -1) {
                        JOptionPane.showMessageDialog(StudentUI.this, "Please select a row to update.");
                        return;
                    }
                    String id = (String) table.getValueAt(rowIndex, 1);
                    String name = JOptionPane.showInputDialog(StudentUI.this, "Enter student name:");
                    String gpaStr = JOptionPane.showInputDialog(StudentUI.this, "Enter student GPA:");
                    double gpa = Double.parseDouble(gpaStr);
                    String major = JOptionPane.showInputDialog(StudentUI.this, "Enter student major:");
                    studentManagement.updateStudent(id, name, gpa, major);
                    updateStudentTable();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(StudentUI.this, "Invalid input for GPA. Please enter a number.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StudentUI.this, "An error occurred while updating the student: " + ex.getMessage());
                }
            }
        });

        // Delete student
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int rowIndex = table.getSelectedRow();
                    if (rowIndex == -1) {
                        JOptionPane.showMessageDialog(StudentUI.this, "Please select a row to delete.");
                        return;
                    }
                    String id = (String) table.getValueAt(rowIndex, 1);
                    studentManagement.deleteStudent(id);
                    updateStudentTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StudentUI.this, "An error occurred while deleting the student: " + ex.getMessage());
                }
            }
        });

        // Search for student
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = JOptionPane.showInputDialog(StudentUI.this, "Enter student ID:");
                    List<Student> students = studentManagement.searchStudents(id, true);
                    updateStudentTable(students);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StudentUI.this, "An error occurred while searching for the student: " + ex.getMessage());
                }
            }
        });

        // Create table panel for displaying list of students
        String[] columnNames = {"#", "ID", "Name", "GPA", "Major"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 10));
        table.setRowHeight(15);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.setBorder(new EmptyBorder(0, 20, 20, 20));

        // Add input, button, and table panels to main panel
        JPanel mainPanel1 = new JPanel();
        mainPanel1.setLayout(new BoxLayout(mainPanel1, BoxLayout.Y_AXIS));
        mainPanel1.add(inputPanel);
        mainPanel1.add(buttonPanel);
        mainPanel1.add(tablePanel);

        getContentPane().add(mainPanel1);
        setVisible(true);

        // Load data and display on table
        updateStudentTable();
    }
    // Update table with all students in the database
    private void updateStudentTable() {
        List<Student> students = studentManagement.getAllStudents();
        updateStudentTable(students);
    }
    // Update table with a specific list of students
    private void updateStudentTable(List<Student> students) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        int i = 1;
        for (Student student : students) {
            Object[] rowData = {i++, student.getId(), student.getName(), student.getGpa(), student.getMajor()};
            model.addRow(rowData);
        }
    }

    // Clear input fields after adding a new student
    private void clearInputFields() {
        nameTextField.setText("");
        idTextField.setText("");
        gpaTextField.setText("");
        majorTextField.setText("");
    }
}