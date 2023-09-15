package com.muhammet.springunit_integ_test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MusteriSaveDto {
  String ad;
  String adres;
  String telefon;
}
