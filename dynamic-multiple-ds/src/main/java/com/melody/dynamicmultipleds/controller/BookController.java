package com.melody.dynamicmultipleds.controller;

import com.melody.dynamicmultipleds.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    public static final Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookService bookService;

    //@GetMapping("/book")
    //public List<Integer> books(){
    //    List<Integer> list = new ArrayList<>();
    //    log.info("master db numbers is {}",bookService.master());
    //    list.add(bookService.master());
    //    log.info("slave db numbers is {}",bookService.slave());
    //    list.add(bookService.slave());
    //    return list;
    //}
}
