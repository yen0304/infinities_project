package com.infinitiessoft.test.service;

import com.infinitiessoft.test.dao.BookRepository;
import com.infinitiessoft.test.entity.Book;
import com.infinitiessoft.test.utils.JsonResult;
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
    public JsonResult creat(Book book) {
        if(book.getName()==null){
            return new JsonResult(200,"新增失敗，書本名稱不能為空");
        }
        if(!Objects.isNull(bookRepository.findByName(book.getName()))){
            return new JsonResult(200,"新增失敗，書本名稱重複");
        }
        bookRepository.save(book);
        return new JsonResult(200,"創建成功");
    }

    /**
     * 依據id更新書本
     * @param id
     * @param book
     * @return
     */
    @Override
    public JsonResult updateById(Integer id,Book book) {
        if(bookRepository.findById(id).orElse(null)==null){
            return new JsonResult(200,"書本id不存在，無法更新資料");
        }
        book.setId(id);
        bookRepository.save(book);
        return new JsonResult(200,"更新成功",book);
    }


    /**
     * 依據書本id刪除資料
     * @param id
     * @return
     */
    @Override
    public JsonResult deleteById(Integer id) {
        if(bookRepository.findById(id).orElse(null)==null){
            return new JsonResult(200,"書本id不存在，無法進行刪除");
        }
        bookRepository.deleteById(id);
        return new JsonResult(200,"刪除成功");
    }

    @Override
    public JsonResult read() {
        List<Book> list = new ArrayList<>();
        Iterator<Book> its =bookRepository.findAll().iterator();
        while (true) {
            if (its.hasNext()) {
               list.add(its.next());
            } else {
                break;
            }
        }
        return new JsonResult(200,"查詢成功",list);
    }
}
