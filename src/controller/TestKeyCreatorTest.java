package controller;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestKeyCreatorTest {

	@Test
	public void testNextPrime() {
		assertEquals(2, KeyCreater.nextPrimes(1) );
		assertEquals(3, KeyCreater.nextPrimes(2) );
		assertEquals(7, KeyCreater.nextPrimes(5) );
		assertEquals(163, KeyCreater.nextPrimes(157) );
		assertEquals(179425033, KeyCreater.nextPrimes(179425027) );
	}
	@Test
	public void testJavaMathe() {
		long a = (long) 5.5;
		assertEquals(5,a );
		a = (long) 0.3235;
		assertEquals(0,a );
		a = (long) -5.3235;
		assertEquals(-5,a );
		
		double devider = Math.floor(5/2);
		assertEquals(devider,2);
	}
	
	@Test
	public void euclydianAlg() {
		assertArrayEquals( null, KeyCreater.euclydianAlg(null, 4) );
		long []wrong = {7,1,35};
		assertArrayEquals( null, KeyCreater.euclydianAlg( wrong , 40) );
		long []wrong2 = {7,1,35,4,5};
		assertArrayEquals( null, KeyCreater.euclydianAlg( wrong2 , 40) );
	}
	@Test
	public void euclydianAlg2() {	
		long []inp = {40,7,40,1};
		long []out = {7,5,1,35};
		assertArrayEquals( out, KeyCreater.euclydianAlg(inp, 40) );
		
	}
	@Test
	public void euclydianAlg3() {	
		long []inp = {40,7,40,1};
		
		long []in2 = KeyCreater.euclydianAlg(inp, 40);
		long []out = {5,2,35,6};
		assertArrayEquals( out, KeyCreater.euclydianAlg(in2, 40) );
		
		long []out2 = {2,1,6,23};
		assertArrayEquals( out2, KeyCreater.euclydianAlg(out, 40) );
	}
	

}
