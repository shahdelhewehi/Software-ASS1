import java.util.ArrayList;
import java.util.Scanner;

// ==== Book Class ====
class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public String displayInfo() {
        return "The Book Name: " + title + "\n" +
                "The Book Author: " + author + "\n" +
                "The Book Available Or Not: " + isAvailable + "\n";
    }
}

// ==== User Class ====
class User {
    private String name;
    private String id;
    private ArrayList<Book> borrowedBooks;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String displayInfo() {
        StringBuilder books = new StringBuilder();
        for (Book b : borrowedBooks) {
            books.append(b.getTitle()).append(", ");
        }
        return "User Name: " + name + "\n" +
                "User ID: " + id + "\n" +
                "Borrowed Books: " + (books.length() > 0 ? books.toString() : "None") + "\n";
    }
}

// ==== Library Class ====
class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public String borrowBook(Book book, User user) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            user.getBorrowedBooks().add(book);
            return user.getName() + " borrowed " + book.getTitle();
        } else {
            return book.getTitle() + " is not available";
        }
    }

    public String returnBook(Book book, User user) {
        if (user.getBorrowedBooks().contains(book)) {
            book.setAvailable(true);
            user.getBorrowedBooks().remove(book);
            return user.getName() + " returned " + book.getTitle();
        } else {
            return user.getName() + " did not borrow " + book.getTitle();
        }
    }

    public void displayAvailableBooks() {
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.displayInfo());
            }
        }
    }

    public User findUserByName(String name) {
        for (User u : users) {
            if (u.getName().equals(name))
                return u;
        }
        return null;
    }

    public Book findBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equals(title))
                return b;
        }
        return null;
    }
}

// ==== Main Class ====
public class Library {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Available Books");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter book title: ");
                    String title = input.nextLine();
                    System.out.print("Enter book author: ");
                    String author = input.nextLine();
                    Book book = new Book(title, author);
                    lib.addBook(book);
                    System.out.println("Book added successfully");
                    break;

                case "2":
                    System.out.print("Enter user name: ");
                    String name = input.nextLine();
                    System.out.print("Enter user ID: ");
                    String userId = input.nextLine();
                    User user = new User(name, userId);
                    lib.addUser(user);
                    System.out.println("User added successfully");
                    break;

                case "3":
                    System.out.print("Enter user name: ");
                    String uName = input.nextLine();
                    System.out.print("Enter book title: ");
                    String bTitle = input.nextLine();

                    User u = lib.findUserByName(uName);
                    Book b = lib.findBookByTitle(bTitle);

                    if (u != null && b != null) {
                        System.out.println(lib.borrowBook(b, u));
                    } else {
                        System.out.println("User or Book not found");
                    }
                    break;

                case "4":
                    System.out.print("Enter user name: ");
                    String uName2 = input.nextLine();
                    System.out.print("Enter book title: ");
                    String bTitle2 = input.nextLine();

                    User u2 = lib.findUserByName(uName2);
                    Book b2 = lib.findBookByTitle(bTitle2);

                    if (u2 != null && b2 != null) {
                        System.out.println(lib.returnBook(b2, u2));
                    } else {
                        System.out.println("User or Book not found");
                    }
                    break;

                case "5":
                    lib.displayAvailableBooks();
                    break;

                case "6":
                    System.out.println("Goodbye 👋");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
