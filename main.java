package Asm;

public class main {
    public static void main(String[] args) {
        StudentManagement studentManagement = new StudentManagement();
        StudentUI studentUI = new StudentUI(studentManagement);
        studentUI.setVisible(true);
    }
}