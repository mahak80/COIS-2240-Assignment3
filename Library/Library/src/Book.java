// This class represents a Book with attributes such as title, author, and ISBN.
// It provides methods to access and modify these details.
public class Book {
    private int id;
    private String title;
    private boolean available;

    public Book(int id, String title) throws Exception {
    	if (!isValidId(id)) {
            throw new Exception("Invalid book ID. Book ID must be between 100 and 999.");
        }
        this.id = id;
        this.title = title;
        this.available = true;
    }
    // Check if the book ID is valid
    public boolean isValidId(int id) {
        return id >= 100 && id <= 999;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    // Method to borrow the book
    public void borrowBook() {
        if (available) {
            available = false;
        }
    }

    // Method to return the book
    public void returnBook() {
        available = true;
    }
}