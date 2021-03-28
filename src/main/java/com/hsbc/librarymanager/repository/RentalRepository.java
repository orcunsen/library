package com.hsbc.librarymanager.repository;

import com.hsbc.librarymanager.repository.entity.Rental;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

  Optional<Rental> findById(Long id);

  int countByReaderIdAndReturnTimeIsNull(Long readerId);
}
