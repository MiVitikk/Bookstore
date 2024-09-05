package backend24.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import backend24.bookstore.domain.Book;
import backend24.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookData(BookRepository bookRepository){
		return (args) ->{

			log.info("save some books");
			bookRepository.save(new Book("Kirja1" , "Kirjailija1", "aaa", 2010, 10));
			bookRepository.save(new Book("Kirja2", "Kirjailija2", "bbb", 2011, 12));
			bookRepository.save(new Book("Kirja3", "Kirjalija3", "ccc", 1990, 20));
			bookRepository.save(new Book("Kirja4", "Kirjailija4", "ddd", 1999, 25));

			log.info("Tulostetaan kirjat");
			for (Book book : bookRepository.findAll()){
				log.info(book.toString());
			}
		};
	}

}
