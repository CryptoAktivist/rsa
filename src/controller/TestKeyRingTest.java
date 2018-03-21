package controller;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestKeyRingTest {
	
	@Test
	public void testMathe() {
	
		assertEquals(4, KeyRing.modulusPower(2, 2, 200) );
		assertEquals(1953125, KeyRing.modulusPower(5, 9, 2000000) );
		assertEquals(2, KeyRing.modulusPower(5, 9, 3) );
		assertEquals(6, KeyRing.modulusPower(5, 9, 7) );
		assertEquals(5, KeyRing.modulusPower(5, 9, 10) );
		assertEquals(9, KeyRing.modulusPower(5, 9, 11) );
		assertEquals(11, KeyRing.modulusPower(5, 9, 23) );
		assertEquals(8, KeyRing.modulusPower(5, 9, 41) );
		assertEquals(1, KeyRing.modulusPower(10, 0, 41) );
		assertEquals(1, KeyRing.modulusPower(238731, 1, 10) );
		assertEquals(238731, KeyRing.modulusPower(238731, 1, 238732) );
	}

	@Test
	public void testCrytAndDecrypt() {
		KeyRing kr = new KeyRing();
		Key k1 = new Key();
		k1.setD(5);
		k1.setE(11);
		k1.setN(14);
		kr.addKey(k1);
		
		assertEquals(1, kr.decrypt(kr.crypt(1, 0), 0) );
		assertEquals(2, kr.decrypt(kr.crypt(2, 0), 0) );
		assertEquals(3, kr.decrypt(kr.crypt(3, 0), 0) );
		assertEquals(4, kr.decrypt(kr.crypt(4, 0), 0) );
		assertEquals(5, kr.decrypt(kr.crypt(5, 0), 0) );
		assertEquals(13, kr.decrypt(kr.crypt(13, 0), 0) );	
	}
	
	@Test
	public void testCypher() {
		KeyRing kr = new KeyRing();
		Key k1 = new Key();
		k1.setD(5);
		k1.setE(11);
		k1.setN(14);
		kr.addKey(k1);
		
		assertEquals(3, kr.crypt(5, 0) ) ;
		assertEquals(12, kr.crypt(10, 0) ) ;
		assertEquals(10, kr.crypt(12, 0) ) ;
	}
	
	@Test
	public void testownKey() {
		KeyRing kr = new KeyRing();
		Key k1 = new Key();
		k1.setD(6219);
		k1.setE(3);
		k1.setN(9523);
		kr.addKey(k1);
		
		assertEquals(13, kr.decrypt(kr.crypt(13, 0), 0) );
		assertEquals(101, kr.decrypt(kr.crypt(101, 0), 0) );	
		
	}

}
