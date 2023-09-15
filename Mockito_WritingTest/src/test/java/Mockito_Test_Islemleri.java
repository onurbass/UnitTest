import com.muhammet.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.*;

import static org.mockito.Mockito.*;
public class Mockito_Test_Islemleri {
    /**
     * class Islem{
     *     public int topla(int a, int b){
     *     return a+b;
     *     }
     * }
     * mock(Islem.class); -> sanal bir nesne yaratır
     * Islem islem = mock(Islem.class);
     * islem.topla(1,2); -> 0 sanal bir sınıv ve method olduğu için cağrımlar işe yaramazlar.
     * ---> Test edilecek methodun tüm diğer bileşenlerden soyutlanması gereklidir.
     * Satış İşlemi -> Ürün Servisi ile bağıntı var, Müşteri Servisinden bağıntı var,
     * Stok Servisi, Ödeme servisi, Kargo servisi, vb.
     * *** mock nesneleri takip edilebiliyorlar. bu nedenle bir nesne içinde olan biteni analiz
     * edebiliyorsunuz.
     *
     */
    @Test
    void ilkMockTest(){
        /**
         * Mock yapacağınız nesnenin sınıfını yazarak ondan bir nesne oluşturmak gerekiyor
         */
        List<String> mockListem = mock(ArrayList.class);
        /**
         * Burada oluşan nesne sanal ve takip edilen türde bir nesnedir. böyleliklte kontrol
         * sağlayabiliyor olacağız.
         */
        mockListem.add("Java");
        mockListem.clear();
        System.out.println("İlk değer......: "+ mockListem.get(0));
        /**
         * Artık işlem yapılan nesnemizin kontrol edilmesi gerekiyor. mesela bir add() method
         * çağrımı yapılmış mı?
         */
        verify(mockListem).add("Java");
        verify(mockListem).clear();
        //verify(mockListem).add("Ali");

    }
    @Test
    void cevapDonTest(){
        List<String> mockListem = mock(ArrayList.class);
        /**
         * !!! DİKKAT !!!
         * Bir mock içinde yapacağınız işlemlerin sıralması önemlidir. bu nedenle yapılacak
         * işlemleri adım adım yazmalısınız. Örnek;
         * bir ekleme işleminden sonra yada hiç ekleme yapmadan bir methodun cevap dönmesini
         * istiyor isek. önce şunu yazmalıyız. "Eğer birisi seni çeğırır ise şunu dön" şeklinde
         * kurgu yapmalı buna göre işlemlere devam etmeliyiz.
         */
        // mockListem.add("Java");
        /**
         * when -> thenReturn
         */
        when(mockListem.get(0)).thenReturn("Java");
        System.out.println("ilk değer.......: "+ mockListem.get(0));
        System.out.println("ikinci değer....: "+ mockListem.get(1));

        Assertions.assertEquals("Java",mockListem.get(0));
    }
    @Test
    void bagimsizRastGeleDegiskenTest(){
        /**
         * findById(Long id) -> musteri dönüyor.
         * ben method içinde 100.000 adet kayıt için test yapacağım. DİKKAT bir önceki işlemde
         * 1 değer için ne döneceğini belirttim. peki 100.000 kayıt için tek tek değer mi
         * dönmeliyim.
         * Böyle durumlarda, değerin bilinmediği genel bir değer ataması yapılacağı durumlarda
         * herhangibir DataType girdisi ile dönüş yap.
         */
        Map<Integer,String> mockMap = mock(HashMap.class);
        when(mockMap.get(anyInt())).thenReturn("Bir şeyler dön");
        System.out.println("bir değer......: "+ mockMap.get(434));
        //System.out.println("bir değer......: "+ mockMap.get(8));
        /**
         * Bir method çağrımının hangi değer ile çağırıldıuğı önemli değil sadece
         * çağrım yapılmış olması yeterli ile bunu kullanabiliriz.         *
         */
        verify(mockMap).get(anyInt());
    }
    @Test
    void cagrimSayisinaGoreTest(){
        LinkedList mockListem = mock(LinkedList.class);
        mockListem.add("once");

        mockListem.add("Java");
        mockListem.add("Java");

        mockListem.add("three times");
        mockListem.add("three times");
        mockListem.add("three times");

        verify(mockListem).add("once");
        verify(mockListem,times(2)).add("Java");
        verify(mockListem,times(1)).add("once");

        verify(mockListem,never()).add("Ahmet HOCA");
        // En fazla 1 kere çapılmış olmalı
        verify(mockListem,atMostOnce()).add("once");
        // 1. kere çağrımı yapılmış olmalı idi ancak 2 kez yapıldığı için fail olur.
        // verify(mockListem,atMostOnce()).add("Java");
        // en az bir kere çağırımı yapılmış olmalı.
        verify(mockListem,atLeastOnce()).add("Java");
        verify(mockListem,atLeastOnce()).add("once");
        verify(mockListem,atLeastOnce()).add("three times");
        // en az xx kadar çağırım yapılmış olmalı
        verify(mockListem,atLeast(2)).add("Java");
        // en fazla xx kadar çağrım yapılmış olmalı
        verify(mockListem,atMost(3)).add("three times");
    }
    @Test
    void istisnaDondurmek(){
        List<String> mockList = mock(ArrayList.class);
        /**
         * when -> then
         * do -> when
         */
        doThrow(new RuntimeException("Olmadı dostum :(")).when(mockList).clear();
        mockList.clear();
    }
    @Test
    void istisnaFirlatmak(){
        List<String> isimler = mock(ArrayList.class);
        when(isimler.get(0)).thenReturn("Ahmet");
        when(isimler.get(1)).thenThrow(new ArrayIndexOutOfBoundsException("Sınırları aştın dostum"));
        isimler.get(0);
        isimler.get(1);
    }
    @Test
    void belliBirSiradaCalismayiTestEtmek(){
        List<String> mockList = mock(ArrayList.class);

        mockList.add("1. eklenen");
        mockList.add("2. eklenen");

        InOrder inOrderList = inOrder(mockList);

        inOrderList.verify(mockList).add("1. eklenen");
        inOrderList.verify(mockList).add("2. eklenen");

        List<String> musteriList = mock(ArrayList.class);
        List<String> urunList = mock(ArrayList.class);

        musteriList.add("Ahmet BEY");
        urunList.add("Şeker");

        InOrder orderList = inOrder(musteriList,urunList);
        orderList.verify(musteriList).add(anyString());
        orderList.verify(urunList).add("Şeker");


    }
    /**
     * Bazen kodlarımızı sadeleştirmek ve daha efektif kodlama yapabilmek adına anotasyonlarla
     * mock nesnelerimizi tanımlarız.
     */
    @Mock
    private Main main;

    /**
     * SPY nesnesi, casus nesne hem sanal hemde gerçek nesne gibi davranabilir. bu davranışı bizin
     * tanımlamamız gerekmektedir.
     */
    @Test
    void spyKullanimi(){
        List<String> mockList = mock(ArrayList.class);
        List<String> spyList = spy(ArrayList.class);
        List<String> spyNewList = spy(new ArrayList<>());
        spyList.add("Ahmet");
        spyNewList.add("Canan");
        mockList.add("Tekin");
        System.out.println("spyList.....: "+ spyList.get(0));
        System.out.println("spyNewList..: "+ spyNewList.get(0));
        System.out.println("mockList....: "+ mockList.get(0));
    }
    @Test
    void spyDoNotihng(){
        List<String> spyList = spy(ArrayList.class);
        spyList.add("Canan");
        when(spyList.get(0)).thenReturn(null);
        System.out.println("SpyList....: "+ spyList.get(0));

        Main spyMain = spy(new Main());
        doNothing()
                .when(spyMain).islem();
        spyMain.islem();
    }


}
