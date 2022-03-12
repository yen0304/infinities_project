package com.infinitiessoft.test.dao;

import com.infinitiessoft.test.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookRepository extends CrudRepository<Book,Integer> {

    Book findByName(String name);

}
