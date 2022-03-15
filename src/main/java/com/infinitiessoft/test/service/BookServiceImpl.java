package com.infinitiessoft.test.service;

import com.infinitiessoft.test.dao.BookRepository;
import com.infinitiessoft.test.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


@Component
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;


    /**
     * 創建書本數據
     * @param book
     * @return
     */
    @Override
    public ResponseEntity<String> creat(Book book) {
        if(book.getName()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(!Objects.isNull(bookRepository.findByName(book.getName()))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 依據id更新書本
     * @param id
     * @param book
     * @return
     */
    @Override
    public ResponseEntity<String> updateById(Integer id,Book book) {
        if(bookRepository.findById(id).orElse(null)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        book.setId(id);
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    /**
     * 依據書本id刪除資料
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<String> deleteById(Integer id) {
        if(bookRepository.findById(id).orElse(null)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List> read() {
        List<Book> list = new ArrayList<>();
        Iterator<Book> its =bookRepository.findAll().iterator();
        while (true) {
            if (its.hasNext()) {
               list.add(its.next());
            } else {
                break;
            }
        }
            return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
