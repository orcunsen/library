package com.hsbc.librarymanager.repository;

import com.hsbc.librarymanager.repository.entity.Reader;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

  Optional<Reader> findById(Long id);
}
