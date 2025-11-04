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

	    if (!proveriZagrade(a)) return false;

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
	public static String racunanje(String str) {
	    // 1️⃣ Zamena Unicode znakova u standardne
	    str = str.replace('\u00D7', '*')  // ×
	             .replace('\u00F7', '/')  // ÷
	             .replace('\u2212', '-'); // −

	    // 2️⃣ Ukloni nevalidne znakove na kraju
	    if (!Character.isDigit(str.charAt(str.length() - 1))
	            && str.charAt(str.length() - 1) != ')'
	            && str.charAt(str.length() - 1) != '%') {
	        str = str.substring(0, str.length() - 1);
	    }

	    try {
	        // 3️⃣ Obrada zagrada rekurzivno
	        while (str.contains("(")) {
	            int open = str.lastIndexOf('(');
	            int close = str.indexOf(')', open);
	            if (close == -1) return "Greška: nema zatvorene zagrade";

	            String unutar = str.substring(open + 1, close);
	            String rez = racunanje(unutar); // rekurzivni poziv
	            str = str.substring(0, open) + rez + str.substring(close + 1);
	        }

	        // 4️⃣ Pretvaranje svih brojeva sa % u odgovarajući decimalni oblik
	        str = obradiProcenat(str);

	        // 5️⃣ Parsiranje brojeva i operatora
	        java.util.List<Double> brojevi = new java.util.ArrayList<>();
	        java.util.List<Character> oper = new java.util.ArrayList<>();
	        StringBuilder broj = new StringBuilder();

	        for (int i = 0; i < str.length(); i++) {
	            char c = str.charAt(i);

	            if (c == '+' || c == '-' || c == '*' || c == '/') {
	                brojevi.add(Double.parseDouble(broj.toString()));
	                oper.add(c);
	                broj.setLength(0);
	            } else {
	                broj.append(c);
	            }
	        }
	        brojevi.add(Double.parseDouble(broj.toString()));

	        // 6️⃣ × i ÷
	        for (int i = 0; i < oper.size();) {
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

	        // 7️⃣ + i -
	        double rezultat = brojevi.get(0);
	        for (int i = 0; i < oper.size(); i++) {
	            char op = oper.get(i);
	            double b = brojevi.get(i + 1);
	            if (op == '+') rezultat += b;
	            else if (op == '-') rezultat -= b;
	        }

	        // 8️⃣ Formatiranje rezultata
	        return (rezultat % 1 == 0) ? Integer.toString((int) rezultat) : Double.toString(rezultat);

	    } catch (Exception e) {
	        return "Greška";
	    }
	}

	// Helper metoda koja konvertuje sve procente u decimalni oblik pre parsiranja
	private static String obradiProcenat(String str) {
	    java.util.regex.Matcher m = java.util.regex.Pattern.compile("(\\d+(?:\\.\\d+)?)%").matcher(str);
	    StringBuffer sb = new StringBuffer();

	    while (m.find()) {
	        double broj = Double.parseDouble(m.group(1));
	        double rez = broj / 100.0;

	        // Ako pre procenta postoji operator + ili - → množi sa prethodnim brojem
	        int start = m.start();
	        if (start > 0) {
	            char pre = str.charAt(start - 1);
	            if (pre == '+' || pre == '-') {
	                // traži prethodni broj
	                int j = start - 2;
	                while (j >= 0 && (Character.isDigit(str.charAt(j)) || str.charAt(j) == '.')) j--;
	                double prethodni = Double.parseDouble(str.substring(j + 1, start - 1));
	                rez = prethodni * broj / 100.0;
	            }
	        }

	        m.appendReplacement(sb, Double.toString(rez));
	    }
	    m.appendTail(sb);
	    return sb.toString();
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
