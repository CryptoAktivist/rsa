package controller;

import java.math.BigInteger;
import java.util.LinkedList;

public class KeyRing {
	 LinkedList<Key> theKeys = new LinkedList<Key>();
	 public Key getKey(int index){
		 return theKeys.get(index);
		 
	 }
	 public int howManyKeys(){
		 return theKeys.size(); 
	 }
	 public String showAllKeys() {
		 String returner="";
		 for (Key k1 : theKeys) {
			 returner +=k1.toString();
		 }
		 return returner;
	 }
	 public void addKey(Key k1) {
		 theKeys.add(k1);
	 }
	 public void removeKey(Key k1){
		 theKeys.remove(k1);
	 }
	 public long crypt(long message, int keyID){
		 Key key = this.theKeys.get(keyID);
		 long cypher = KeyRing.modulusPower (message, key.e, key.n);
		 return cypher;
	 }
	 public long crypt(long message, Key key){
		 long cypher = KeyRing.modulusPower (message, key.e, key.n);
		 return cypher;
	 }

	 public long decrypt(long cypher, int keyID){
		 Key key = this.theKeys.get(keyID);
		 long message = KeyRing.modulusPower (cypher, key.d, key.n);
	
		 return message;
		 
	 }
	 public long decrypt(long cypher, Key key){
		 long message = KeyRing.modulusPower (cypher, key.d, key.n);
	
		 return message;
		 
	 }

	 
	 public static long modulusPower(long base, long power, long modulus){
		 if(power==0) {
			 return 1;
		 }
		 if(power==1) {
			 return base%modulus;
		 }
		 
		 /* ==== old (slow) ====
		 for(int i=1;i<power;i++) {
			 //System.out.println("test res:" +resultat);
			 resultat=resultat*base;
			 //System.out.println("test res2:" +resultat);
			 resultat=resultat%modulus;
			 //System.out.println("test mod:" +resultat);
		 }*/
		 BigInteger resultatBI = new BigInteger(""+0);
		 BigInteger baseBI = new BigInteger(""+base);
		 BigInteger powerBI = new BigInteger(""+power);
		 BigInteger modulusBI = new BigInteger(""+modulus);
		 
		 resultatBI = baseBI.modPow(powerBI, modulusBI);
	
		 
		 return Long.parseLong( resultatBI.toString() );
	 }
}
