package com.hsbc.librarymanager.mapper;

import com.hsbc.librarymanager.dto.ReaderDto;
import com.hsbc.librarymanager.repository.entity.Reader;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ReaderMapper {

  Reader fromReaderDto(final ReaderDto readerDto);

  ReaderDto toReaderDto(final Reader reader);
}
