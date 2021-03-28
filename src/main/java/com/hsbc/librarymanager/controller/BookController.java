package com.hsbc.librarymanager.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import com.hsbc.librarymanager.dto.BookDto;
import com.hsbc.librarymanager.mapper.BookMapper;
import com.hsbc.librarymanager.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/book")
@Slf4j
@Validated
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private BookMapper bookMapper;

  @ApiOperation(value = "Add book", nickname = "addBook", response = ResponseEntity.class)
  @PostMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> addBook(@RequestBody @Valid BookDto bookDto) {
    log.info("Received a call to add book.");
    bookService.addBook(bookMapper.fromBookDto(bookDto));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation(value = "Get book by id", nickname = "getBookById", response = ResponseEntity.class)
  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<BookDto> getBookById(
      @NotNull @ApiParam(required = true) @PathVariable("id") Long id) {
    log.info("Received a call to get book by id for id: {}.", id);
    return ok().body(bookMapper.toBookDto(bookService.getBookById(id)));
  }

  @ApiOperation(
      value = "Get book by title",
      nickname = "getBookByTitle",
      response = ResponseEntity.class)
  @GetMapping(path = "/byTitle", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookDto>> getBookByTitle(
      @NotNull @NotBlank @RequestParam String title) {
    log.info("Received a call to get book by title for title: {}.", title);
    return ok().body(bookMapper.toBookDtoList(bookService.getBookByTitle(title)));
  }

  @ApiOperation(
      value = "Get book by author",
      nickname = "getBookByAuthor",
      response = ResponseEntity.class)
  @GetMapping(path = "/byAuthor", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookDto>> getBookByAuthor(
      @NotNull @NotBlank @RequestParam String author) {
    log.info("Received a call to get book by author for author: {}.", author);
    return ok().body(bookMapper.toBookDtoList(bookService.getBookByAuthor(author)));
  }
}
