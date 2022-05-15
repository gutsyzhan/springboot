package com.melody.dynamicmultipleds.controller;

import com.melody.dynamicmultipleds.entity.Book;
import com.melody.dynamicmultipleds.mapper.BookMapper;
import com.melody.dynamicmultipleds.provider.MultipleDataSourceProvider;
import com.melody.dynamicmultipleds.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class DataSourceController {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceController.class);
    /**
     * 修改数据源名称
     * @param dsName
     * @param session
     * @return
     */
    @PostMapping("/dsName")
    public void updateDataSourceName(String dsName, HttpSession session){
        session.setAttribute(MultipleDataSourceProvider.DS_SESSION_KEY,dsName);
        logger.info("数据源切换为{}",dsName);
    }


    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
}
