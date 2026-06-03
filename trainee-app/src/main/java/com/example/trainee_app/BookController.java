package com.example.trainee_app;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private static List<Book> bookshelf = new ArrayList<>();


    @GetMapping("/add-book")
    public String addBook(@RequestParam int id, @RequestParam String name) {
        Book newBook = new Book(id, name);
        bookshelf.add(newBook);
        return "Book added successfully!";
    }


    @GetMapping("/all-books")
    public List<Book> getAllBooks() {
        return bookshelf;
    }


    @GetMapping("/find-by-id")
    public Book findById(@RequestParam int id) {
        for (Book b : bookshelf) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }


    @GetMapping("/find-by-name")
    public Book findByName(@RequestParam String name) {
        for (Book b : bookshelf) {

            if (b.getName().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }


    @GetMapping("/search-msg")
    public String searchMsg(@RequestParam int id) {
        for (Book b : bookshelf) {
            if (b.getId() == id) {
                return "Found: " + b.getName();
            }
        }
        return "Sorry, that book ID is not available.";
    }
}
