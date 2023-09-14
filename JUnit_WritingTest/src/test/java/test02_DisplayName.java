import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
@DisplayName("Bu sinif kullanici veritabani için testler içerir")
public class test02_DisplayName {
  @Test
  void findAllByUsernameTest() {
	System.out.println("findAllByUsernameTest");
  }

  @Nested
  class nestedClass {
	@Test
	void testDeneme() {
	  System.out.println("nestedClass");
	}

  }
}
