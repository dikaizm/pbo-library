package Model;

public class BookCategory {
    private int id;
    private String name;

    public BookCategory() {
    }

    public BookCategory(String name) {
        this.name = name;
    }   

    public BookCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
