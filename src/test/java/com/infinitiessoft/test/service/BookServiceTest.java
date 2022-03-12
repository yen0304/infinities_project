package com.infinitiessoft.test.service;

import com.infinitiessoft.test.dao.BookRepository;
import com.infinitiessoft.test.entity.Book;
import com.infinitiessoft.test.utils.JsonResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Service層測試")
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("測試新增書本")
    @Test
    public void creat() {
        Book book=new Book();
        book.setAuthor("J.K.羅琳");
        book.setTranslator("趙丕慧");
        book.setIsbn("9789573337843");
        book.setName("哈利波特(7)死神的聖物");
        book.setPrice(799);
        book.setPublisher("皇冠");
        assertEquals("創建成功",bookService.creat(book).getMessage());
        assertEquals("新增失敗，書本名稱重複",bookService.creat(book).getMessage());

        book.setName(null);
        assertEquals("新增失敗，書本名稱不能為空",bookService.creat(book).getMessage());
    }

    @DisplayName("測試更新書本")
    @Test
    public void updateById() {
        Book book=new Book();
        book.setAuthor("J.K.羅琳");
        book.setTranslator("趙丕慧");
        book.setIsbn("9789573337546");
        book.setName("哈利波特(6)混血王子的背叛");
        book.setPrice(2000);
        book.setPublisher("皇冠");

        JsonResult result=bookService.updateById(1,book);
        assertEquals("更新成功",result.getMessage());

        Integer price=bookRepository.findById(1).get().getPrice();
        assertEquals(2000,price);

        assertEquals("書本id不存在，無法更新資料",bookService.updateById(28,book).getMessage());
    }

    @DisplayName("測試刪除")
    @Test
    void deleteById() {
        assertEquals("刪除成功",bookService.deleteById(3).getMessage());
        assertNull(bookRepository.findById(3).orElse(null));
    }

    @DisplayName("測試查詢所有書本")
    @Test
    public void read() {
        assertNotNull(bookService.read().getData());
    }
}