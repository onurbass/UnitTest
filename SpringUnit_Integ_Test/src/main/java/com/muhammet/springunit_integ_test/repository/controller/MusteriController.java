package com.muhammet.springunit_integ_test.repository.controller;

import com.muhammet.springunit_integ_test.dto.MusteriSaveDto;
import com.muhammet.springunit_integ_test.repository.entity.Musteri;
import com.muhammet.springunit_integ_test.service.MusteriService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musteri")
@RequiredArgsConstructor
public class MusteriController {

  private final MusteriService musteriService;

  @PostMapping("/save")
  public ResponseEntity<Boolean> save(@RequestBody MusteriSaveDto dto) {
	musteriService.save(dto.getAd(),dto.getAdres(),dto.getTelefon());
	return ResponseEntity.ok(true);
  }

  @GetMapping("/getall")
  public ResponseEntity<List<Musteri>> findAll() {
	return ResponseEntity.ok(musteriService.findAll());
  }
}
