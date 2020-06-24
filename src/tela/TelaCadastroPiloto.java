package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bo.pilotoBo;
import entity.Piloto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class TelaCadastroPiloto extends JFrame {

	private JPanel contentPane;
	private JTextField nomeTxt;
	private JTextField emailTxt;
	private JTextField cpfTxt;
	private JTextField dataTxt;
	private JTextField celularTxt;
	DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPiloto frame = new TelaCadastroPiloto();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroPiloto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroPiloto.class.getResource("/tela/trofeu.jpg")));
		setTitle("Novo piloto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nomeTxt = new JTextField();
		nomeTxt.setBounds(130, 46, 156, 20);
		contentPane.add(nomeTxt);
		nomeTxt.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(27, 49, 76, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(27, 79, 48, 14);
		contentPane.add(lblNewLabel_1);

		emailTxt = new JTextField();
		emailTxt.setBounds(130, 76, 156, 20);
		contentPane.add(emailTxt);
		emailTxt.setColumns(10);

		cpfTxt = new JTextField();
		cpfTxt.setBounds(130, 107, 156, 20);
		cpfTxt.setDocument(new Controller());
		contentPane.add(cpfTxt);
		cpfTxt.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(25, 115, 48, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Data nascimento");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(27, 141, 106, 14);
		contentPane.add(lblNewLabel_3);

		dataTxt = new JTextField();
		dataTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dataTxt.setText("");
			}
		});
		dataTxt.setBounds(130, 138, 156, 20);
		contentPane.add(dataTxt);
		dataTxt.setText("12/12/2020");
		dataTxt.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Celular");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(27, 175, 48, 14);
		contentPane.add(lblNewLabel_4);

		celularTxt = new JTextField();
		celularTxt.setBounds(130, 172, 156, 20);
		celularTxt.setDocument(new Controller());
		contentPane.add(celularTxt);
		celularTxt.setColumns(10);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaInicial();
				dispose();
			}
		});
		btnNewButton.setBounds(10, 227, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Pr\u00F3ximo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximo();
			}
		});
		btnNewButton_1.setBounds(322, 227, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_5 = new JLabel("Bem vindo ao GMK");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(126, 0, 217, 35);
		contentPane.add(lblNewLabel_5);

		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		contentPane.add(lblFundo);
		setVisible(true);
	}

	public void proximo() {
		pilotoBo pilotoBo=new pilotoBo();
		try {
			new TelaCadastroPiloto2(pilotoBo.proximaTela(nomeTxt.getText(), cpfTxt.getText(), emailTxt.getText(),
					celularTxt.getText(), dataTxt.getText()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}

	}
}
