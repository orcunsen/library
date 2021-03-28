package com.hsbc.librarymanager.mapper;

import static com.hsbc.librarymanager.util.ModelFactory.buildReader;
import static com.hsbc.librarymanager.util.ModelFactory.buildReaderDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.hsbc.librarymanager.dto.ReaderDto;
import com.hsbc.librarymanager.repository.entity.Reader;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReaderMapperTest {

  ReaderMapper readerMapper = new ReaderMapperImpl();

  @Test
  public void fromReaderDto() {
    ReaderDto readerDto = buildReaderDto();

    Reader reader = readerMapper.fromReaderDto(readerDto);

    assertNotNull(reader);
    assertEquals(reader.getName(), readerDto.getName());
    assertEquals(reader.getLastName(), readerDto.getLastName());
  }

  @Test
  public void toReaderDto() {
    Reader reader = buildReader();

    ReaderDto readerDto = readerMapper.toReaderDto(reader);

    assertNotNull(readerDto);
    assertEquals(readerDto.getName(), reader.getName());
    assertEquals(readerDto.getLastName(), reader.getLastName());
  }
}
