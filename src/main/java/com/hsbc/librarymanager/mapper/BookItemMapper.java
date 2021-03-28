package com.hsbc.librarymanager.mapper;

import com.hsbc.librarymanager.dto.BookItemDto;
import com.hsbc.librarymanager.repository.entity.BookItem;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookItemMapper {

  BookItem fromBookItemDto(final BookItemDto bookItemDto);

  BookItemDto toBookItemDto(final BookItem bookItem);

  List<BookItemDto> toBookItemDtoList(final List<BookItem> bookItem);
}
