package com.hsbc.librarymanager.repository;

import com.hsbc.librarymanager.repository.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Optional<Book> findById(Long id);

  List<Book> findByTitle(String title);

  List<Book> findByAuthor(String author);
}
