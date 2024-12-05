import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members = new ArrayList<Member>();
    private List<Book> books = new ArrayList<Book>();

 // Add a new member to the library
    public boolean addMember(Member member) {
        // Check if a member with the same ID already exists
        if (findMemberById(member.getId()) != null) {
            System.out.println("A member with ID " + member.getId() + " already exists. Member not added.");
            return false; // Duplicate ID, addition failed
        }
        members.add(member);
        return true; // Member added successfully
    }

    // Add a new book to the library
    public boolean addBook(Book book) {
        // Check if a book with the same ID already exists
        if (findBookById(book.getId()) != null) {
            System.out.println("A book with ID " + book.getId() + " already exists. Book not added.");
            return false; // Duplicate ID, addition failed
        }
        books.add(book);
        return true; // Book added successfully
    }

    // Find a member by ID
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // Find a book by ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // Get the list of members
    public List<Member> getMembers() {
        return members;
    }
    
    // Get the list of books
    public List<Book> getBooks() {
        return books;
    }
}
