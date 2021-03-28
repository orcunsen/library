package com.hsbc.librarymanager.mapper;

import static com.hsbc.librarymanager.util.ModelFactory.buildRental;
import static com.hsbc.librarymanager.util.ModelFactory.buildRentalDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.hsbc.librarymanager.dto.RentalDto;
import com.hsbc.librarymanager.repository.entity.Rental;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RentalMapperTest {

  RentalMapper rentalMapper = new RentalMapperImpl();

  @Test
  public void fromRentalDto() {
    RentalDto rentalDto = buildRentalDto();

    Rental rental = rentalMapper.fromRentalDto(rentalDto);

    assertNotNull(rental);
    assertEquals(rental.getBookItemId(), rentalDto.getBookItemId());
    assertEquals(rental.getReaderId(), rentalDto.getReaderId());
  }

  @Test
  public void toRentalDto() {
    Rental rental = buildRental();

    RentalDto rentalDto = rentalMapper.toRentalDto(rental);

    assertNotNull(rentalDto);
    assertEquals(rentalDto.getBookItemId(), rental.getBookItemId());
    assertEquals(rentalDto.getReaderId(), rental.getReaderId());
  }
}
