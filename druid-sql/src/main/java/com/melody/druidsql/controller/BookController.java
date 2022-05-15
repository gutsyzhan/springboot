package com.melody.druidsql.controller;

import com.melody.druidsql.entity.Book;
import com.melody.druidsql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public List<Book> selectBookByName(String name){
        return bookService.selectBookByName(name);
    }
}