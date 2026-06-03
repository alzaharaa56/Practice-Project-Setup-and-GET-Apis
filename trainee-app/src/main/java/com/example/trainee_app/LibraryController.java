package com.example.trainee_app;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
@RestController
public class LibraryController {

    private static List<Author> authorList = new ArrayList<>();

    private static List<Book> bookList = new ArrayList<>();

    @GetMapping("/addAuthor")
    public String addAuthor(@RequestParam int id, @RequestParam String name, @RequestParam String biography) {
        Author newAuthor = new Author(id, name, biography);
        authorList.add(newAuthor);
        return "Author registered successfully!";
    }

    @GetMapping("/allAuthors")
    public List<Author> getAllAuthors() {
        return authorList;
    }


    @GetMapping("/addRelationalBook")
    public String addRelationalBook(@RequestParam int id,
                                    @RequestParam String name,
                                    @RequestParam int authorId) {

        boolean authorExists = false;


        for (Author author : authorList) {
            if (author.getId() == authorId) {
                authorExists = true;
                break;
            }
        }

        if (authorExists) {
            bookList.add(new Book(id, name, authorId));
            return "Book added and linked to author successfully!";
        } else {
            return "Error: Author registry failed. Author ID does not exist.";
        }
    }

    @GetMapping("/authorReport")
    public String getAuthorReport(@RequestParam String authorName) {
        Author foundAuthor = null;


        for (Author a : authorList) {
            if (a.getName().equalsIgnoreCase(authorName)) {
                foundAuthor = a;
                break;
            }
        }


        if (foundAuthor == null) {
            return "Error: Author not found.";
        }


        StringBuilder booksBuilder = new StringBuilder();
        boolean hasBooks = false;

        for (Book b : bookList) {
            if (b.getAuthorId() == foundAuthor.getId()) {
                booksBuilder.append(b.getName()).append(", ");
                hasBooks = true;
            }
        }


        String booksList = hasBooks ? booksBuilder.toString() : "None";

        return "Author: " + foundAuthor.getName() +
                " | Biography: " + foundAuthor.getBiography() +
                " | Books Written: " + booksList;
    }
}

