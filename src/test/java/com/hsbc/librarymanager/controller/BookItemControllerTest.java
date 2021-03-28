package com.hsbc.librarymanager.controller;

import static com.hsbc.librarymanager.util.ModelFactory.buildBookItem;
import static com.hsbc.librarymanager.util.ModelFactory.buildBookItemDto;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hsbc.librarymanager.dto.BookItemDto;
import com.hsbc.librarymanager.mapper.BookItemMapper;
import com.hsbc.librarymanager.repository.entity.BookItem;
import com.hsbc.librarymanager.service.BookItemService;
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
public class BookItemControllerTest {

  @Mock
  private BookItemService bookItemService;

  @Mock
  private BookItemMapper bookItemMapper;

  @InjectMocks
  private BookItemController bookItemController;

  @Test
  public void addBookItem_success() {
    BookItemDto bookItemDto = buildBookItemDto();
    BookItem bookItem = buildBookItem();
    when(bookItemMapper.fromBookItemDto(bookItemDto)).thenReturn(bookItem);
    doNothing().when(bookItemService).addBookItem(bookItem);

    bookItemController.addBookItem(bookItemDto);

    verify(bookItemMapper).fromBookItemDto(bookItemDto);
    verify(bookItemService).addBookItem(bookItem);
  }

  @Test
  public void getBookItemById_success() {
    BookItemDto bookItemDto = buildBookItemDto();
    BookItem bookItem = buildBookItem();
    when(bookItemService.getBookItemById(1L)).thenReturn(bookItem);
    when(bookItemMapper.toBookItemDto(bookItem)).thenReturn(bookItemDto);

    bookItemController.getBookItemById(1L);

    verify(bookItemService).getBookItemById(1L);
    verify(bookItemMapper).toBookItemDto(bookItem);
  }

  @Test
  public void getBookItemByBookId_success() {
    BookItemDto bookItemDto = buildBookItemDto();
    BookItem bookItem = buildBookItem();
    when(bookItemService.getBookItemByBookId(1L)).thenReturn(List.of(bookItem));
    when(bookItemMapper.toBookItemDtoList(List.of(bookItem))).thenReturn(List.of(bookItemDto));

    bookItemController.getBookItemByBookId(1L);

    verify(bookItemService).getBookItemByBookId(1L);
    verify(bookItemMapper).toBookItemDtoList(List.of(bookItem));
  }

  @Test
  public void updateBookItemAsDestroyed_success() {
    doNothing().when(bookItemService).updateBookItemAsDestroyed(1L);

    bookItemController.updateBookItemAsDestroyed(1L);

    verify(bookItemService).updateBookItemAsDestroyed(1L);
  }
}
