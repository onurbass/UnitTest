package com.muhammet.springunit_integ_test.repository.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblmusteri")
public class Musteri {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String ad;
  String adres;
  String telefon;

}
