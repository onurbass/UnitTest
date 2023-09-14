package com.onurbas;

public class Islemler {
  public int topla(int a, int b) {
	return a + b;
  }
  //bolum metodu
  public double bolum(int a, int b) {
	if (b == 0) {
	  throw new ArithmeticException("Bölen 0 olamaz");
	}
	return a / b;
  }
}
