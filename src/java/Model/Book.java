/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tiara
 */

public class Book {
    private int id;
    private String title;
    private String details;
    private String publisher;

    public Book(){
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Book(int id, String title, String details, String publisher) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getPublisher() {
        return publisher;
    }
}
