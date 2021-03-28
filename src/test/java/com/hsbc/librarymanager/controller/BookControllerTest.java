package com.hsbc.librarymanager.controller;

import static com.hsbc.librarymanager.util.ModelFactory.buildBook;
import static com.hsbc.librarymanager.util.ModelFactory.buildBookDto;
import static com.hsbc.librarymanager.util.TestConstants.BOOK_AUTHOR;
import static com.hsbc.librarymanager.util.TestConstants.BOOK_TITLE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hsbc.librarymanager.dto.BookDto;
import com.hsbc.librarymanager.mapper.BookMapper;
import com.hsbc.librarymanager.repository.entity.Book;
import com.hsbc.librarymanager.service.BookService;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

  @Mock
  private BookService bookService;

  @Mock
  private BookMapper bookMapper;

  @InjectMocks
  private BookController bookController;

  @Test
  public void addBook_success() {
    BookDto bookDto = buildBookDto();
    Book book = buildBook();
    when(bookMapper.fromBookDto(bookDto)).thenReturn(book);

    bookController.addBook(bookDto);

    verify(bookMapper).fromBookDto(bookDto);
    verify(bookService).addBook(book);
  }

  @Test
  public void getBookById_success() {
    BookDto bookDto = buildBookDto();
    Book book = buildBook();
    when(bookService.getBookById(1L)).thenReturn(book);
    when(bookMapper.toBookDto(book)).thenReturn(bookDto);

    bookController.getBookById(1L);

    verify(bookService).getBookById(1L);
    verify(bookMapper).toBookDto(book);
  }

  @Test
  public void getBookByTitle_success() {
    BookDto bookDto = buildBookDto();
    Book book = buildBook();
    when(bookService.getBookByTitle(BOOK_TITLE)).thenReturn(List.of(book));
    when(bookMapper.toBookDtoList(List.of(book))).thenReturn(List.of(bookDto));

    bookController.getBookByTitle(BOOK_TITLE);

    verify(bookService).getBookByTitle(BOOK_TITLE);
    verify(bookMapper).toBookDtoList(List.of(book));
  }

  @Test
  public void getBookByAuthor_success() {
    BookDto bookDto = buildBookDto();
    Book book = buildBook();
    when(bookService.getBookByAuthor(BOOK_AUTHOR)).thenReturn(List.of(book));
    when(bookMapper.toBookDtoList(List.of(book))).thenReturn(List.of(bookDto));

    bookController.getBookByAuthor(BOOK_AUTHOR);

    verify(bookService).getBookByAuthor(BOOK_AUTHOR);
    verify(bookMapper).toBookDtoList(List.of(book));
  }
}
