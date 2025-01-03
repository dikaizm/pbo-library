/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tiara
 */

public class Librarian extends User {
    private String department;
    
    public Librarian() {
        super();
    }

    public Librarian(int id, String name, String username, String password, String department) {
        super(id, name, username, password);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String getRole() {
        return "Librarian";
    }
}
