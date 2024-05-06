package com.example.zbd.repository;

import com.example.zbd.dto.BookWithAuthorsAndGenresDto;
import com.example.zbd.dto.NumberEachBookSoldDTO;
import com.example.zbd.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

//    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.genreName = :genreName")
//    List<Book> findByGenreName(@Param("genreName") String genreName);
    List<Book> findBooksByGenreGenreName(String genreName);

    @Query("select b.title, SUM(o.quantity) as total_sold from Book b join b.orderDetails o group by b.title order by total_sold desc")
    List<NumberEachBookSoldDTO> findNumberEachBookSold();

    @Query("select b.title, a.name, g.genreName from Book b join b.author a join b.genre g ")
    List<BookWithAuthorsAndGenresDto> getBooksWithAuthorsAndGenres();
}
