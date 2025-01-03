/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tiara
 */

public class Student extends User {
    private String major;

    public Student(){
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

    public String getMajor() {
        return major;
    }

    @Override
    public String getRole() {
        return "student";
    }
}
