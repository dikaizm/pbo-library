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
    private String course;

    public Student(){
        super();
    }
    
    public Student(int id, String name, String username, String password, String course) {
        super(id, name, username, password);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String getRole() {
        return "Student";
    }
}
