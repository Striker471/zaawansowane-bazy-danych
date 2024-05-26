package com.example.zbd.devtools;

import com.example.zbd.model.Book;
import com.example.zbd.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Profile("testQuery")
@Component
public class TestOrmQuery implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final GenreRepository genreRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    public TestOrmQuery(
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
    public void run(String... args) throws Exception {

//        List<Book> bookList = bookRepository.findBooksByGenreGenreNameOrderByTitle("Legend");
//        List<Book> bookListOwn = bookRepository.findByGenreName("Legend");
//        var genreDTOList = genreRepository.findAverageRatingEachGenre();
        //        for(int i = 0; i < 10; i++){
//            System.out.println(bookListOwn.get(i));
//        }
//        var bestSellingBooks = bookRepository.findBestSellingBooks();
        orderRepository.findBookStatistics(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 12, 31));
//        List<AuthorWithBookAmountDto>  authorList = authorRepository.getAuthorsWithBookAmount();


    }
}