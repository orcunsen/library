package com.hsbc.librarymanager.service;

import static com.hsbc.librarymanager.exception.ErrorRegistry.BOOK_NOT_FOUND;
import static com.hsbc.librarymanager.util.ModelFactory.buildBook;
import static com.hsbc.librarymanager.util.TestConstants.BOOK_AUTHOR;
import static com.hsbc.librarymanager.util.TestConstants.BOOK_TITLE;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hsbc.librarymanager.repository.BookRepository;
import com.hsbc.librarymanager.repository.entity.Book;
import com.hsbc.librarymanager.util.TestHelper;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.annotation.ComponentScan;

@ExtendWith(MockitoExtension.class)
@ComponentScan({"com.hsbc.*"})
@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private BookService bookService;

  @Test
  public void addBook_success() {
    Book book = buildBook();
    when(bookRepository.save(book)).thenReturn(null);

    bookService.addBook(book);

    verify(bookRepository).save(book);
  }

  @Test
  public void getBookById_success() {
    Book book = buildBook();
    when(bookRepository.findById(1L)).thenReturn(of(book));

    bookService.getBookById(1L);

    verify(bookRepository).findById(1L);
  }

  @Test
  public void getBookById_notFound() {
    when(bookRepository.findById(1L)).thenReturn(empty());

    TestHelper.throwsExceptionCheck(() -> bookService.getBookById(1L), BOOK_NOT_FOUND);
  }

  @Test
  public void getBookByTitle_success() {
    Book book = buildBook();
    when(bookRepository.findByTitle(BOOK_TITLE)).thenReturn(List.of(book));

    bookService.getBookByTitle(BOOK_TITLE);

    verify(bookRepository).findByTitle(BOOK_TITLE);
  }

  @Test
  public void getBookByTitle_notFound() {
    when(bookRepository.findByTitle(BOOK_TITLE)).thenReturn(Collections.emptyList());

    TestHelper.throwsExceptionCheck(() -> bookService.getBookByTitle(BOOK_TITLE), BOOK_NOT_FOUND);
  }

  @Test
  public void getBookByAuthor_success() {
    Book book = buildBook();
    when(bookRepository.findByAuthor(BOOK_AUTHOR)).thenReturn(List.of(book));

    bookService.getBookByAuthor(BOOK_AUTHOR);

    verify(bookRepository).findByAuthor(BOOK_AUTHOR);
  }

  @Test
  public void getBookByAuthor_notFound() {
    when(bookRepository.findByAuthor(BOOK_AUTHOR)).thenReturn(Collections.emptyList());

    TestHelper.throwsExceptionCheck(() -> bookService.getBookByAuthor(BOOK_AUTHOR), BOOK_NOT_FOUND);
  }
}
