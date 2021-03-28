package com.hsbc.librarymanager.service;

import static com.hsbc.librarymanager.exception.ErrorRegistry.DESTROYED_BOOK_ITEM;
import static com.hsbc.librarymanager.exception.ErrorRegistry.MAXIMUM_RENTAL_COUNT;
import static com.hsbc.librarymanager.exception.ErrorRegistry.RENTAL_HAS_ALREADY_RETURNED;
import static com.hsbc.librarymanager.exception.ErrorRegistry.RENTAL_NOT_FOUND;
import static com.hsbc.librarymanager.util.ModelFactory.buildBookItem;
import static com.hsbc.librarymanager.util.ModelFactory.buildReader;
import static com.hsbc.librarymanager.util.ModelFactory.buildRental;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hsbc.librarymanager.enums.BookItemStatus;
import com.hsbc.librarymanager.repository.RentalRepository;
import com.hsbc.librarymanager.repository.entity.BookItem;
import com.hsbc.librarymanager.repository.entity.Reader;
import com.hsbc.librarymanager.repository.entity.Rental;
import com.hsbc.librarymanager.util.TestHelper;
import java.sql.Timestamp;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.annotation.ComponentScan;

@ExtendWith(MockitoExtension.class)
@ComponentScan({"com.hsbc.*"})
@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RentalServiceTest {

  @Mock
  private RentalRepository rentalRepository;

  @Mock
  private com.hsbc.librarymanager.service.BookItemService bookItemService;

  @Mock
  private com.hsbc.librarymanager.service.ReaderService readerService;

  @InjectMocks
  private com.hsbc.librarymanager.service.RentalService rentalService;

  @Test
  public void rentABook_success() {
    Reader reader = buildReader();
    BookItem bookItem = buildBookItem();
    when(readerService.getReaderById(1L)).thenReturn(reader);
    when(bookItemService.getBookItemById(1L)).thenReturn(bookItem);
    when(rentalRepository.countByReaderIdAndReturnTimeIsNull(1L)).thenReturn(0);
    when(rentalRepository.save(any(Rental.class))).thenReturn(null);

    rentalService.rentABook(buildRental());

    verify(readerService).getReaderById(1L);
    verify(bookItemService).getBookItemById(1L);
    verify(rentalRepository).countByReaderIdAndReturnTimeIsNull(1L);
    verify(rentalRepository).save(any(Rental.class));
  }

  @Test
  public void rentABook_destroyedBookItem() {
    Reader reader = buildReader();
    BookItem bookItem = buildBookItem();
    bookItem.setStatus(BookItemStatus.DESTROYED);
    when(readerService.getReaderById(1L)).thenReturn(reader);
    when(bookItemService.getBookItemById(1L)).thenReturn(bookItem);

    TestHelper.throwsExceptionCheck(
        () -> rentalService.rentABook(buildRental()), DESTROYED_BOOK_ITEM);
  }

  @Test
  public void rentABook_maximumBorrowingCount() {
    Reader reader = buildReader();
    BookItem bookItem = buildBookItem();
    when(readerService.getReaderById(1L)).thenReturn(reader);
    when(bookItemService.getBookItemById(1L)).thenReturn(bookItem);
    when(rentalRepository.countByReaderIdAndReturnTimeIsNull(1L)).thenReturn(6);

    TestHelper.throwsExceptionCheck(
        () -> rentalService.rentABook(buildRental()), MAXIMUM_RENTAL_COUNT);
  }

  @Test
  public void getRentalById_success() {
    Rental rental = buildRental();
    when(rentalRepository.findById(1L)).thenReturn(of(rental));

    rentalService.getRentalById(1L);

    verify(rentalRepository).findById(1L);
  }

  @Test
  public void getRentalById_notFound() {
    when(rentalRepository.findById(1L)).thenReturn(empty());

    TestHelper.throwsExceptionCheck(() -> rentalService.getRentalById(1L), RENTAL_NOT_FOUND);
  }

  @Test
  public void returnABook_success() {
    Rental rental = buildRental();
    when(rentalRepository.findById(1L)).thenReturn(of(rental));
    when(rentalRepository.save(any(Rental.class))).thenReturn(null);

    rentalService.returnABook(1L);

    verify(rentalRepository).findById(1L);
    verify(rentalRepository).save(any(Rental.class));
  }

  @Test
  public void returnABook_alreadyReturned() {
    Rental rental = buildRental();
    rental.setReturnTime(new Timestamp(System.currentTimeMillis()));
    when(rentalRepository.findById(1L)).thenReturn(of(rental));

    TestHelper.throwsExceptionCheck(
        () -> rentalService.returnABook(1L), RENTAL_HAS_ALREADY_RETURNED);
  }
}
