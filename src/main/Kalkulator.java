package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Kalkulator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldPomocni;
	private JTextField textFieldTrenutni;
	private JButton button;
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
		textFieldPomocni.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textFieldPomocni.setBackground(new Color(255, 255, 255));
		panelIzlaz.add(textFieldPomocni);
		textFieldPomocni.setColumns(10);
		textFieldPomocni.setBorder(null);
		textFieldPomocni.setHorizontalAlignment(JTextField.RIGHT);
		textFieldPomocni.setEditable(false);
		
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
				
			String postojece = textFieldPomocni.getText();
			textFieldPomocni.setText(postojece + "(");

			}
		});
		btnLevaZagrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnLevaZagrada);
		btnLevaZagrada.setPreferredSize(new Dimension(50,50));
		
		btnDesnaZagrada = new JButton(")");
		btnDesnaZagrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String postojece = textFieldPomocni.getText();
				textFieldPomocni.setText(postojece + ")");
			}
		});
		btnDesnaZagrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnDesnaZagrada);
		
		btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldPomocni.setText(null);
				textFieldTrenutni.setText("0");
			}
		});
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnC);
		
		btnCancel = new JButton("<---");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelDugmici.add(btnCancel);
		//btn4.setIcon(new ImageIcon("C:/Users/pop/eclipse-workspace/Kalkulator/undo.png"));
		
		btnReciprocno = new JButton("1/X");
		btnReciprocno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnReciprocno);
		
		btnNaKvadrat = new JButton("X\u00B2");
		btnNaKvadrat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnNaKvadrat);
		
		
		//JLabel label = new JLabel("<html>&radic;9 = 3</html>");

		btnKoren = new JButton("<html>&radic;X</html>");
		btnKoren.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDugmici.add(btnKoren);
		
		btnPodeljeno = new JButton("\u00F7");
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
				if(textFieldPomocni.getText()!="") {
					String postojece=textFieldPomocni.getText();
					textFieldPomocni.setText(postojece+textFieldTrenutni.getText()+"+");
					return;
				}
				textFieldPomocni.setText(textFieldTrenutni.getText()+"+");
				
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
		btnZarez.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelDugmici.add(btnZarez);
		
		btnJednako = new JButton("=");
		btnJednako.setBackground(new Color(0, 128, 192));
		btnJednako.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelDugmici.add(btnJednako);
		

		
		
		
		
		
		
		
		 SwingUtilities.invokeLater(() -> {//gde hoces kursor
	            textFieldTrenutni.requestFocusInWindow();
	        });
	    }
	
	
	public void postaviBroj(int broj) {
		
		String br=Integer.toString(broj);
		String postojece = textFieldTrenutni.getText();
		if(postojece.equals("0")) {
			textFieldTrenutni.setText(br);
			return;
		}
		try {
			//textFieldPomocni.getText().charAt(textFieldPomocni.getText().length()-1)=='+'
		if(textFieldPomocni.getText().charAt(textFieldPomocni.getText().length()-1)=='+') {
				textFieldTrenutni.setText(br);
				return;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		textFieldTrenutni.setText(postojece + br);
	}
	

}
