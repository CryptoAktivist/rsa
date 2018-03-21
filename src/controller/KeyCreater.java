package controller;

public class KeyCreater {

	public String showPrimes(long start, long limit) {
		String returner ="";
		boolean isPrime;
		long count=0;
        for(long pirme=start;pirme<limit;pirme++) {
        	isPrime = true;
            for(long j=2;j*j<=pirme;j++) {
                if(pirme%j==0){
                	isPrime=false;
                	break;
                }
            }//check loop
            if(isPrime) {
            	 returner +=pirme+",";
            	 count++;
            }
        }//all numbers loop
        System.out.println(count);
   		return returner;
	}
	public static long nextPrimes(long start) {
		long found=0;
		long search=start;
		boolean isPrime;
		while(found==0) {
			search++;
			isPrime=true;
			for(long j=2;j*j<=search;j++) {
			    if(search%j==0){
			    	isPrime=false;
			    	break;
			    }
			}//check loop
			if(isPrime) {
				found=search;	
			}
		}
		return found;
	}
	

	
	
	
	public static long[] euclydianAlg(long[] input, long phi) {
		if (input==null || input.length!=4) {
			System.out.println("Falsche eingabe in euclydianAlg");
			return null;
		}
		
		long[] out = new long[4];
		long x =input[0];
		long y =input[1];
		long a =input[2];
		long b =input[3];
		System.out.println("inp = ("+x+"|"+y+"   "+a+"|"+b+")");
		

		
		long devider = (long) Math.floor(x/y);
		System.out.println("devider:"+devider+"   y:"+y);
		out[0]=input[1];
		out[2]=input[3];
		
		out[1] = x-(devider*y);
		
		out[3] = a-(devider*b);
		if(out[1]<0) {
			out[1]=phi+out[1];
			
		}
		while(out[3]<0) {
			System.out.print("did negative:" + out[3] +" + " + phi + " tp ");
			out[3]=phi+out[3];
			System.out.print(out[3]+"\n");
		}
		
		
		System.out.println("out = ("+ out[0]+"|"+out[1]+")"+"   ("+out[2]+"|"+out[3]+")");
		return out;
		
	}
	
	
}
