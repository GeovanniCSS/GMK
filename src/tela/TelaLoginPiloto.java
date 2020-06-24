package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bo.pilotoBo;
import entity.Piloto;
import java.awt.Toolkit;

public class TelaLoginPiloto extends JFrame {

	private JPanel contentPane;
	private JPasswordField senhaTxt;
	private JTextField loginTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLoginPiloto frame = new TelaLoginPiloto();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLoginPiloto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLoginPiloto.class.getResource("/tela/trofeu.jpg")));
		Piloto piloto=new Piloto();	
		setTitle("Login piloto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		loginTxt = new HintTextField("Login");
		loginTxt.setBounds(159, 119, 156, 20);
		contentPane.add(loginTxt);
		loginTxt.setColumns(10);

		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaInicial();
				dispose();
			}
		});
		voltar.setBounds(25, 216, 89, 23);
		contentPane.add(voltar);

		senhaTxt = new JPasswordField();
		senhaTxt.setBounds(159, 157, 155, 20);
		contentPane.add(senhaTxt);

		JButton entrar = new JButton("Entrar");
		entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				efetuarLogin(piloto);
			}
		});
		JButton btnRegistrar = new JButton("Cadastrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroPiloto();
				dispose();
			}
		});
		btnRegistrar.setBounds(216, 187, 101, 23);
		contentPane.add(btnRegistrar);
		entrar.setBounds(125, 187, 89, 23);
		contentPane.add(entrar);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundologin.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);

		contentPane.add(lblFundo);
		setVisible(true);
	}

	private void efetuarLogin(Piloto piloto) {
		piloto.setLogin(loginTxt.getText());
		piloto.setSenha(senhaTxt.getText());
		pilotoBo pilotoBo=new pilotoBo();
		try {
			new TelaPrincipalPiloto(pilotoBo.login("login", piloto));
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			
		}
	}
}
