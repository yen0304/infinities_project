package com.infinitiessoft.test.service;

import com.infinitiessoft.test.dao.BookRepository;
import com.infinitiessoft.test.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String creat(Book book) {
        if(book.getName()==null){
            throw new IllegalArgumentException("書本名稱為空");
        }
        if(!Objects.isNull(bookRepository.findByName(book.getName()))){
            throw new IllegalArgumentException("書本名稱重複");
        }
        bookRepository.save(book);
        return "創建成功";
    }

    /**
     * 依據id更新書本
     * @param id
     * @param book
     * @return
     */
    @Override
    public String updateById(Integer id,Book book) {
        if(bookRepository.findById(id).orElse(null)==null){
            throw new IllegalArgumentException("書本Id不存在，無法更新");
        }
        book.setId(id);
        bookRepository.save(book);
        return "更新成功";
    }


    /**
     * 依據書本id刪除資料
     * @param id
     * @return
     */
    @Override
    public String deleteById(Integer id) {
        if(bookRepository.findById(id).orElse(null)==null){
            throw new IllegalArgumentException("書本Id不存在，無法刪除");
        }
        bookRepository.deleteById(id);
        return "刪除成功";
    }

    @Override
    public List<Book> read() {
        List<Book> list = new ArrayList<>();
        Iterator<Book> its =bookRepository.findAll().iterator();
        while (true) {
            if (its.hasNext()) {
               list.add(its.next());
            } else {
                break;
            }
        }
        return list;
    }
}
