package operacije;

public class Zagrade {
	
	public static String izmedjuZagrada(String izraz) {
		
	    int open = izraz.indexOf('(');
	    int close = izraz.indexOf(')', open); // tražimo zatvorenu zagradu posle otvorene
	    return izraz.substring(open + 1, close);
	    
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
}
