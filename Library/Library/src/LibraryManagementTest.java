import static org.junit.Assert.*;
import org.junit.Test;

public class LibraryManagementTest {

    @Test
    public void testBookIdValidation() throws Exception {
        // Test valid book IDs
        Book validBook1 = new Book(100, "Valid Book 1");
        assertEquals(100, validBook1.getId());

        Book validBook2 = new Book(999, "Valid Book 2");
        assertEquals(999, validBook2.getId());

        // Test invalid book IDs and check exceptions
        try {
            new Book(99, "Invalid Book");
            fail("Expected an exception for ID less than 100");
        } catch (Exception e) {
            assertEquals("Invalid book ID. Book ID must be between 100 and 999.", e.getMessage());
        }

        try {
            new Book(1000, "Invalid Book");
            fail("Expected an exception for ID greater than 999");
        } catch (Exception e) {
            assertEquals("Invalid book ID. Book ID must be between 100 and 999.", e.getMessage());
        }
    }
}