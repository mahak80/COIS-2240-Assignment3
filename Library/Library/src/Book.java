public class Book {
    private int id;
    private String title;
    private boolean available;

    // Constructor with validation for ID
    public Book(int id, String title) throws Exception {
        if (!isValidId(id)) {
            throw new Exception("Invalid book ID. Book ID must be between 100 and 999.");
        }
        this.id = id;
        this.title = title;
        this.available = true; // Default state is available
    }

    // Check if the book ID is valid
    public boolean isValidId(int id) {
        return id >= 100 && id <= 999;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    // Borrow the book
    public void borrowBook() {
        available = false;
    }

    // Return the book
    public void returnBook() {
        available = true;
    }
}