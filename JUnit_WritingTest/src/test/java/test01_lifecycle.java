import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class test01_lifecycle {
  @BeforeAll
  void initAll() {
	System.out.println("1. adım");
  }
  @BeforeEach
  void init() {
	System.out.println("2. adım repeat");
  }


  @Test
  void excampleTest() {
	System.out.println("Test işlemi yapıldı");
  }
  @Test
  void ornekBasarisizTest() {
	fail("Test başarısız oldu");
	System.out.println("Test işlemi yapıldı");
  }
  @Test//kapatılmış test
  @Disabled("Test kapatıldı")
  void ornekKapatilmisTest() {
	System.out.println("Test işlemi yapıldı");
  }
  @Test
  void abortedTest() {
	//bu test yarı yolda durdurulacak
	assertEquals(4, "Onur".length());
	fail("yarı yolda bırakıldı");
  }

/*
  her test bittikten sonra çalışır
   */
  @AfterEach
  void sonCalisanRepeat() {
	System.out.println("Her testten sonra çalışırım");
  }

  /*
  tüm testler bittikten sonra çalışır
   */
  @AfterAll
  void sonCalisanAll() {
	System.out.println("Ben en son çalışırım");
  }


}
