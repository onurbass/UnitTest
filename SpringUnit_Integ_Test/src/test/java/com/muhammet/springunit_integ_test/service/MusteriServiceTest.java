package com.muhammet.springunit_integ_test.service;

import com.muhammet.springunit_integ_test.repository.IMusteriRepository;
import com.muhammet.springunit_integ_test.repository.entity.Musteri;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class MusteriServiceTest {

    @InjectMocks
    private MusteriService musteriService;
    /**
     * Bir method ve Mock olarak yaratılan sınıfların içinde kullanılan tüm bağımlılıklar null olarak döner.
     * çünkü onlar için Mock nesneleri ek bir işlem yapmaz. Bu nedenle bağımlılığı olan tüm bileşenleri
     * tanımlamak ve inject etmek zorundasınız.
     */
    @Mock
    private IMusteriRepository repository;
    @Test
    void save(){
     when(repository.findAll()).thenReturn(new ArrayList<>());
     when(repository.save(ArgumentMatchers.any(Musteri.class))).thenReturn(
             Musteri.builder()
                     .id(1L)
                     .ad("Muhammet")
                     .adres("Ankara")
                     .telefon("0 555 666 9987")
                     .build()
     );
     Musteri musteri = musteriService.save("Muhammet","Ankara","0 555 666 9987");
     assertNotNull(musteri.getId(), "Müşteri kayıt işleminde id NULL dönmüştür");
    }
    @Test
    void saveAdVarIse(){
        when(repository.findAll()).thenReturn(Arrays.asList(Musteri.builder()
                .id(1L)
                .ad("Muhammet")
                .adres("Ankara")
                .telefon("0 555 666 9987")
                .build()));

        assertThrows(IllegalArgumentException.class,()->{
            musteriService.save("Muhammet","Ankara","0 555 666 9987");
        });
    }
}
