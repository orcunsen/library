package com.hsbc.librarymanager.repository;

import com.hsbc.librarymanager.repository.entity.BookItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookItemRepository extends JpaRepository<BookItem, Long> {

  Optional<BookItem> findById(Long id);

  List<BookItem> findByBookId(Long bookId);
}
