package backend24.bookstore.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend24.bookstore.domain.Book;
import backend24.bookstore.domain.BookRepository;
import backend24.bookstore.domain.CategoryRepository;

@RestController
public class RestBookstoreController {
    private static final Logger log = LoggerFactory.getLogger(RestBookstoreController.class);


    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/books")
    public Iterable<Book> getBooks(){
        log.info("Fetch books from db");
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Optional<Book> getOneBook(@PathVariable("id") Long id){
        return bookRepository.findById(id);
    }

    @PostMapping("/book")
    Book newBook(@RequestBody Book newBook){
        return bookRepository.save(newBook);
    
    }

    @PutMapping("/book/{id}")
    Book editBook(@RequestBody Book editedBook, @PathVariable Long id){
        editedBook.setId(id);
        return bookRepository.save(editedBook);
    }

    @DeleteMapping("/books/{id}")
    public Iterable<Book> deleteBook(@PathVariable Long id){
        bookRepository.deleteById(id);
        return bookRepository.findAll();
    }

}
