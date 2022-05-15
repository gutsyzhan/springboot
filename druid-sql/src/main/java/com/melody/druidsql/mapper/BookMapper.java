package com.melody.druidsql.mapper;

import com.melody.druidsql.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> selectBookByName(String name);
}