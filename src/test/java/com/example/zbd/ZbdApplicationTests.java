package com.example.zbd;

import com.example.zbd.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ZbdApplicationTests {

    @Autowired
    private BookRepository bookRepository;
}
