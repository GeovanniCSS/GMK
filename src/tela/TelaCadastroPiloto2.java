package tela;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.pilotoBo;
import bo.PilotoCorridaBo;
import entity.Piloto;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TelaCadastroPiloto2 extends JFrame {

	private JPanel contentPane;
	private JTextField loginTxt;
	private JTextField senhaTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPiloto2 frame = new TelaCadastroPiloto2(new Piloto());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroPiloto2(Piloto piloto) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroPiloto2.class.getResource("/tela/trofeu.jpg")));
		setTitle("Futuro login do piloto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel entrar = new JLabel("");
		entrar.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		entrar.setBounds(0, 0, 443, 270);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(65, 94, 48, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(65, 128, 48, 14);
		contentPane.add(lblNewLabel_1);

		loginTxt = new JTextField();
		loginTxt.setBounds(151, 91, 96, 20);
		contentPane.add(loginTxt);
		loginTxt.setColumns(10);

		senhaTxt = new JTextField();
		senhaTxt.setBounds(151, 125, 96, 20);
		contentPane.add(senhaTxt);
		senhaTxt.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Escolha seu nome de piloto");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(82, 33, 268, 45);
		contentPane.add(lblNewLabel_2);

		JButton finalizarB = new JButton("Finalizar");
		finalizarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarPiloto(piloto);
			}
		});
		finalizarB.setBounds(323, 213, 89, 23);
		contentPane.add(finalizarB);

		JButton voltarB = new JButton("Voltar");
		voltarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroPiloto();
				dispose();
			}
		});
		voltarB.setBounds(32, 213, 89, 23);
		contentPane.add(voltarB);
		contentPane.add(entrar);
		setVisible(true);
	}

	public void salvarPiloto(Piloto piloto) {
		pilotoBo pilotoBo=new pilotoBo();
		try {
			piloto.setLogin(loginTxt.getText());
			piloto.setSenha(senhaTxt.getText());
			new TelaPrincipalPiloto(pilotoBo.cadastro("cadastro", piloto));
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}
