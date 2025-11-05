package operacije;


public class AnalizaIzraza {
	
	public static String zameniDoPoslednjegOperatora(String izraz, String noviTekst) {
	    int pozicija = -1;

	    for (int i = izraz.length() - 1; i >= 0; i--) {
	        char c = izraz.charAt(i);
	        if (c == '+' || c == '-' || c == '*' || c == '/' ||
	            c == '\u00D7' || c == '\u00F7' || c == '\u2212') {
	            pozicija = i;
	            break;
	        }
	    }

	    // Ako nema operatora, ne diraj ništa
	    if (pozicija == -1) {
	        return izraz;
	    }

	    // Deo posle poslednjeg operatora
	    String ostatak = izraz.substring(pozicija);

	    // Novi string: noviTekst + ostatak
	    return noviTekst + ostatak;
	}

	
	public static int brZnakova(String a) {
		int brojac = 0;
	    for (int i = 0; i < a.length(); i++) {
	        char c = a.charAt(i);
	        if (c == '+' || c == '\u2212' || c == '\u00F7' || c == '\u00D7') {
	            brojac++;
	        }
	    }
	    return brojac;
	}
	
	
	public static boolean mozeLiDaRacuna(String a) {
	    if (a == null || a.isEmpty()) return false;

	    if (!Zagrade.proveriZagrade(a)) return false;

	    char last = a.charAt(a.length() - 1);

	    // Ako se završava operatorom (osim %)
	    if (last == '\u00F7' || last == '\u00D7' || last == '(')
	        return false;

	    // Ako se završava na procenat → validno
	    if (last == '%') return true;

	    // Ako ima procenat negde u sredini (npr. "200+10%")
	    if (a.contains("%")) {
	        int poz = a.indexOf('%');
	        boolean pre = (poz > 0 && Character.isDigit(a.charAt(poz - 1)));
	        boolean posle = (poz < a.length() - 1 &&
	                         (Character.isDigit(a.charAt(poz + 1)) || a.charAt(poz + 1) == ')'));
	        if (pre || posle) return true;
	    }

	    int brojac = 0;
	    for (int i = 0; i < a.length(); i++) {
	        char c = a.charAt(i);
	        if (c == '+' || c == '\u2212' || c == '\u00F7' || c == '\u00D7')
	            brojac++;
	    }

	    return brojac >= 1;
	}
}
