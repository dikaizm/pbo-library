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

    public Librarian(String name, String email, String password, String department) {
        super(name, email, password);
        this.department = department;
    }

    public Librarian(int id, String name, String email, String password, String department) {
        super(id, name, email, password);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String getRole() {
        return "librarian";
    }
}
