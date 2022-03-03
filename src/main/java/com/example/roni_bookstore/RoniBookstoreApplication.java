package com.example.roni_bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.roni_bookstore.domain.Book;
import com.example.roni_bookstore.domain.BookRepository;
import com.example.roni_bookstore.domain.Category;
import com.example.roni_bookstore.domain.CategoryRepository;

@SpringBootApplication
public class RoniBookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(RoniBookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RoniBookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of students");
			crepository.save(new Category("Magic"));
			crepository.save(new Category("Adventure"));
			crepository.save(new Category("Scifi"));

			repository.save(new Book("J.K Rowling", "Harry Potter", "030-2211-3-8756-07", 1997, 22,
					crepository.findByName("Magic").get(0)));
			repository.save(new Book("Edgar Rice Burroughs", "John Carter", "030-2630-0-85-0768", 1964, 16,
					crepository.findByName("Adventure").get(0)));

			log.info("fetch all students");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}