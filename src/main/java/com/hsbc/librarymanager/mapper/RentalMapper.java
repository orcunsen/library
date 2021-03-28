package com.hsbc.librarymanager.mapper;

import com.hsbc.librarymanager.dto.RentalDto;
import com.hsbc.librarymanager.repository.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RentalMapper {

  Rental fromRentalDto(final RentalDto rentalDto);

  RentalDto toRentalDto(final Rental rental);
}
