/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author tiara
 */
public class Student extends User {

    private String major;
    private List<BorrowRecord> borrowRecords;

    public Student() {
        super();
    }

    public Student(String name, String email, String password, String major) {
        super(name, email, password);
        this.major = major;
    }

    public Student(int id, String name, String email, String password, String major) {
        super(id, name, email, password);
        this.major = major;
    }

    public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }

    public int countBorrowedBooks() {
        int count = 0;
        if (borrowRecords != null) {
            for (BorrowRecord record : borrowRecords) {
                if ("borrowed".equals(record.getStatus())) {
                    count++;
                }
            }
        }

        return count;
    }

    public int countOverdueBooks() {
        int count = 0;
        if (borrowRecords != null) {
            for (BorrowRecord record : borrowRecords) {
                if (record.isOverdue()) {
                    count++;
                }
            }
        }
        return count;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String getRole() {
        return "student";
    }
}
