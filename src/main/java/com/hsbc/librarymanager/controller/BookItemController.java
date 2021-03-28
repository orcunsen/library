package com.hsbc.librarymanager.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import com.hsbc.librarymanager.dto.BookItemDto;
import com.hsbc.librarymanager.mapper.BookItemMapper;
import com.hsbc.librarymanager.service.BookItemService;
import com.hsbc.librarymanager.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/bookItem")
@Slf4j
@Validated
public class BookItemController {

  @Autowired
  private BookItemService bookItemService;

  @Autowired
  private BookItemMapper bookItemMapper;

  @Autowired
  private BookService bookService;

  @ApiOperation(value = "Add book item", nickname = "addBookItem", response = ResponseEntity.class)
  @PostMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> addBookItem(@RequestBody @Valid BookItemDto bookItemDto) {
    log.info("Received a call to add book item.");
    bookItemService.addBookItem(bookItemMapper.fromBookItemDto(bookItemDto));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation(
      value = "Get book item by id",
      nickname = "getBookItemById",
      response = ResponseEntity.class)
  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<BookItemDto> getBookItemById(
      @NotNull @ApiParam(required = true) @PathVariable("id") Long id) {
    log.info("Received a call to get book item by id for id: {}.", id);
    return ok().body(bookItemMapper.toBookItemDto(bookItemService.getBookItemById(id)));
  }

  @ApiOperation(
      value = "Get book item by book id",
      nickname = "getBookItemByBookId",
      response = ResponseEntity.class)
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookItemDto>> getBookItemByBookId(@NotNull @RequestParam Long bookId) {
    log.info("Received a call to get book item by id for book id: {}.", bookId);
    return ok().body(bookItemMapper.toBookItemDtoList(bookItemService.getBookItemByBookId(bookId)));
  }

  @ApiOperation(
      value = "Update book item as destroyed by id",
      nickname = "updateBookItemAsDestroyed",
      response = ResponseEntity.class)
  @PutMapping(path = "/update/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateBookItemAsDestroyed(@NotNull @RequestParam Long id) {
    log.info("Received a call to update book item as destroyed by id for id: {}.", id);
    bookItemService.updateBookItemAsDestroyed(id);
    return ok().build();
  }
}
