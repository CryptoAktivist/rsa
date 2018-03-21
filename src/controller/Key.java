package controller;


public class Key {
	long e=0;//public Part
	long n=0;//public Part
	long d=0;//private Part
	long phi=0;//private Part
	
	public long getPhi() {
		return phi;
	}
	public void setPhi(long phi) {
		this.phi = phi;
	}
	long p;
	long q;
	
	
	public long getE() {
		return e;
	}
	public void setE(long e) {
		this.e = e;
	}
	public long getN() {
		return n;
	}
	public void setN(long n) {
		this.n = n;
	}
	public long getD() {
		return d;
	}
	public void setD(long d) {
		this.d = d;
	}
	public long getPrim1() {
		return p;
	}
	public void setPrim1(long prim1) {
		this.p = prim1;
	}
	public long getPrim2() {
		return q;
	}
	public void setPrim2(long prim2) {
		this.q = prim2;
	}

	public Key() {
		
	}
	
	public Key(long n, long e) {
		this.n=n;
		this.e=e;
	}
	
	public boolean istItprivateKey() {
		if(d==0) {
			return false;
		}else {
			return true;
		}
	}
	public String toString() {
		String returner="";
		returner +="e:"+e+" n:"+n;
		if(this.istItprivateKey()) {
			returner += " d:"+ d;
		}
		return returner;
		
	}
	public boolean selfTest() {
		if(n==0) {
			return false;
		}
		if(e==0) {
			return false;
		}
		return true;
	}
	
	public long calcN() {
		if(n!=0) {
			return n;
		}
		n=p*q;
		return p*q;
	}
	public long calcE() {
		if(this.e!=0) {
			return this.e;
		}
		long e=0;
		long phi = this.calcPhiOfN();
		long prime =3;
		System.out.println("Search for e" +this.p+" "+this.q);
		while(e==0&&prime<phi) {
			if(phi%prime!=0) {
				e=prime;
			}else {
				prime=KeyCreater.nextPrimes(prime);
			}
		}
		if(e==0) {
			System.out.println("No e found");
		}
		System.out.println("E found=" +e);
		this.e=e;
		return e;
	}
	public long calcPhiOfN() {
		if(this.phi!=0) {
			return phi; 
		}
		return (p-1)*(q-1);
		
	}
	public long calcD() {
		if(this.d!=0) {
			return d; 
		}
		//d muss kleinder als phi of n sein
		//d*e mod phi muss 1 ergeben
		long d=0;
		long phi =this.calcPhiOfN();
		long e = this.calcE();
		long[] inp = {phi, e, phi, 1};
		long[] out;
		while(d==0) {
			out=KeyCreater.euclydianAlg(inp, phi);
			if(out[1]==1) {
				d=out[3];
			}else {
				inp=out;
			}
		}
		this.d=d;
		return d;
	}

}
