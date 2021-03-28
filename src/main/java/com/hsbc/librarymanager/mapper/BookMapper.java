package com.hsbc.librarymanager.mapper;

import com.hsbc.librarymanager.dto.BookDto;
import com.hsbc.librarymanager.repository.entity.Book;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookMapper {

  Book fromBookDto(final BookDto bookDto);

  BookDto toBookDto(final Book book);

  List<BookDto> toBookDtoList(final List<Book> book);
}
