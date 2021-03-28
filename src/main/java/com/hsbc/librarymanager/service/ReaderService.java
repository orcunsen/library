package com.hsbc.librarymanager.service;

import static com.hsbc.librarymanager.exception.ErrorRegistry.READER_NOT_FOUND;

import com.hsbc.librarymanager.exception.LibraryManagerException;
import com.hsbc.librarymanager.repository.ReaderRepository;
import com.hsbc.librarymanager.repository.entity.Reader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReaderService {

  @Autowired
  private ReaderRepository readerRepository;

  public void addReader(Reader reader) {
    log.info("Method addReader started.");
    readerRepository.save(reader);
  }

  public Reader getReaderById(Long id) {
    log.info("Method getReaderById started for id: {}", id);
    return readerRepository
        .findById(id)
        .orElseThrow(() -> new LibraryManagerException(READER_NOT_FOUND));
  }
}
