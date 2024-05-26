package com.example.zbd.repository;

import com.example.zbd.dto.BestSellingBooksDTO;
import com.example.zbd.dto.BookWithAuthorsAndGenresDTO;
import com.example.zbd.dto.NumberEachBookSoldDTO;
import com.example.zbd.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b" +
            " JOIN b.genre g" +
            " WHERE g.genreName = :genreName" +
            " ORDER BY b.title")
    List<Book> findByGenreName(@Param("genreName") String genreName);


    List<Book> findBooksByGenreGenreNameOrderByTitle(String genreName);

    List<Book> findBooksByGenreGenreNameAndAuthorName(String genreName, String authorName);


    @Query("select new com.example.zbd.dto.NumberEachBookSoldDTO(b.title, SUM(o.quantity))" +
            " from Book b" +
            " join b.orderDetails o" +
            " group by b.title" +
            " order by SUM(o.quantity) desc")
    List<NumberEachBookSoldDTO> findNumberEachBookSold();

    @Query("select new com.example.zbd.dto.BookWithAuthorsAndGenresDTO(b.title, a.name, g.genreName)" +
            " from Book b" +
            " join b.author a" +
            " join b.genre g ")
    List<BookWithAuthorsAndGenresDTO> findBooksWithAuthorsAndGenres();

    @Query("select new com.example.zbd.dto.BestSellingBooksDTO(b.title, count(od.quantity), sum(od.quantity)) " +
            "from Order o join o.orderDetails od " +
            "join od.book b " +
            "where o.date < current date - 1 year " +
            "group by b.title " +
            "order by sum(od.quantity) desc ")
    List<BestSellingBooksDTO> findBestSellingBooks();
}
