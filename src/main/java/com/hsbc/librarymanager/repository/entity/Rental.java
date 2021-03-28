package com.hsbc.librarymanager.repository.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rental")
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "book_item_id", nullable = false)
  private Long bookItemId;

  @NotNull
  @Column(name = "reader_id", nullable = false)
  private Long readerId;

  @NotNull
  @Column(name = "rental_time", nullable = false)
  private Timestamp rentalTime;

  @Column(name = "return_time")
  private Timestamp returnTime;
}
