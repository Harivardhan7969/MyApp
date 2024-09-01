package com.hari.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hari.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
