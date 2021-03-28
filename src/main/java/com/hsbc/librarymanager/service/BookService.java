package com.hsbc.librarymanager.service;

import static com.hsbc.librarymanager.exception.ErrorRegistry.BOOK_NOT_FOUND;

import com.hsbc.librarymanager.exception.LibraryManagerException;
import com.hsbc.librarymanager.repository.BookRepository;
import com.hsbc.librarymanager.repository.entity.Book;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public void addBook(Book book) {
    log.info("Method addBook started.");
    bookRepository.save(book);
  }

  public Book getBookById(Long id) {
    log.info("Method getBookById started for id: {}.", id);
    return bookRepository
        .findById(id)
        .orElseThrow(() -> new LibraryManagerException(BOOK_NOT_FOUND));
  }

  public List<Book> getBookByTitle(String title) {
    log.info("Method getBookByTitle started for title: {}.", title);
    List<Book> bookList = bookRepository.findByTitle(title);

    if (bookList.isEmpty()) {
      throw new LibraryManagerException(BOOK_NOT_FOUND);
    }

    return bookList;
  }

  public List<Book> getBookByAuthor(String author) {
    log.info("Method getBookByAuthor started for author: {}.", author);
    List<Book> bookList = bookRepository.findByAuthor(author);

    if (bookList.isEmpty()) {
      throw new LibraryManagerException(BOOK_NOT_FOUND);
    }

    return bookList;
  }
}
