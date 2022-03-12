package com.infinitiessoft.test.controller;


import com.infinitiessoft.test.entity.Book;
import com.infinitiessoft.test.service.BookService;
import com.infinitiessoft.test.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    
    @Autowired
    private BookService bookService;


    @GetMapping("/book")
    public JsonResult read(){
        return bookService.read();
    }

    @PostMapping("/book")
    public JsonResult create(@RequestBody Book book){
        return bookService.creat(book);
    }

    @PutMapping("/book/{id}")
    public JsonResult update(@PathVariable Integer id,@RequestBody Book book){
        return bookService.updateById(id,book);
    }

    @DeleteMapping("/book/{id}")
    public JsonResult delete(@PathVariable Integer id){
        return bookService.deleteById(id);
    }
}
