package backend24.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import backend24.bookstore.domain.Book;
import backend24.bookstore.domain.BookRepository;
import backend24.bookstore.domain.Category;
import backend24.bookstore.domain.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository catrepository;

    @Test
    public void findByTitleShouldReturnBook(){
        List<Book> books = bookRepository.findByTitle("The Hobbit");
         assertThat(books).hasSize(1);
         assertThat(books.get(0).getTitle()).isEqualTo("The Hobbit");
        
    }

    @Test
    public void deleteNewBook(){
        List<Book> books = bookRepository.findByTitle("The Hobbit");
        Book book = books.get(0);
        bookRepository.delete(book);
        List<Book> newBooks = bookRepository.findByTitle("The Hobbit");
        assertThat(newBooks).hasSize(0);
    }

    @Test
    public void createNewbook(){
        Category testCategory = new Category("TestCategory");
        catrepository.save(testCategory);
        Book book = new Book("TestTitle", "TestAuthot", "000", 1990, 20.0, testCategory);
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }
}
