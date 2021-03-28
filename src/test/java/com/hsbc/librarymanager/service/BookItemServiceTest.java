package com.hsbc.librarymanager.service;

import static com.hsbc.librarymanager.exception.ErrorRegistry.BOOK_ITEM_NOT_FOUND;
import static com.hsbc.librarymanager.util.ModelFactory.buildBookItem;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hsbc.librarymanager.enums.BookItemStatus;
import com.hsbc.librarymanager.repository.BookItemRepository;
import com.hsbc.librarymanager.repository.entity.BookItem;
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
public class BookItemServiceTest {

  @Mock
  private BookItemRepository bookItemRepository;

  @Mock
  private BookService bookService;

  @InjectMocks
  private BookItemService bookItemService;

  @Test
  public void addBookItem_success() {
    BookItem bookItem = buildBookItem();
    when(bookItemRepository.save(bookItem)).thenReturn(null);

    bookItemService.addBookItem(bookItem);

    verify(bookItemRepository).save(bookItem);
  }

  @Test
  public void getBookItemById_success() {
    BookItem bookItem = buildBookItem();
    when(bookItemRepository.findById(1L)).thenReturn(of(bookItem));

    bookItemService.getBookItemById(1L);

    verify(bookItemRepository).findById(1L);
  }

  @Test
  public void getBookById_notFound() {
    when(bookItemRepository.findById(1L)).thenReturn(empty());

    TestHelper.throwsExceptionCheck(() -> bookItemService.getBookItemById(1L), BOOK_ITEM_NOT_FOUND);
  }

  @Test
  public void getBookItemByBookId_success() {
    BookItem bookItem = buildBookItem();
    when(bookItemRepository.findByBookId(1L)).thenReturn(List.of(bookItem));

    bookItemService.getBookItemByBookId(1L);

    verify(bookItemRepository).findByBookId(1L);
  }

  @Test
  public void getBookItemByBookId_notFound() {
    when(bookItemRepository.findByBookId(1L)).thenReturn(Collections.emptyList());

    TestHelper.throwsExceptionCheck(
        () -> bookItemService.getBookItemByBookId(1L), BOOK_ITEM_NOT_FOUND);
  }

  @Test
  public void updateBookItemAsDestroyed_success() {
    BookItem bookItem = buildBookItem();
    when(bookItemRepository.findById(1L)).thenReturn(of(bookItem));
    bookItem.setStatus(BookItemStatus.DESTROYED);

    bookItemService.updateBookItemAsDestroyed(1L);

    verify(bookItemRepository).save(bookItem);
  }
}
