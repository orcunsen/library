package com.hsbc.librarymanager.service;

import static com.hsbc.librarymanager.exception.ErrorRegistry.BOOK_ITEM_NOT_FOUND;

import com.hsbc.librarymanager.enums.BookItemStatus;
import com.hsbc.librarymanager.exception.LibraryManagerException;
import com.hsbc.librarymanager.repository.BookItemRepository;
import com.hsbc.librarymanager.repository.entity.BookItem;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookItemService {

  @Autowired
  private BookItemRepository bookItemRepository;

  @Autowired
  private BookService bookService;

  public void addBookItem(BookItem bookItem) {
    log.info("Method addBookItem started.");
    bookService.getBookById(bookItem.getBookId());
    bookItem.setStatus(BookItemStatus.AVAILABLE);
    bookItemRepository.save(bookItem);
  }

  public BookItem getBookItemById(Long id) {
    log.info("Method getBookItemById started for id: {}.", id);
    return bookItemRepository
        .findById(id)
        .orElseThrow(() -> new LibraryManagerException(BOOK_ITEM_NOT_FOUND));
  }

  public List<BookItem> getBookItemByBookId(Long bookId) {
    log.info("Method getBookItemByBookId started for book id: {}.", bookId);
    bookService.getBookById(bookId);
    List<BookItem> bookItemList = bookItemRepository.findByBookId(bookId);

    if (bookItemList.isEmpty()) {
      throw new LibraryManagerException(BOOK_ITEM_NOT_FOUND);
    }

    return bookItemList;
  }

  public void updateBookItemAsDestroyed(Long id) {
    log.info("Method updateBookItemAsDestroyed started for id: {}.", id);
    BookItem bookItem = getBookItemById(id);
    bookItem.setStatus(BookItemStatus.DESTROYED);
    bookItemRepository.save(bookItem);
  }
}
