package com.infinitiessoft.test.controller;


import com.infinitiessoft.test.entity.Book;
import com.infinitiessoft.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    
    @Autowired
    private BookService bookService;


    @GetMapping("/books")
    public List<Book> read(){
        return bookService.read();
    }

    @PostMapping("/books")
    public String create(@RequestBody Book book){
        return bookService.creat(book);
    }

    @PutMapping("/books/{bookId}")
    public String update(@PathVariable Integer bookId,@RequestBody Book book){
        return bookService.updateById(bookId,book);
    }

    @DeleteMapping("/books/{bookId")
    public String delete(@PathVariable Integer bookId){
        return bookService.deleteById(bookId);
    }
}
