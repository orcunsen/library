package com.hsbc.librarymanager.service;

import static com.hsbc.librarymanager.exception.ErrorRegistry.READER_NOT_FOUND;
import static com.hsbc.librarymanager.util.ModelFactory.buildReader;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hsbc.librarymanager.repository.ReaderRepository;
import com.hsbc.librarymanager.repository.entity.Reader;
import com.hsbc.librarymanager.util.TestHelper;
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
public class ReaderServiceTest {

  @Mock
  private ReaderRepository readerRepository;

  @InjectMocks
  private ReaderService readerService;

  @Test
  public void addReader_success() {
    Reader reader = buildReader();
    when(readerRepository.save(reader)).thenReturn(null);

    readerService.addReader(reader);

    verify(readerRepository).save(reader);
  }

  @Test
  public void getReaderById_success() {
    Reader reader = buildReader();
    when(readerRepository.findById(1L)).thenReturn(of(reader));

    readerService.getReaderById(1L);

    verify(readerRepository).findById(1L);
  }

  @Test
  public void getReaderById_notFound() {
    when(readerRepository.findById(1L)).thenReturn(empty());

    TestHelper.throwsExceptionCheck(() -> readerService.getReaderById(1L), READER_NOT_FOUND);
  }
}
