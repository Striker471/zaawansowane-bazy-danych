package com.example.zbd.devtools;

import com.example.zbd.model.Book;
import com.example.zbd.model.Genre;
import com.example.zbd.repository.*;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Component
@Profile("dev")
public class DataGenerator implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final GenreRepository genreRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    private final int genreSize = 30;
    Faker faker = new Faker(new Locale("pl"));
    Random generator = new Random();


    public DataGenerator(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            CustomerRepository customerRepository,
            GenreRepository genreRepository,
            OrderDetailRepository orderDetailRepository,
            OrderRepository orderRepository,
            ReviewRepository reviewRepository
    ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.genreRepository = genreRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {


        createGenresTable();


//        System.out.println(genres);
//        while (genres.size() < 100) {
//            Genre genre = new Genre();
//            genre.setGenreName(faker.book().genre());
//            System.out.println(genres.size());
//        }


    }

    public List<List<String>> getRecordsFromFile(String path) {
        List<List<String>> records = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                List<String> record = new ArrayList<>();
                record.add(values[1]); // Book title
                record.add(values[2]); // Book author
                record.add(values[3]); // Year of publication
                records.add(record);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return records;
    }

    public void createGenresTable() {
        Set<String> genresName = new HashSet<>();
        while (genresName.size() < genreSize)
            genresName.add(faker.book().genre());

        for (var genreName : genresName) {
            Genre genre = new Genre();
            genre.setGenreName(genreName);
            genreRepository.save(genre);
        }
    }

    public void createBooksTable() {
        var books = getRecordsFromFile("C:\\Users\\jakub\\Downloads\\books.csv\\books.csv"); //TODO file.path property

        List<Genre> genreList = genreRepository.findAll();
        for (var record : books) {
            Book book = new Book();
            book.setTitle(record.get(0));
            book.setPublication_date(generateRandomDateWithSpecificYear(Integer.parseInt(record.get(2))));
//            book.setPrice(faker.number().randomDouble(70, 0.5, 0.77));

            book.setGenre(genreList.get(generator.nextInt(genreSize)));


        }
    }

    private Date generateRandomDateWithSpecificYear(int year) {
        LocalDate date = LocalDate.of(year, 1 + faker.random().nextInt(12), 1 + faker.random().nextInt(28));
        return Date.valueOf(date);
    }
}