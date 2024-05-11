package com.example.zbd.repository;

import com.example.zbd.dto.BookWithAuthorsAndGenresDto;
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


    @Query("select new com.example.zbd.dto.NumberEachBookSoldDTO(b.title, SUM(o.quantity))" +
            " from Book b" +
            " join b.orderDetails o" +
            " group by b.title" +
            " order by SUM(o.quantity) desc")
    List<NumberEachBookSoldDTO> findNumberEachBookSold();

    @Query("select new com.example.zbd.dto.BookWithAuthorsAndGenresDto(b.title, a.name, g.genreName)" +
            " from Book b" +
            " join b.author a" +
            " join b.genre g ")
    List<BookWithAuthorsAndGenresDto> getBooksWithAuthorsAndGenres();


}
