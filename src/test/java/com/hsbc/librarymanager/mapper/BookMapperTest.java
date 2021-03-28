package com.hsbc.librarymanager.mapper;

import static com.hsbc.librarymanager.util.ModelFactory.buildBook;
import static com.hsbc.librarymanager.util.ModelFactory.buildBookDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.hsbc.librarymanager.dto.BookDto;
import com.hsbc.librarymanager.repository.entity.Book;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookMapperTest {

  BookMapper bookMapper = new BookMapperImpl();

  @Test
  public void fromBookDto() {
    BookDto bookDto = buildBookDto();

    Book book = bookMapper.fromBookDto(bookDto);

    assertNotNull(book);
    assertEquals(book.getTitle(), bookDto.getTitle());
    assertEquals(book.getAuthor(), bookDto.getAuthor());
    assertEquals(book.getPublicationYear(), bookDto.getPublicationYear());
  }

  @Test
  public void toBookDtoList() {
    Book book = buildBook();

    List<BookDto> bookDtoList = bookMapper.toBookDtoList(List.of(book));

    assertNotNull(bookDtoList);
    assertEquals(bookDtoList.get(0).getTitle(), book.getTitle());
    assertEquals(bookDtoList.get(0).getAuthor(), book.getAuthor());
    assertEquals(bookDtoList.get(0).getPublicationYear(), book.getPublicationYear());
  }
}
