package com.example.trainee_app;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private static List<Book> bookshelf = new ArrayList<>();


    @GetMapping("/addBook")
    public String addBook(@RequestParam int id, @RequestParam String name, int authorId) {

        Book newBook = new Book(id, name, authorId);
        bookshelf.add(newBook);
        return "Book added successfully!";
    }


    @GetMapping("/allBooks")
    public List<Book> getAllBooks() {
        return bookshelf;
    }


    @GetMapping("/findById")
    public Book findById(@RequestParam int id) {
        for (Book b : bookshelf) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }


    @GetMapping("/findByName")
    public Book findByName(@RequestParam String name) {
        for (Book b : bookshelf) {

            if (b.getName().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }


    @GetMapping("/searchMsg")
    public String searchMsg(@RequestParam int id) {
        for (Book b : bookshelf) {
            if (b.getId() == id) {
                return "Found: " + b.getName();
            }
        }
        return "Sorry, that book ID is not available.";
    }
}
