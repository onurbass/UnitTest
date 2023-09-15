package com.muhammet.springunit_integ_test.service;


import com.muhammet.springunit_integ_test.repository.ISatisRepository;
import com.muhammet.springunit_integ_test.repository.entity.Satis;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SatisServiceTest {

    @InjectMocks
    private SatisService satisService;

    @Mock
    private ISatisRepository repository;
    @Mock
    private MusteriService musteriService;

    @Test
    void save() throws Exception {
        /**
         * satiş içinde findByMusteriId methodu çağım yapıldığı için buraya gönderilen
         * id ile çağrıma cevap vermek doğrudur.
         */
        Mockito.when(musteriService.findByMusteriId(1L)).thenReturn(true);
        Mockito.when(repository.save(ArgumentMatchers.any(Satis.class))).thenReturn(Satis.builder()
                        .id(1L)
                        .fiyat(32D)
                        .toplamfiyat(320D)
                        .musteriid(1L)
                        .adet(10)
                        .urun("Şeker")
                .build());
       Long id = satisService.satis(1L,"Şeker",32D,10);
       Assertions.assertNotNull(id);
    }

    @Test
    void saveMusteriYokIseHataDon(){
        Assertions.assertThrows(Exception.class,()->{
           satisService.satis(1L,"",0D,0);
        });
    }

    @Test
    void saveAdetFiyatYanliIse(){
        Mockito.when(musteriService.findByMusteriId(1L)).thenReturn(true);
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            satisService.satis(1L,"",0D,0);
        });
    }
}
