import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LibraryManagementTest {

    private Transaction transaction;
    private Book book;
    private Member member;

    @Before
    public void setUp() throws Exception {
        // Initialize the Transaction instance, Book, and Member
        transaction = Transaction.getTransaction();
        book = new Book(101, "Test Book");
        member = new Member(1, "Test Member");
    }

    @Test
    public void testBorrowReturn() throws Exception {
        // Step 1: Ensure the book is initially available
        assertTrue("Book should initially be available", book.isAvailable());

        // Step 2: Borrow the book
        boolean borrowSuccess = transaction.borrowBook(book, member);
        assertTrue("Borrowing should be successful", borrowSuccess);
        assertFalse("Book should be unavailable after borrowing", book.isAvailable());

        // Step 3: Attempt to borrow the same book again (should fail)
        boolean borrowFail = transaction.borrowBook(book, member);
        assertFalse("Borrowing the same book again should fail", borrowFail);

        // Step 4: Return the book
        boolean returnSuccess = transaction.returnBook(book, member);
        assertTrue("Returning the book should be successful", returnSuccess);
        assertTrue("Book should be available after returning", book.isAvailable());

        // Step 5: Attempt to return the same book again (should fail)
        boolean returnFail = transaction.returnBook(book, member);
        assertFalse("Returning the same book again should fail", returnFail);
    }
}