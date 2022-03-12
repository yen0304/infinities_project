package com.infinitiessoft.test.service;

import com.infinitiessoft.test.entity.Book;
import com.infinitiessoft.test.utils.JsonResult;

public interface BookService {

    JsonResult creat(Book book);

    JsonResult updateById(Integer id,Book book);

    JsonResult deleteById(Integer id);


    JsonResult read();
}
