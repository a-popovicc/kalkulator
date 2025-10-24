package main;

public class OperacijeNadStringom {
	public static int sabiranje(String s) {
		int suma=0;
		int a;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)!='+') {
				a = Integer.parseInt(s.charAt(i) + "");
				suma=suma+a;
			}
			
		}
		return suma;
	}
}
