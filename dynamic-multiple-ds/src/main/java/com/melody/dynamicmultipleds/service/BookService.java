package com.melody.dynamicmultipleds.service;

import com.melody.dynamicmultipleds.annotation.MyDataSource;
import com.melody.dynamicmultipleds.entity.Book;
import com.melody.dynamicmultipleds.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    //@MyDataSource("master")
    //public Integer master(){
    //    return bookMapper.number();
    //}
    //
    //@MyDataSource("slave")
    //public Integer slave(){
    //    return bookMapper.number();
    //}

    @MyDataSource("master")
    public List<Book> getAllBooks(){
       return bookMapper.getAllBooks();
    }
}