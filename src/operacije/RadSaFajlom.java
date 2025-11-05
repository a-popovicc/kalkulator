package operacije;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class RadSaFajlom {
	
	public static void upisiUFajl(String pomocni,String trenutni) {
		try (FileWriter f=new FileWriter("src/resursi/datoteke/Istorija.txt", true);
				BufferedWriter b=new BufferedWriter(f);
				PrintWriter pw=new PrintWriter(b)){
			
	        pw.println(pomocni+trenutni);
	        pw.println();
	        // prazan red radi čitljivosti			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static String ucitajIzFajla() {
		StringBuilder sadrzaj = new StringBuilder();
		try(FileReader fr=new FileReader("src/resursi/datoteke/Istorija.txt");
				BufferedReader in = new BufferedReader(fr)){
			
		 String linija;
	        while ((linija = in.readLine()) != null) {
	            sadrzaj.append(linija).append("\n");
	        }	
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return sadrzaj.toString();
	}
	public static void obrisiSveIzFajla() {
	    try (FileWriter fw = new FileWriter("src/resursi/datoteke/Istorija.txt", false);
	         PrintWriter pw = new PrintWriter(fw)) {

	        // Ne upisujemo ništa — samo otvaramo fajl i odmah ga zatvaramo.
	        // To briše sav prethodni sadržaj.
	        pw.print("");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
