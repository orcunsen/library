package com.hsbc.librarymanager.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import com.hsbc.librarymanager.dto.ReaderDto;
import com.hsbc.librarymanager.mapper.ReaderMapper;
import com.hsbc.librarymanager.service.ReaderService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/reader")
@Slf4j
@Validated
public class ReaderController {

  @Autowired
  private ReaderService readerService;

  @Autowired
  private ReaderMapper readerMapper;

  @ApiOperation(value = "Add reader", nickname = "addReader", response = ResponseEntity.class)
  @PostMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> addReader(@RequestBody @Valid ReaderDto readerDto) {
    log.info("Received a call to add reader.");
    readerService.addReader(readerMapper.fromReaderDto(readerDto));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation(
      value = "Get reader by Id",
      nickname = "getReaderById",
      response = ResponseEntity.class)
  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<ReaderDto> getReaderById(
      @NotNull @ApiParam(required = true) @PathVariable("id") Long id) {
    log.info("Received a call to get reader by id for id: {}.", id);
    return ok().body(readerMapper.toReaderDto(readerService.getReaderById(id)));
  }
}
