package com.hsbc.librarymanager.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import com.hsbc.librarymanager.dto.RentalDto;
import com.hsbc.librarymanager.mapper.RentalMapper;
import com.hsbc.librarymanager.service.RentalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("v1/rental")
@Slf4j
@Validated
public class RentalController {

  @Autowired
  private RentalService rentalService;

  @Autowired
  private RentalMapper rentalMapper;

  @ApiOperation(value = "Rent a book", nickname = "rentABook", response = ResponseEntity.class)
  @PostMapping(path = "/rent", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> rentABook(@RequestBody @Valid RentalDto rentalDto) {
    log.info("Received a call to rent a book.");
    rentalService.rentABook(rentalMapper.fromRentalDto(rentalDto));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation(
      value = "Get rental by id",
      nickname = "getRentalById",
      response = ResponseEntity.class)
  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<RentalDto> getRentalById(
      @NotNull @ApiParam(required = true) @PathVariable("id") Long id) {
    log.info("Received a call to get rental by id for id: {}.", id);
    return ok().body(rentalMapper.toRentalDto(rentalService.getRentalById(id)));
  }

  @ApiOperation(value = "Return a book", nickname = "returnABook", response = ResponseEntity.class)
  @PutMapping(path = "/return/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> returnABook(@NotNull @RequestParam Long id) {
    log.info("Received a call to return a book for book id: {}.", id);
    rentalService.returnABook(id);
    return ok().build();
  }
}
