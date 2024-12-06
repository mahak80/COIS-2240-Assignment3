import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {
    // Singleton instance
    private static Transaction instance;

    // Track whether the Singleton instance has been created
    private static boolean isInstanceCreated = false;

    // List to store transaction history
    private final List<String> transactionHistory = new ArrayList<>();

    // Private constructor to prevent instantiation
    private Transaction() {
        if (isInstanceCreated) {
            throw new RuntimeException("Singleton instance already created. Reflection is not allowed.");
        }
        isInstanceCreated = true;
    }

    // Public method to get the Singleton instance
    public static synchronized Transaction getTransaction() {
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
    }

    // Borrow a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book);
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            transactionHistory.add(transactionDetails);
            saveTransaction(transactionDetails);
            return true;
        }
        System.out.println("The book is not available.");
        return false;
    }

    // Return a book
    public boolean returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            transactionHistory.add(transactionDetails);
            saveTransaction(transactionDetails);
            return true;
        }
        System.out.println("This book was not borrowed by the member.");
        return false;
    }

    // Display the transaction history from the file
    public void displayTransactionHistory() {
        File file = new File("transactions.txt");
        if (!file.exists()) {
            System.out.println("No transactions found. The transactions file does not exist.");
            return;
        }

        System.out.println("=== Transaction History ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading transaction history: " + e.getMessage());
        }
    }

    // Save a transaction to the file
    private void saveTransaction(String transactionDetails) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true))) {
            writer.write(transactionDetails);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Get the current date and time
    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}