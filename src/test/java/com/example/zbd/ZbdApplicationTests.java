package com.example.zbd;

import com.example.zbd.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ZbdApplicationTests {

    @Autowired
    private BookRepository bookRepository;


    @Test
    void contextLoads() {
        var x = bookRepository.findByGenreName("elo");
        assertEquals("elo", x);
    }


}
