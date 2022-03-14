package com.infinitiessoft.test.service;

import com.infinitiessoft.test.entity.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    ResponseEntity<String> creat(Book book);

    ResponseEntity<String> updateById(Integer id,Book book);

    ResponseEntity<String> deleteById(Integer id);


    ResponseEntity<List> read();
}
