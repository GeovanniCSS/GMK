package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Kartodromo;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TelaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
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
	public TelaInicial() {
		setTitle("GM Kartodromo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/tela/trofeu.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaLoginKartodromo();
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/btnKartodromo.png")));
		btnNewButton.setBounds(24, 11, 137, 239);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaLoginPiloto();
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/btnPiloto.png")));
		btnNewButton_1.setBounds(171, 11, 137, 239);
		contentPane.add(btnNewButton_1);
		
		
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		contentPane.add(lblFundo);
		setVisible(true);
	}
	
}
