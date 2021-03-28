package com.hsbc.librarymanager.controller;

import static com.hsbc.librarymanager.util.ModelFactory.buildRental;
import static com.hsbc.librarymanager.util.ModelFactory.buildRentalDto;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hsbc.librarymanager.dto.RentalDto;
import com.hsbc.librarymanager.mapper.RentalMapper;
import com.hsbc.librarymanager.repository.entity.Rental;
import com.hsbc.librarymanager.service.RentalService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class RentalControllerTest {

  @Mock
  private RentalService rentalService;

  @Mock
  private RentalMapper rentalMapper;

  @InjectMocks
  private RentalController rentalController;

  @Test
  public void rentABook_success() {
    RentalDto rentalDto = buildRentalDto();
    Rental rental = buildRental();
    when(rentalMapper.fromRentalDto(rentalDto)).thenReturn(rental);

    rentalController.rentABook(rentalDto);

    verify(rentalMapper).fromRentalDto(rentalDto);
    verify(rentalService).rentABook(rental);
  }

  @Test
  public void getRentalById_success() {
    RentalDto rentalDto = buildRentalDto();
    Rental rental = buildRental();
    when(rentalService.getRentalById(1L)).thenReturn(rental);
    when(rentalMapper.toRentalDto(rental)).thenReturn(rentalDto);

    rentalController.getRentalById(1L);

    verify(rentalService).getRentalById(1L);
    verify(rentalMapper).toRentalDto(rental);
  }

  @Test
  public void returnABook_success() {
    doNothing().when(rentalService).returnABook(1L);

    rentalService.returnABook(1L);

    verify(rentalService).returnABook(1L);
  }
}
