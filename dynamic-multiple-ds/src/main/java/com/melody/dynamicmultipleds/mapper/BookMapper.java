package com.melody.dynamicmultipleds.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {
    @Select("select count(*) from book")
    Integer number();
}
