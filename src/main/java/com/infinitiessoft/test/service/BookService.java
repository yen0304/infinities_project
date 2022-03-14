package com.infinitiessoft.test.service;

import com.infinitiessoft.test.entity.Book;

import java.util.List;

public interface BookService {

    String creat(Book book);

    String updateById(Integer id,Book book);

    String deleteById(Integer id);


    List<Book> read();
}
