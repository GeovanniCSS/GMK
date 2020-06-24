package tela;

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

import bo.KartodromoBo;
import entity.Kartodromo;
import java.awt.Toolkit;

public class TelaLoginKartodromo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLoginKartodromo frame = new TelaLoginKartodromo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLoginKartodromo() {
		super("Login Kartodromo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLoginKartodromo.class.getResource("/tela/trofeu.jpg")));
		Kartodromo kartodromo = new Kartodromo();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticar(kartodromo);
			}
			
		});
		btnEntrar.setBounds(125, 187, 89, 23);
		contentPane.add(btnEntrar);
		
		JButton btnRegistrar = new JButton("Cadastrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroKartodromo();
				dispose();
			}
		});
		btnRegistrar.setBounds(216, 187, 101, 23);
		contentPane.add(btnRegistrar);
		
		textField = new JTextField();
		textField.setBounds(161, 118, 153, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(161, 155, 153, 23);
		contentPane.add(passwordField);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaInicial();
				dispose();
			}
		});
		btnVoltar.setBounds(10, 227, 89, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/fundologin.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		contentPane.add(lblFundo);
		
		
		
		
		setVisible(true);
	}
	public void autenticar(Kartodromo kartodromo) {
		KartodromoBo kartodromoBo=new KartodromoBo();
		kartodromo.setNome(textField.getText());
		kartodromo.setSenha(passwordField.getText());
		try {
			new TelaPrincipalKartodromo(kartodromoBo.login("login", kartodromo));
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos");
			
		}
	
	}

}
