package com.melody.dynamicmultipleds.mapper;

import com.melody.dynamicmultipleds.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select count(*) from book")
    Integer number();

    @Select("select * from book")
    List<Book> getAllBooks();
}
