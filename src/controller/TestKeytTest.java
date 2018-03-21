package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestKeytTest {
	@Test
	public void testCalcPhie() {
		Key k1 = new Key();
		k1.setPrim1(7);
		k1.setPrim2(11);
		assertEquals(60, k1.calcPhiOfN() );
		
	}
	
	@Test
	public void testCalcE() {
		Key k1 = new Key();
		k1.setPrim1(7);
		k1.setPrim2(11);
		assertEquals(7, k1.calcE() );
	}
	
	@Test
	public void testCalcN() {
		Key k1 = new Key();
		k1.setPrim1(7);
		k1.setPrim2(11);
		assertEquals(77, k1.calcN() );
	}
	@Test
	public void testCalcPhi() {
		Key k1 = new Key();
		k1.setPrim1(7);
		k1.setPrim2(11);
		assertEquals(60, k1.calcPhiOfN() );
		k1.setPhi(22);
		k1.calcPhiOfN();
		assertEquals(22, k1.calcPhiOfN() );
	}
	@Test
	public void testCalcD() {
		System.out.println("Heeeereer");
		Key k1 = new Key();
		k1.setPrim1(5);
		k1.setPrim2(11);
		k1.setE(7);
		assertEquals(23, k1.calcD() );
		
		Key k2 = new Key();
		k2.setD(0);
		k2.setPrim1(89);
		k2.setPrim2(107);
		assertEquals(6219, k2.calcD() );
		
		k2.setE(0);
		k2.setD(0);
		k2.setPhi(0);
		k2.setN(0);
		k2.setPrim1(53);
		k2.setPrim2(59);
		assertEquals(2011, k2.calcD() );
		
		System.out.println("test k3");
		Key k3 = new Key();
		k3.setPrim1(17);
		k3.setPrim2(11);
		k3.setE(7);
		assertEquals(23, k3.calcD() );
		
		System.out.println("test k4");
		Key k4 = new Key();
		k4.setPrim1(12589);
		k4.setPrim2(5197);
		k4.setE(68633);
		assertEquals(953, k4.calcD() );
	}
	
	@Test
	public void hardcoreKeyTest() {
		Key k = new Key();
		k.setPrim1(12589);
		k.setPrim2(5197);
		k.calcD();
		k.calcN();
		
		KeyRing kr = new KeyRing();
		kr.addKey(k);
		long cypher = kr.crypt(18262, 0);
		long message = kr.decrypt(cypher, 0);
		assertEquals(message, 18262 );
		
		message=12128913;
		assertEquals(message, kr.decrypt(kr.crypt(message, 0),0) );
		
		message=398987;
		assertEquals(message, kr.decrypt(kr.crypt(message, 0),0) );
	}
	
	@Test
	public void hardcoreKeyTester() {
		Key k = new Key();
		k.setPrim1(761);
		k.setPrim2(599);
		k.calcD();
		k.calcN();
		
		KeyRing kr = new KeyRing();
		kr.addKey(k);
		long cypher = kr.crypt(231, 0);
		long message = kr.decrypt(cypher, 0);
		assertEquals(message, 231 );
		
		message=15;
		assertEquals(message, kr.decrypt(kr.crypt(message, 0),0) );
		
		message=398987;
		assertEquals(message, kr.decrypt(kr.crypt(message, 0),0) );
	}
	@Test
	public void hardcorerERKeyTester() {
		Key k = new Key();
		k.setPrim1(3877);
		k.setPrim2(3533);
		k.calcD();
		k.calcN();
		KeyRing kr = new KeyRing();
		kr.addKey(k);
		
		long message = 981739;
		assertEquals(message, kr.decrypt(kr.crypt(message, 0),0) );
		
		message=981739;
		assertEquals(message, kr.decrypt(kr.crypt(message, 0),0) );
		
		message=812787;
		assertEquals(message, kr.decrypt(kr.crypt(message, 0),0) );
	}
	@Test
	public void hardcorerERERKeyTester() {
		Key k = new Key();
		k.setPrim1(3023 );
		k.setPrim2(1627 );
		k.calcD();
		k.calcN();
		KeyRing kr = new KeyRing();
		kr.addKey(k);
		
		long message = 1;
		assertEquals(message, kr.decrypt(kr.crypt(message, 0),0) );
		
		message=819;
		assertEquals(message, kr.decrypt(kr.crypt(message, 0),0) );
		
		message=210930;
		assertEquals(message, kr.decrypt(kr.crypt(message, 0),0) );
	}
	
	
}
