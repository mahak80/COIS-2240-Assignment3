import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
import static org.junit.Assert.*;

public class LibraryManagementTest {

    private Transaction transaction;
    private Book book;
    private Member member;

    @Before
    public void setUp() throws Exception {
        transaction = Transaction.getTransaction();
        book = new Book(101, "Test Book"); // Book with valid ID and title
        member = new Member(1, "Test Member"); // Corrected to include an ID and name
    }

    // Test 1: Book ID Validation
    @Test
    public void testBookId() {
        try {
            // Valid IDs
            Book validBook1 = new Book(100, "Valid Book 1");
            Book validBook2 = new Book(999, "Valid Book 2");
            assertNotNull("Book with ID 100 should be created successfully", validBook1);
            assertNotNull("Book with ID 999 should be created successfully", validBook2);

            // Invalid IDs
            try {
                new Book(99, "Invalid Book");
                fail("Exception should be thrown for ID less than 100");
            } catch (Exception e) {
                assertEquals("Invalid book ID. Book ID must be between 100 and 999.", e.getMessage());
            }

            try {
                new Book(1000, "Invalid Book");
                fail("Exception should be thrown for ID greater than 999");
            } catch (Exception e) {
                assertEquals("Invalid book ID. Book ID must be between 100 and 999.", e.getMessage());
            }
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // Test 2: Borrow/Return Validation
    @Test
    public void testBorrowReturn() {
        // Step 1: Ensure the book is available
        assertTrue("Book should be available", book.isAvailable());

        // Step 2: Borrow the book
        boolean borrowSuccess = transaction.borrowBook(book, member);
        assertTrue("Borrowing should succeed", borrowSuccess);
        assertFalse("Book should be unavailable after borrowing", book.isAvailable());

        // Step 3: Attempt to borrow the same book again
        boolean borrowFail = transaction.borrowBook(book, member);
        assertFalse("Borrowing the same book again should fail", borrowFail);

        // Step 4: Return the book
        boolean returnSuccess = transaction.returnBook(book, member);
        assertTrue("Returning the book should succeed", returnSuccess);
        assertTrue("Book should be available after returning", book.isAvailable());

        // Step 5: Attempt to return the same book again
        boolean returnFail = transaction.returnBook(book, member);
        assertFalse("Returning the same book again should fail", returnFail);
    }

    // Test 3: Singleton Validation
    @Test
    public void testSingletonTransaction() throws Exception {
        // Step 1: Retrieve the constructor of the Transaction class
        Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();

        // Step 2: Check the constructor's modifier
        int modifiers = constructor.getModifiers();
        assertTrue("Constructor should be private", Modifier.isPrivate(modifiers));

        // Step 3: Ensure Reflection-based instantiation throws an exception
        constructor.setAccessible(true); // Make the private constructor accessible
        try {
            constructor.newInstance(); // Attempt to create a new instance via Reflection
            fail("Reflection should have failed to create a new instance");
        } catch (InvocationTargetException e) {
            // Verify the exception message
            String expectedMessage = "Singleton instance already created. Reflection is not allowed.";
            assertEquals(expectedMessage, e.getCause().getMessage());
        }

        // Step 4: Validate that the Singleton instance is not null
        Transaction transactionInstance = Transaction.getTransaction();
        assertNotNull("Singleton instance should not be null", transactionInstance);
    }
}