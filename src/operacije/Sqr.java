package operacije;

public class Sqr {
	
	public static String kvadriraj(String a) {
		
		double br=Double.parseDouble(a);
		double rezultat=Math.pow(br, 2);
		if (rezultat % 1 == 0) {
            return Integer.toString((int) rezultat);
        } else {
            return Double.toString(rezultat);
        }
	}	
	
	public static String korenuj(String a) {
		
		double br=Double.parseDouble(a);
		double rezultat=Math.sqrt(br);
		if (rezultat % 1 == 0) {
            return Integer.toString((int) rezultat);
        } else {
            return Double.toString(rezultat);
        }
	}
	
}
