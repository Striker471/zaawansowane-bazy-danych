package com.example.zbd.devtools;

import com.example.zbd.model.*;
import com.example.zbd.repository.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Component
@Profile("init")
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

    @Override
    public void run(String... args) {
        fillGenresTable();
        fillBooksAndAuthorsTables();
        fillCustomersTable();
        fillOrdersAndOrderDetailsTables();
        fillReviewsTable();
        System.out.println("Finished generating data");
    }

    public List<List<String>> getRecordsFromFile(String path) {
        List<List<String>> records = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.replace("\"", "").split(";");
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

    public void fillGenresTable() {
        Set<String> genresName = new HashSet<>();
        while (genresName.size() < genreSize)
            genresName.add(faker.book().genre());

        for (var genreName : genresName) {
            Genre genre = new Genre();
            genre.setGenreName(genreName);
            genreRepository.save(genre);
        }
    }

    public void fillBooksAndAuthorsTables() {
        var books = getRecordsFromFile("C:\\Users\\jakub\\Downloads\\books.csv\\books.csv"); //TODO file.path property
        List<Genre> genreList = genreRepository.findAll();

        for (var record : books) {
            Book book = new Book();
            try {
                book.setPublicationDate(generateRandomDateWithSpecificYear(Integer.parseInt(record.get(2))));
            } catch (Exception e) {
                continue;
            }

            Author author = authorRepository.findByName(record.get(1))
                    .orElseGet(() -> {
                        Author newAuthor = new Author();
                        newAuthor.setName(record.get(1));
                        return authorRepository.save(newAuthor);
                    });

            book.setTitle(record.get(0));
            book.setPrice(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 120)));
            book.setStockQuantity(faker.random().nextInt(300));
            book.setGenre(genreList.get(faker.random().nextInt(genreSize)));
            book.setAuthor(author);
            bookRepository.save(book);
        }
    }

    private LocalDate generateRandomDateWithSpecificYear(int year) {
        return LocalDate.of(year,  1 + faker.random().nextInt(12), 1 + faker.random().nextInt(28));
    }

    public void fillCustomersTable() {
        for (int i = 0; i < 100000; i++) {
            Customer customer = new Customer();
            customer.setCustomerName(faker.name().fullName());
            customer.setEmail(faker.internet().emailAddress());
            customer.setAddress(faker.address().fullAddress());
            customer.setPhoneNumber(faker.phoneNumber().phoneNumber());
            customerRepository.save(customer);
        }
    }

    public void fillOrdersAndOrderDetailsTables() {
        List<Customer> customerList = customerRepository.findAll();

        int bookAmount = (int) bookRepository.count();

        for (Customer customer : customerList) {
            for (int i = 0; i < faker.random().nextInt(1, 6); i++) {
                Order order = new Order();
                order.setCustomer(customer);
                order.setDate(generateRandomDateWithSpecificYear(faker.random().nextInt(2006, 2023)));
                order.setTotalPrice(new BigDecimal("10.0"));
                Order savedOrder = orderRepository.save(order);

                BigDecimal totalPrice = new BigDecimal("0.0");

                for (int j = 0; j < faker.random().nextInt(1, 7); j++) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setQuantity(faker.random().nextInt(1, 70));
                    orderDetail.setOrder(savedOrder);

                    Optional<Book> book = bookRepository.findById(faker.random().nextInt(1, bookAmount));

                    if (book.isPresent()) {
                        BigDecimal quantity = new BigDecimal(orderDetail.getQuantity());
                        orderDetail.setBook(book.get());
                        BigDecimal unitPrice = book.get().getPrice().multiply(quantity);
                        totalPrice = totalPrice.add(unitPrice);
                        orderDetail.setUnitPrice(unitPrice);
                        orderDetailRepository.save(orderDetail);
                    }
                }
                savedOrder.setTotalPrice(totalPrice);
                orderRepository.save(savedOrder);
            }
        }
    }

    public void fillReviewsTable() {
        List<Customer> customerList = customerRepository.findAll();
        int bookAmount = (int) bookRepository.count();

        for (Customer customer : customerList) {
            if (faker.random().nextInt(10) == 0) {
                Optional<Book> book = bookRepository.findById(faker.random().nextInt(1, bookAmount));
                if (book.isPresent()) {
                    Review review = new Review();
                    review.setCustomer(customer);
                    review.setReviewDate(generateRandomDateWithSpecificYear(faker.random().nextInt(2006, 2023)));
                    review.setComment(faker.book().publisher() + faker.lorem().sentence() + faker.artist().name());
                    review.setBook(book.get());
                    review.setRating(faker.random().nextInt(101));
                    reviewRepository.save(review);
                }
            }
        }
    }
}