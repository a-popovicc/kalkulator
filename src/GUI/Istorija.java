package GUI;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import operacije.RadSaFajlom;

import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Istorija extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public Istorija() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setText(RadSaFajlom.ucitajIzFajla());
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnAzuriraj = new JButton("refresh");
		btnAzuriraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(RadSaFajlom.ucitajIzFajla());
			}
		});
		
		JButton btnObrisi = new JButton("delete");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RadSaFajlom.obrisiSveIzFajla();
				textArea.setText(RadSaFajlom.ucitajIzFajla());
			}
		});
		panel.add(btnObrisi);
		panel.add(btnAzuriraj);
		
		JButton btnIzlaz = new JButton("exit");
		btnIzlaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Istorija.this.dispose();
			}
		});
		panel.add(btnIzlaz);

	}

}
