package backend24.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import backend24.bookstore.domain.AppUser;
import backend24.bookstore.domain.AppUserRepository;
import backend24.bookstore.domain.Book;
import backend24.bookstore.domain.BookRepository;
import backend24.bookstore.domain.Category;
import backend24.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookData(BookRepository bookRepository, CategoryRepository catrepository, AppUserRepository urepository){
		return (args) ->{

			log.info("save some books");

			AppUser user1 = new AppUser("user" , "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			Category category1 = new Category("Horror");
			Category category2 = new Category("Fantasy");
			Category category3 = new Category("Drama");

			catrepository.save(category1);
			catrepository.save(category2);
			catrepository.save(category3);


			bookRepository.save(new Book("Kirja1", "Kirjailija1", "12341234", 2020, 10.0, category3));
			bookRepository.save(new Book("Kirja2", "Kirajilija2", "12319238", 1990, 15.0, category1));
			bookRepository.save(new Book("Kirja3", "Kirjailija3", "000101010", 1991, 2.0, category2));

			log.info("Tulostetaan kirjat");
			for (Book book : bookRepository.findAll()){
				log.info(book.toString());
			}
		};
	}

}
