package com.hsbc.librarymanager.util;

import static com.hsbc.librarymanager.util.TestConstants.BOOK_AUTHOR;
import static com.hsbc.librarymanager.util.TestConstants.BOOK_TITLE;
import static com.hsbc.librarymanager.util.TestConstants.PUBLICATION_YEAR;
import static com.hsbc.librarymanager.util.TestConstants.READER_LASTNAME;
import static com.hsbc.librarymanager.util.TestConstants.READER_NAME;

import com.hsbc.librarymanager.dto.BookDto;
import com.hsbc.librarymanager.dto.BookItemDto;
import com.hsbc.librarymanager.dto.ReaderDto;
import com.hsbc.librarymanager.dto.RentalDto;
import com.hsbc.librarymanager.enums.BookItemStatus;
import com.hsbc.librarymanager.repository.entity.Book;
import com.hsbc.librarymanager.repository.entity.BookItem;
import com.hsbc.librarymanager.repository.entity.Reader;
import com.hsbc.librarymanager.repository.entity.Rental;
import java.sql.Timestamp;

public class ModelFactory {

  public static BookDto buildBookDto() {
    return BookDto.builder()
        .title(BOOK_TITLE)
        .author(BOOK_AUTHOR)
        .publicationYear(PUBLICATION_YEAR)
        .build();
  }

  public static Book buildBook() {
    return Book.builder()
        .id(1L)
        .title(BOOK_TITLE)
        .author(BOOK_AUTHOR)
        .publicationYear(PUBLICATION_YEAR)
        .build();
  }

  public static BookItemDto buildBookItemDto() {
    return BookItemDto.builder().bookId(1L).status(BookItemStatus.AVAILABLE).build();
  }

  public static BookItem buildBookItem() {
    return BookItem.builder().id(1L).bookId(1L).status(BookItemStatus.AVAILABLE).build();
  }

  public static RentalDto buildRentalDto() {
    return RentalDto.builder().bookItemId(1L).readerId(1L).build();
  }

  public static Rental buildRental() {
    return Rental.builder()
        .id(1L)
        .bookItemId(1L)
        .readerId(1L)
        .rentalTime(new Timestamp(System.currentTimeMillis()))
        .build();
  }

  public static ReaderDto buildReaderDto() {
    return ReaderDto.builder().name(READER_NAME).lastName(READER_LASTNAME).build();
  }

  public static Reader buildReader() {
    return Reader.builder().id(1L).name(READER_NAME).lastName(READER_LASTNAME).build();
  }
}
