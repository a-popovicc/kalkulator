package main;

public class OperacijeNadStringom {
	public static String kvadriraj(String a) {
		int br=Integer.parseInt(a);
		int resenje=(int)(Math.pow(br, 2));
		return Integer.toString(resenje);
	}	
	public static String korenuj(String a) {
		int br=Integer.parseInt(a);
		return Double.toString(Math.sqrt(br));
	}
	
}
