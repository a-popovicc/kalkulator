package main;

public class OperacijeNadStringom {
	
	
	public static String izmedjuZagrada(String izraz) {
	    int open = izraz.indexOf('(');
	    int close = izraz.indexOf(')', open); // tražimo zatvorenu zagradu posle otvorene
	    return izraz.substring(open + 1, close);
	}
	
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
	public static String autoZatvoriZagrade(String izraz) {
	    int otvorene = 0;
	    int zatvorene = 0;
	    for (char c : izraz.toCharArray()) {
	        if (c == '(') otvorene++;
	        else if (c == ')') zatvorene++;
	    }

	    int razlika = otvorene - zatvorene;
	    if (razlika > 0) {
	        for (int i = 0; i < razlika; i++) izraz += ")";
	    }
	    return izraz;
	}

	public static boolean proveriZagrade(String izraz) {
	    if (izraz == null) return false;

	    int balance = 0;
	    for (int i = 0; i < izraz.length(); i++) {
	        char c = izraz.charAt(i);
	        if (c == '(') {
	            balance++;
	        } else if (c == ')') {
	            balance--;
	            // ako imamo više zatvorenih nego otvorenih → nevažeće
	            if (balance < 0) return false;
	        }
	    }
	    // na kraju mora biti 0 da bi sve bilo upareno
	    return balance == 0;
	}
	public static boolean mozeLiDaRacuna(String a) {
	    if (a == null || a.isEmpty()) return false;

	    // Ako izraz nije validan po zagradama — odmah stop
	    if (!proveriZagrade(a)) return false;

	    // Ako se završava operatorom — ne računaj
	    char last = a.charAt(a.length() - 1);
	    if (last == '\u00F7' || last == '\u00D7' || last == '(') 
	        return false;

	    // Broj operatora — čisto da proverimo da ima smisla računati
	    int brojac = 0;
	    for (int i = 0; i < a.length(); i++) {
	        char c = a.charAt(i);
	        if (c == '+' || c == '\u2212' || c == '\u00F7' || c == '\u00D7')
	            brojac++;
	    }

	    
	    return brojac >= 1;
	}

	/*public static String racunanjePomocnog(String str) {
	   

	    str = str.replace('\u00D7', '*'); 
	    str = str.replace('\u00F7', '/');
	    str = str.replace('\u2212', '-'); 

	    
	    String izraz = str.substring(0, str.length() - 1);

	    double a = 0;
	    double b = 0;
	    double rezultat = 0;

	    try {
	        if (izraz.contains("+")) {
	            String[] delovi = izraz.split("\\+");
	            if (delovi.length == 2) {
	                a = Double.parseDouble(delovi[0]);
	                b = Double.parseDouble(delovi[1]);
	                rezultat = a + b;
	            }
	        } else if (izraz.contains("-")) {
	           
	            String[] delovi = izraz.split("(?<=\\d)-(?=\\d)");
	            if (delovi.length == 2) {
	                a = Double.parseDouble(delovi[0]);
	                b = Double.parseDouble(delovi[1]);
	                rezultat = a - b;
	            }
	        } else if (izraz.contains("*")) {
	            String[] delovi = izraz.split("\\*");
	            if (delovi.length == 2) {
	                a = Double.parseDouble(delovi[0]);
	                b = Double.parseDouble(delovi[1]);
	                rezultat = a * b;
	            }
	        } else if (izraz.contains("/")) {
	            String[] delovi = izraz.split("/");
	            if (delovi.length == 2) {
	                a = Double.parseDouble(delovi[0]);
	                b = Double.parseDouble(delovi[1]);
	                if (b == 0) return "Greška"; // zaštita od deljenja nulom
	                rezultat = a / b;
	            }
	        } else {
	            return ""; // nije prepoznat operator
	        }
	    } catch (NumberFormatException e) {
	        return "Greška"; // nevalidan broj
	    }

	    // Ako rezultat nema decimalni deo, vrati ga kao int
	    if (rezultat % 1 == 0) {
	        return Integer.toString((int) rezultat);
	    } else {
	        return Double.toString(rezultat);
	    }
	}*/
	
	// ja sam napisao kad su dva broja a chat je ispravio kad ih ima vise
	
	public static String racunanje(String str) {
	    // 1️⃣ Zamena Unicode znakova
	    str = str.replace('\u00D7', '*')  // ×
	             .replace('\u00F7', '/')  // ÷
	             .replace('\u2212', '-'); // −

	    // 2️⃣ Ako poslednji znak nije broj ni zagrada, ukloni ga
	    if (!Character.isDigit(str.charAt(str.length() - 1)) && str.charAt(str.length() - 1) != ')') {
	        str = str.substring(0, str.length() - 1);
	    }

	    try {
	        // 3️⃣ Obradi zagrade rekurzivno
	        while (str.contains("(")) {
	            int open = str.lastIndexOf('(');
	            int close = str.indexOf(')', open);
	            if (close == -1) return "Greška: nema zatvorene zagrade";

	            String unutar = str.substring(open + 1, close);
	            String rez = racunanje(unutar); // rekurzivni poziv
	            str = str.substring(0, open) + rez + str.substring(close + 1);
	        }

	        // 4️⃣ Parsiranje brojeva i operatora, uz podršku za negativne brojeve
	        java.util.List<Double> brojevi = new java.util.ArrayList<>();
	        java.util.List<Character> oper = new java.util.ArrayList<>();

	        StringBuilder broj = new StringBuilder();
	        for (int i = 0; i < str.length(); i++) {
	            char c = str.charAt(i);

	            if (c == '+' || c == '*' || c == '/') {
	                brojevi.add(Double.parseDouble(broj.toString()));
	                oper.add(c);
	                broj.setLength(0);
	            } else if (c == '-') {
	                // Minus znak na početku ili posle operatora → deo broja (negativan)
	                if (i == 0 || "+-*/".indexOf(str.charAt(i - 1)) != -1) {
	                    broj.append(c);
	                } else {
	                    brojevi.add(Double.parseDouble(broj.toString()));
	                    oper.add(c);
	                    broj.setLength(0);
	                }
	            } else {
	                broj.append(c);
	            }
	        }
	        brojevi.add(Double.parseDouble(broj.toString()));

	        // 5️⃣ Prvo × i ÷
	        for (int i = 0; i < oper.size(); ) {
	            char op = oper.get(i);
	            if (op == '*' || op == '/') {
	                double a = brojevi.get(i);
	                double b = brojevi.get(i + 1);
	                double res = (op == '*') ? a * b : (b == 0 ? 0 : a / b);
	                brojevi.set(i, res);
	                brojevi.remove(i + 1);
	                oper.remove(i);
	            } else {
	                i++;
	            }
	        }

	        // 6️⃣ Zatim + i -
	        double rezultat = brojevi.get(0);
	        for (int i = 0; i < oper.size(); i++) {
	            char op = oper.get(i);
	            double b = brojevi.get(i + 1);
	            if (op == '+') rezultat += b;
	            else if (op == '-') rezultat -= b;
	        }

	        // 7️⃣ Vraćanje rezultata
	        if (rezultat % 1 == 0) {
	            return Integer.toString((int) rezultat);
	        } else {
	            return Double.toString(rezultat);
	        }

	    } catch (Exception e) {
	        return "Greška";
	    }
	}



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
