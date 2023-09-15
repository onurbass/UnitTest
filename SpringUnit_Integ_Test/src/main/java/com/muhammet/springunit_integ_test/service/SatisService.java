package com.muhammet.springunit_integ_test.service;

import com.muhammet.springunit_integ_test.repository.ISatisRepository;
import com.muhammet.springunit_integ_test.repository.entity.Satis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SatisService {

  private final ISatisRepository repository;
  private final MusteriService musteriService;

  public Long satis(Long musteriid,String urun,Double fiyat,int adet) throws Exception {
	if (!musteriService.findByMusteriId(musteriid))
	  throw new Exception("Böyle bir müşteri kayıtlı değil. işlem iptal edildi.");
	if (adet > 0 && fiyat > 0) {
	  Satis satis = repository.save(Satis.builder()
										 .adet(adet)
										 .fiyat(fiyat)
										 .urun(urun)
										 .tarih(System.currentTimeMillis())
										 .musteriid(musteriid)
										 .toplamfiyat(fiyat * adet)
										 .build());
	  return satis.getId();
	} else
	  throw new IllegalArgumentException("Adet ve Fiyat bilgilerinde geçersiz giriş");
  }

}
