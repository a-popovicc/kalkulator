package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.SwingUtilities;


import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Kalkulator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldPomocni;
	private JTextField textFieldTrenutni;

	private JButton btnDesnaZagrada;
	private JButton btnLevaZagrada;
	private JButton btnCancel;
	private JButton btnC;
	private JButton btnPodeljeno;
	private JButton btnReciprocno;
	private JButton btnNaKvadrat;
	private JButton btn7;
	private JButton btnKoren;
	private JButton btn9;
	private JButton btn8;
	private JButton btn4;
	private JButton btnPuta;
	private JButton btn6;
	private JButton btnMinus;
	private JButton btn5;
	private JButton btn1;
	private JButton btnPlus;
	private JButton btn3;
	private JButton btnProcenat;
	private JButton btn2;
	private JButton btn0;
	private JButton btnZarez;
	private JButton btnJednako;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kalkulator frame = new Kalkulator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private boolean unosOdPocetka = true;
	
	public Kalkulator() {
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelIzlaz = new JPanel(new GridLayout(2,1));
		contentPane.add(panelIzlaz, BorderLayout.NORTH);
		panelIzlaz.setPreferredSize(new Dimension(100,120));
		panelIzlaz.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
		
		textFieldPomocni = new JTextField();
		textFieldPomocni.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldPomocni.setBackground(new Color(255, 255, 255));
		panelIzlaz.add(textFieldPomocni);
		textFieldPomocni.setColumns(10);
		textFieldPomocni.setBorder(null);
		textFieldPomocni.setHorizontalAlignment(JTextField.RIGHT);
		textFieldPomocni.setEditable(false);
		textFieldPomocni.setForeground(Color.gray);
		
		textFieldTrenutni = new JTextField();
		textFieldTrenutni.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textFieldTrenutni.setBackground(new Color(255, 255, 255));
		textFieldTrenutni.setEditable(false);
		panelIzlaz.add(textFieldTrenutni);
		textFieldTrenutni.setColumns(10);
		textFieldTrenutni.setBorder(null);
		textFieldTrenutni.setHorizontalAlignment(JTextField.RIGHT);
		textFieldPomocni.setEditable(false);
		
		textFieldTrenutni.setText("0");
		
		JPanel panelDugmici = new JPanel();
		contentPane.add(panelDugmici, BorderLayout.CENTER);
		panelDugmici.setLayout(new GridLayout(6, 10, 5, 5));
		panelDugmici.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	
		
		btnLevaZagrada = new JButton("(");
		btnLevaZagrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pomocniText = textFieldPomocni.getText();
				

		        if (pomocniText.isEmpty()) {
		            // Ako je pomoćni prazan → samo otvori zagradu
		            textFieldPomocni.setText("(");
		        } else {
		        	
		        	if(pomocniText.startsWith("sqr")) {
						textFieldPomocni.setText(OperacijeNadStringom.kvadriraj(OperacijeNadStringom.izmedjuZagrada(textFieldPomocni.getText()))+"\u00D7(");
						unosOdPocetka=true;
						return;
		        	}
		        	if(pomocniText.startsWith("\u221A")) {
						textFieldPomocni.setText(OperacijeNadStringom.korenuj(OperacijeNadStringom.izmedjuZagrada(textFieldPomocni.getText()))+"\u00D7(");
						unosOdPocetka=true;
						return;
		        	}
		        	
		            char poslednji = pomocniText.charAt(pomocniText.length() - 1);

		            // Ako je poslednji znak operator ili otvorena zagrada → samo dodaj (
		            if (poslednji == '(' || poslednji == '+' || poslednji == '-' || poslednji == '\u00D7' || poslednji == '\u00F7') {
		                textFieldPomocni.setText(pomocniText + "(");
		            }
		            // Ako je poslednji znak zatvorena zagrada → dodaj implicitno množenje
		            else if (poslednji == ')') {
		                textFieldPomocni.setText(pomocniText + "\u00D7(");
		            }
		            // Ako je poslednji znak broj → dodaj implicitno množenje
		            else if (Character.isDigit(poslednji)) {
		                textFieldPomocni.setText(pomocniText + "\u00D7(");
		            }
		        }

		        unosOdPocetka = true; // sledeći broj počinje od početka
		    }
  
		});
		btnLevaZagrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnLevaZagrada);
		btnLevaZagrada.setPreferredSize(new Dimension(50,50));
		
		btnDesnaZagrada = new JButton(")");
		btnDesnaZagrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pomocniText = textFieldPomocni.getText();
		        String trenutniText = textFieldTrenutni.getText();

		        // Ako nema otvorenih zagrada koje nisu zatvorene — nema smisla dodavati )
		        long otvorene = pomocniText.chars().filter(ch -> ch == '(').count();
		        long zatvorene = pomocniText.chars().filter(ch -> ch == ')').count();
		        if (otvorene <= zatvorene) {
		            return;
		        }

		        // Ako je pomoćni prazan — ne može )
		        if (pomocniText.isEmpty()) {
		            return;
		        }

		        char poslednji = pomocniText.charAt(pomocniText.length() - 1);

		        // Ako poslednji znak nije broj ni zagrada, a trenutni tekst jeste broj → dodaj broj pa )
		        if (poslednji == '+' || poslednji == '-' || poslednji == '\u00D7' || poslednji == '\u00F7' || poslednji == '(') {
		            // Ako u trenutnom ima broj — zatvori zagradu posle njega
		            if (!trenutniText.isEmpty() && Character.isDigit(trenutniText.charAt(trenutniText.length() - 1))) {
		                textFieldPomocni.setText(pomocniText + trenutniText + ")");
		            } else {
		                // U suprotnom, ignoriši
		                return;
		            }
		        } else {
		            // Ako je poslednji znak broj ili zatvorena zagrada, samo dodaj )
		            textFieldPomocni.setText(pomocniText + ")");
		        }

		        // Ako može da računa — odmah prikaži rezultat kao Windows kalkulator
		        if (OperacijeNadStringom.mozeLiDaRacuna(textFieldPomocni.getText())) {
		            String rezultat = OperacijeNadStringom.racunanje(textFieldPomocni.getText());
		            textFieldTrenutni.setText(rezultat);
		        }

		        unosOdPocetka = true;
		    }
		});
		btnDesnaZagrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnDesnaZagrada);
		
		btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldPomocni.setText(null);
				textFieldTrenutni.setText("0");
				unosOdPocetka = true;
			}
		});
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnC);
		
		btnCancel = new JButton("<---");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//textFieldTrenutni.getText().substring(0, textFieldTrenutni.getText().length() - 1);
				if(textFieldPomocni.getText().contains("=")) {
					return;
				}
				if(textFieldTrenutni.getText().equals("0")) {
					return;
				}else if(textFieldTrenutni.getText().length()==1) {
					textFieldTrenutni.setText("0");
				}else
					textFieldTrenutni.setText(textFieldTrenutni.getText().substring(0, textFieldTrenutni.getText().length() - 1));
 					
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelDugmici.add(btnCancel);
		//btnCancel.setIcon(new ImageIcon(Kalkulator.class.getResource("/Icons/cancel2.png")));
		
		btnReciprocno = new JButton("1/X");
		btnReciprocno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnReciprocno);
		
		btnNaKvadrat = new JButton("X\u00B2");
		btnNaKvadrat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldPomocni.getText().isEmpty() || textFieldPomocni.getText().contains("=") || textFieldPomocni.getText().startsWith("sqr")) {
					textFieldPomocni.setText("sqr("+textFieldTrenutni.getText()+")");
					textFieldTrenutni.setText(OperacijeNadStringom.kvadriraj(textFieldTrenutni.getText()));
					unosOdPocetka=true;
				return;
				}
				if(textFieldPomocni.getText().endsWith(")")){
					textFieldPomocni.setText(textFieldPomocni.getText()+"\u00D7"+OperacijeNadStringom.kvadriraj(textFieldTrenutni.getText()));
					unosOdPocetka=true;
					return;
				}
				textFieldPomocni.setText(textFieldPomocni.getText()+OperacijeNadStringom.kvadriraj(textFieldTrenutni.getText()));
				textFieldTrenutni.setText(OperacijeNadStringom.kvadriraj(textFieldTrenutni.getText()));
				unosOdPocetka=true;
			}
		});
		btnNaKvadrat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnNaKvadrat);
		
		
		//JLabel label = new JLabel("<html>&radic;9 = 3</html>");

		btnKoren = new JButton("<html>&radic;X</html>");
		btnKoren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldPomocni.getText().isEmpty() || textFieldPomocni.getText().contains("=") || textFieldPomocni.getText().startsWith("\u221A")) {
					textFieldPomocni.setText("\u221A("+textFieldTrenutni.getText()+")");
					textFieldTrenutni.setText(OperacijeNadStringom.korenuj(textFieldTrenutni.getText()));
					unosOdPocetka=true;
				return;
				}
				if(textFieldPomocni.getText().endsWith(")")){
					textFieldPomocni.setText(textFieldPomocni.getText()+"\u00D7"+OperacijeNadStringom.korenuj(textFieldTrenutni.getText()));
					unosOdPocetka=true;
					return;
				}
				textFieldPomocni.setText(textFieldPomocni.getText()+OperacijeNadStringom.korenuj(textFieldTrenutni.getText()));
				textFieldTrenutni.setText(OperacijeNadStringom.korenuj(textFieldTrenutni.getText()));
				unosOdPocetka=true;
				
			}
		});
		btnKoren.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnKoren);
		
		btnPodeljeno = new JButton("\u00F7");
		btnPodeljeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldTrenutni.getText().endsWith(".")) {
		        	textFieldTrenutni.setText(textFieldTrenutni.getText()+"0");
		        }
				if(textFieldPomocni.getText().startsWith("sqr")) {
					textFieldPomocni.setText(OperacijeNadStringom.kvadriraj(OperacijeNadStringom.izmedjuZagrada(textFieldPomocni.getText()))+"\u00F7");
					return;
				}
				if(textFieldPomocni.getText().startsWith("\u221A")) {
					textFieldPomocni.setText(OperacijeNadStringom.korenuj(OperacijeNadStringom.izmedjuZagrada(textFieldPomocni.getText()))+"\u00F7");
					return;
				}
				//bilo bi lakse da uzima resenje direktno iz trenutnog ali sta ako pre operatoa korisnik klikne broj pa se truntni promeni
				String pomocniText = textFieldPomocni.getText();
		        String trenutniText = textFieldTrenutni.getText();
		        try {
			        if(Character.isDigit(pomocniText.charAt(pomocniText.length() - 1))) {
			        	textFieldPomocni.setText(textFieldPomocni.getText()+"\u00F7");
			        	return;
			        }
			        }catch (Exception a) {
						// TODO: handle exception
					}


		        if (pomocniText.isEmpty()) {
		           
		            textFieldPomocni.setText(trenutniText + "\u00F7");
		        } else {
		            char poslednji = pomocniText.charAt(pomocniText.length() - 1);
		            if (poslednji != ')') {
		                textFieldPomocni.setText(pomocniText + trenutniText + "\u00F7");
		            } else {
		                
		                textFieldPomocni.setText(pomocniText + "\u00F7");
		            }
		        }
		        if (OperacijeNadStringom.mozeLiDaRacuna(textFieldPomocni.getText())) {
		            textFieldTrenutni.setText(
		                OperacijeNadStringom.racunanje(textFieldPomocni.getText())
		            );
		        }

		        unosOdPocetka = true;
			}
		});
		btnPodeljeno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelDugmici.add(btnPodeljeno);
		
		btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postaviBroj(7);
			}
		});
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btn7);
		
		btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postaviBroj(8);
			}
		});
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btn8);
		
		btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postaviBroj(9);
			}
		});
		btn9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btn9);
		
		btnPuta = new JButton("\u00D7");
		btnPuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldTrenutni.getText().endsWith(".")) {
		        	textFieldTrenutni.setText(textFieldTrenutni.getText()+"0");
		        }
				if(textFieldPomocni.getText().startsWith("sqr")) {
					textFieldPomocni.setText(OperacijeNadStringom.kvadriraj(OperacijeNadStringom.izmedjuZagrada(textFieldPomocni.getText()))+"\u00D7");
					return;
				}
				if(textFieldPomocni.getText().startsWith("\u221A")) {
					textFieldPomocni.setText(OperacijeNadStringom.korenuj(OperacijeNadStringom.izmedjuZagrada(textFieldPomocni.getText()))+"\u00D7");
					return;
				}
				String pomocniText = textFieldPomocni.getText();
		        String trenutniText = textFieldTrenutni.getText();
		        try {
			        if(Character.isDigit(pomocniText.charAt(pomocniText.length() - 1))) {
			        	textFieldPomocni.setText(textFieldPomocni.getText()+"\u00D7");
			        	return;
			        }
			        }catch (Exception a) {
						// TODO: handle exception
					}


		        if (pomocniText.isEmpty()) {
		            textFieldPomocni.setText(trenutniText + "\u00D7");
		        } else {
		            char poslednji = pomocniText.charAt(pomocniText.length() - 1);

		           
		            if (poslednji != ')') {
		                textFieldPomocni.setText(pomocniText + trenutniText + "\u00D7");
		            } else {
		                
		                textFieldPomocni.setText(pomocniText + "\u00D7");
		            }
		        }
		        
		        if (OperacijeNadStringom.mozeLiDaRacuna(textFieldPomocni.getText())) {
		            textFieldTrenutni.setText(
		                OperacijeNadStringom.racunanje(textFieldPomocni.getText())
		            );
		        }

		        unosOdPocetka = true;
			}
		});
		btnPuta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelDugmici.add(btnPuta);
		
		btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postaviBroj(4);
			}
		});
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btn4);
		
		btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postaviBroj(5);
			}
		});
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btn5);
		
		btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postaviBroj(6);
			}
		});
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btn6);
		
		btnMinus = new JButton("\u2212");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldTrenutni.getText().endsWith(".")) {
				    textFieldTrenutni.setText(textFieldTrenutni.getText() + "0");
				}

				if (textFieldPomocni.getText().startsWith("sqr")) {
				    textFieldPomocni.setText(
				        OperacijeNadStringom.kvadriraj(
				            OperacijeNadStringom.izmedjuZagrada(textFieldPomocni.getText())
				        ) + "\u2212"
				    );
				    return;
				}

				if (textFieldPomocni.getText().startsWith("\u221A")) {
				    textFieldPomocni.setText(
				        OperacijeNadStringom.korenuj(
				            OperacijeNadStringom.izmedjuZagrada(textFieldPomocni.getText())
				        ) + "\u2212"
				    );
				    return;
				}

				String pomocniText = textFieldPomocni.getText();
				String trenutniText = textFieldTrenutni.getText();

				try {
				    if (Character.isDigit(pomocniText.charAt(pomocniText.length() - 1))) {
				        textFieldPomocni.setText(pomocniText + "\u2212");
				        return;
				    }
				} catch (Exception a) {
				    // ignorisi
				}

				// unarni minus ako se završava otvorenom zagradom
				if (!pomocniText.isEmpty() && pomocniText.charAt(pomocniText.length() - 1) == '(' && unosOdPocetka==true) {
				    textFieldPomocni.setText(pomocniText + "-");
				    textFieldTrenutni.setText("0");
				    unosOdPocetka = false;
				    return;
				}

				if (pomocniText.isEmpty()) {
				    textFieldPomocni.setText(trenutniText + "\u2212");
				} else {
				    char poslednji = pomocniText.charAt(pomocniText.length() - 1);

				    if (poslednji != ')') {
				        textFieldPomocni.setText(pomocniText + trenutniText + "\u2212");
				    } else {
				        textFieldPomocni.setText(pomocniText + "\u2212");
				    }
				}

				if (OperacijeNadStringom.mozeLiDaRacuna(textFieldPomocni.getText())) {
				    textFieldTrenutni.setText(
				        OperacijeNadStringom.racunanje(textFieldPomocni.getText())
				    );
				}

				unosOdPocetka = true;
			}
		});
		btnMinus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelDugmici.add(btnMinus);
		
		btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postaviBroj(1);
			}
		});
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btn1);
		
		btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postaviBroj(2);
			}
		});
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btn2);
		
		btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postaviBroj(3);
			}
		});
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btn3);
		
		btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldTrenutni.getText().endsWith(".")) {
		        	textFieldTrenutni.setText(textFieldTrenutni.getText()+"0");
		        }// ova funkcija cuva da ukoliko se slucajno klikne operator pre zavrsenih decimala automatski dodaje .0 u pomocni
				if(textFieldPomocni.getText().startsWith("sqr")) {
					textFieldPomocni.setText(OperacijeNadStringom.kvadriraj(OperacijeNadStringom.izmedjuZagrada(textFieldPomocni.getText()))+"+");
					return;
				}
				if(textFieldPomocni.getText().startsWith("\u221A")) {
					textFieldPomocni.setText(OperacijeNadStringom.korenuj(OperacijeNadStringom.izmedjuZagrada(textFieldPomocni.getText()))+"+");
					return;
				}
				String pomocniText = textFieldPomocni.getText();
		        String trenutniText = textFieldTrenutni.getText();
		        
		        /*try {
		        if(Character.isDigit(pomocniText.charAt(pomocniText.length() - 1))) {
		        	textFieldPomocni.setText(textFieldPomocni.getText()+"+");
		        	return;
		        }
		        }catch (Exception a) {
					// TODO: handle exception
				}*/
		        
		        if (pomocniText.isEmpty()) {
		            // Ako nema ničega, dodaj samo trenutni broj i +
		            textFieldPomocni.setText(trenutniText + "+");
		        } else {
		        	if(Character.isDigit(pomocniText.charAt(pomocniText.length() - 1))) {
			        	textFieldPomocni.setText(textFieldPomocni.getText()+"+");
			        	return;//ovo dolazi kao odgovor na slucaj da se nastavlja opracija nakon kvadriranja koje je u pomocni poslao samo broj bez daljeg operatora
			        }
		            char poslednji = pomocniText.charAt(pomocniText.length() - 1);

		            // Ako poslednji znak NIJE zatvorena zagrada, dodaj broj pre +
		            if (poslednji != ')') {
		                textFieldPomocni.setText(pomocniText + trenutniText + "+");
		            } else {
		                // Ako jeste zatvorena zagrada, samo dodaj +
		                textFieldPomocni.setText(pomocniText + "+");
		            }
		        }

		        // Nakon dodavanja operatora, prikaži eventualan rezultat (ako može)
		        if (OperacijeNadStringom.mozeLiDaRacuna(textFieldPomocni.getText())) {
		            textFieldTrenutni.setText(
		                OperacijeNadStringom.racunanje(textFieldPomocni.getText())
		            );
		        }

		        unosOdPocetka = true;
		    
			}
		});
		btnPlus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelDugmici.add(btnPlus);
		
		btnProcenat = new JButton("%");
		btnProcenat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnProcenat);
		
		btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postaviBroj(0);
			}
		});
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btn0);
		
		btnZarez = new JButton(".");
		btnZarez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldTrenutni.getText().contains(".")) {
					return;
				}else
					textFieldTrenutni.setText(textFieldTrenutni.getText()+".");
			}
		});
		btnZarez.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelDugmici.add(btnZarez);
		
		btnJednako = new JButton("=");
		btnJednako.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
	        String pomocniText = textFieldPomocni.getText();
	        String trenutniText = textFieldTrenutni.getText();
	        String izraz;
	        //ako u pomocnom vec postoji = ondnosno radi se novi klik znaka jednako
				
			if(pomocniText.endsWith("=")) { 
			 return; 
			}
				 
				/*
				 * if(pomocniText.endsWith("=")) {
				 * if(OperacijeNadStringom.brZnakova(pomocniText)<1) { return; }
				 * if(OperacijeNadStringom.brZnakova(pomocniText)>=1) {
				 * textFieldPomocni.setText(
				 * OperacijeNadStringom.zameniDoPoslednjegOperatora(textFieldPomocni.getText(),
				 * textFieldTrenutni.getText()));
				 * textFieldTrenutni.setText(OperacijeNadStringom.racunanje(textFieldPomocni.
				 * getText())); return; }
				 * 
				 * }
				 */

	        // Ako pomoćni prazan stavi trenutni + "="
	        if (pomocniText.isEmpty()) {
	            textFieldPomocni.setText(trenutniText + "=");
	            return;
	        }
	        if(pomocniText.startsWith("sqr")) {
	        	textFieldPomocni.setText(textFieldTrenutni.getText()+"=");
	        	return;
	        }

	        //  Ako pomoćni završava operatorom dodaj trenutni i "=" pa računa
	        char poslednji = pomocniText.charAt(pomocniText.length() - 1);
	        if (poslednji == '+' || poslednji == '\u00D7' || poslednji == '\u2212' || poslednji == '\u00F7') {
	            izraz = pomocniText + trenutniText;
	            izraz = OperacijeNadStringom.autoZatvoriZagrade(izraz);
	            textFieldPomocni.setText(izraz + "=");
	            textFieldTrenutni.setText(OperacijeNadStringom.racunanje(izraz));
	            return;
	        }else if(Character.isDigit(poslednji)) {
	        	izraz=pomocniText;
	        	izraz=OperacijeNadStringom.autoZatvoriZagrade(izraz);
	        	textFieldPomocni.setText(izraz + "=");
	        	textFieldTrenutni.setText(OperacijeNadStringom.racunanje(izraz));
	        	return;
	        }

	        //  Ako se pomoćni završava zatvorenom zagradom samo zatvori eventualne zagrade i računa
	        if (poslednji == ')') {
	            izraz = OperacijeNadStringom.autoZatvoriZagrade(pomocniText);
	            textFieldPomocni.setText(izraz + "=");
	            textFieldTrenutni.setText(OperacijeNadStringom.racunanje(izraz));
	            return;
	        }

	        //  Svi ostali slučajevi (npr otvorena zagrada na kraju)  spoji pomoćni i trenutni
	        izraz = pomocniText + trenutniText;
	        izraz = OperacijeNadStringom.autoZatvoriZagrade(izraz);
	        textFieldPomocni.setText(izraz + "=");
	        textFieldTrenutni.setText(OperacijeNadStringom.racunanje(izraz));
	    }
	

		});
		btnJednako.setBackground(new Color(0, 128, 192));
		btnJednako.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelDugmici.add(btnJednako);

		 SwingUtilities.invokeLater(() -> {//gde hoces kursor
	            textFieldTrenutni.requestFocusInWindow();
	        });
	    }
	public void postaviBroj(int broj) {
	    String br = Integer.toString(broj);
	    String pomocniText = textFieldPomocni.getText();
	    String trenutniText = textFieldTrenutni.getText();
	    
	    
	    if (pomocniText.contains("=")) {
	        textFieldPomocni.setText("");         
	        textFieldTrenutni.setText(br);         
	        unosOdPocetka = false;                 
	        return;                          
	    }
	    if (unosOdPocetka || trenutniText.equals("0")) {
	        textFieldTrenutni.setText(br);
	    } else {
	        textFieldTrenutni.setText(trenutniText + br);
	    }

	   
	    if (!pomocniText.isEmpty() && pomocniText.charAt(pomocniText.length() - 1) == ')') {
	    	if(pomocniText.startsWith("sqr")) {
		    	textFieldPomocni.setText(OperacijeNadStringom.kvadriraj(OperacijeNadStringom.izmedjuZagrada(pomocniText))+"\u00D7");
		    	textFieldTrenutni.setText(br);
		    	unosOdPocetka=false;
		    	return;
		    }
	    	if(pomocniText.startsWith("\u221A")) {
		    	textFieldPomocni.setText(OperacijeNadStringom.korenuj(OperacijeNadStringom.izmedjuZagrada(pomocniText))+"\u00D7");
		    	textFieldTrenutni.setText(br);
		    	unosOdPocetka=false;
		    	return;
		    }
	        textFieldPomocni.setText(pomocniText + "\u00D7");
	    }

	    unosOdPocetka = false;
	}

}
