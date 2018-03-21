package controller;
import view.*;

public class Main {


	public static void main(String[] args) {
		Controller c1 = new Controller();
		
		Key testKey = new Key();
		testKey.setPrim1(11);
		testKey.setPrim2(5);
		testKey.calcN();
		testKey.calcD();
		c1.theKeyRing.addKey(testKey);
		
		testKey = new Key();
		testKey.setPrim1(13);
		testKey.setPrim2(7);
		testKey.calcN();
		testKey.calcD();
		c1.theKeyRing.addKey(testKey);
		testKey = new Key();
		testKey.setE(5);
		testKey.setN(2033);
		c1.theKeyRing.addKey(testKey);

		
		
		View v1 = new View(c1);
		c1.addView(v1);
		
		
	
		
	}

}
