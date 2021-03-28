package com.hsbc.librarymanager.service;

import static com.hsbc.librarymanager.enums.BookItemStatus.DESTROYED;
import static com.hsbc.librarymanager.exception.ErrorRegistry.DESTROYED_BOOK_ITEM;
import static com.hsbc.librarymanager.exception.ErrorRegistry.MAXIMUM_RENTAL_COUNT;
import static com.hsbc.librarymanager.exception.ErrorRegistry.RENTAL_HAS_ALREADY_RETURNED;
import static com.hsbc.librarymanager.exception.ErrorRegistry.RENTAL_NOT_FOUND;

import com.hsbc.librarymanager.exception.LibraryManagerException;
import com.hsbc.librarymanager.repository.RentalRepository;
import com.hsbc.librarymanager.repository.entity.Rental;
import java.sql.Timestamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private BookItemService bookItemService;

  @Autowired
  private ReaderService readerService;

  public void rentABook(Rental rental) {
    log.info("Method rentABook started.");
    readerService.getReaderById(rental.getReaderId());

    if (bookItemService.getBookItemById(rental.getBookItemId()).getStatus() == DESTROYED) {
      throw new LibraryManagerException(DESTROYED_BOOK_ITEM);
    }

    if (rentalRepository.countByReaderIdAndReturnTimeIsNull(rental.getReaderId()) >= 5) {
      throw new LibraryManagerException(MAXIMUM_RENTAL_COUNT);
    }

    rental.setRentalTime(new Timestamp(System.currentTimeMillis()));
    rentalRepository.save(rental);
  }

  public Rental getRentalById(Long id) {
    log.info("Method getRentalById started for id: {}", id);
    return rentalRepository
        .findById(id)
        .orElseThrow(() -> new LibraryManagerException(RENTAL_NOT_FOUND));
  }

  public void returnABook(Long id) {
    log.info("Method returnABook started for id: {}", id);
    Rental rental = getRentalById(id);

    if (rental.getReturnTime() != null) {
      throw new LibraryManagerException(RENTAL_HAS_ALREADY_RETURNED);
    }
    rental.setReturnTime(new Timestamp(System.currentTimeMillis()));
    rentalRepository.save(rental);
  }
}
