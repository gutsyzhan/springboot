package com.melody.druidsql.service;

import com.melody.druidsql.entity.Book;
import com.melody.druidsql.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    public List<Book> selectBookByName(String name){
        return bookMapper.selectBookByName(name);
    }
}
