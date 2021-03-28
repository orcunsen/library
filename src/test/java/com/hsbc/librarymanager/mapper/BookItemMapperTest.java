package com.hsbc.librarymanager.mapper;

import static com.hsbc.librarymanager.util.ModelFactory.buildBookItem;
import static com.hsbc.librarymanager.util.ModelFactory.buildBookItemDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.hsbc.librarymanager.dto.BookItemDto;
import com.hsbc.librarymanager.repository.entity.BookItem;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookItemMapperTest {

  BookItemMapper bookItemMapper = new BookItemMapperImpl();

  @Test
  public void fromBookItemDto() {
    BookItemDto bookItemDto = buildBookItemDto();

    BookItem bookItem = bookItemMapper.fromBookItemDto(bookItemDto);

    assertNotNull(bookItem);
    assertEquals(bookItem.getBookId(), bookItemDto.getBookId());
    assertEquals(bookItem.getStatus(), bookItemDto.getStatus());
  }

  @Test
  public void toBookItemDtoList() {
    BookItem bookItem = buildBookItem();

    List<BookItemDto> bookItemDtoList = bookItemMapper.toBookItemDtoList(List.of(bookItem));

    assertNotNull(bookItemDtoList);
    assertEquals(bookItemDtoList.get(0).getBookId(), bookItem.getBookId());
    assertEquals(bookItemDtoList.get(0).getStatus(), bookItem.getStatus());
  }
}
