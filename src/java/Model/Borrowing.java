/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tiara
 */

import java.sql.Date;

public class Borrowing {
    private int id;
    private int bookId;
    private int userId;
    private Date dateBorrowed;
    private Date dateDue;

    public Borrowing(int id, int bookId, int userId, Date dateBorrowed, Date dateDue) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.dateBorrowed = dateBorrowed;
        this.dateDue = dateDue;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getDateBorrowed() {
        return dateBorrowed;
    }

    public Date getDateDue() {
        return dateDue;
    }
}
