package com.woodtli.spring_data_jpa.repositories;

import com.woodtli.spring_data_jpa.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    Iterable<Todo> findAll();

    long count();
}
