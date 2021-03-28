package com.hsbc.librarymanager.controller;

import static com.hsbc.librarymanager.util.ModelFactory.buildReader;
import static com.hsbc.librarymanager.util.ModelFactory.buildReaderDto;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hsbc.librarymanager.dto.ReaderDto;
import com.hsbc.librarymanager.mapper.ReaderMapper;
import com.hsbc.librarymanager.repository.entity.Reader;
import com.hsbc.librarymanager.service.ReaderService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ReaderControllerTest {

  @Mock
  private ReaderService readerService;

  @Mock
  private ReaderMapper readerMapper;

  @InjectMocks
  private ReaderController readerController;

  @Test
  public void addReader_success() {
    ReaderDto readerDto = buildReaderDto();
    Reader reader = buildReader();
    when(readerMapper.fromReaderDto(readerDto)).thenReturn(reader);

    readerController.addReader(readerDto);

    verify(readerMapper).fromReaderDto(readerDto);
    verify(readerService).addReader(reader);
  }

  @Test
  public void getReaderById_success() {
    ReaderDto readerDto = buildReaderDto();
    Reader reader = buildReader();
    when(readerService.getReaderById(1L)).thenReturn(reader);
    when(readerMapper.toReaderDto(reader)).thenReturn(readerDto);

    readerController.getReaderById(1L);

    verify(readerService).getReaderById(1L);
    verify(readerMapper).toReaderDto(reader);
  }
}
