package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import bo.CidadeBo;
import bo.KartodromoBo;
import entity.Cidade;
import entity.Kartodromo;
import java.awt.Toolkit;

public class TelaCadastroKartodromo extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfSenha;
	private JTextField tfRua;
	private JTextField tfNumero;
	private JTextField tfComplemento;
	private JLabel lblNewLabel;
	private JComboBox comboBox;
	private int numero;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroKartodromo frame = new TelaCadastroKartodromo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroKartodromo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroKartodromo.class.getResource("/tela/trofeu.jpg")));
		setTitle("Novo Kart\u00F3dromo");
		Kartodromo kartodromo = new Kartodromo();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Nome");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(15, 33, 48, 14);
		contentPane.add(lblNewLabel);
		
		tfNome = new JTextField();
		tfNome.setBounds(129, 30, 128, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfSenha = new JTextField();
		tfSenha.setBounds(129, 61, 128, 20);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(15, 64, 48, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Endereço");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(15, 92, 128, 14);
		contentPane.add(lblNewLabel_2);
		
		tfRua = new JTextField();
		tfRua.setBounds(129, 92, 128, 20);
		contentPane.add(tfRua);
		tfRua.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("N\u00FAmero da rua");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setBounds(15, 120, 128, 14);
		contentPane.add(lblNewLabel_2_1);
		
		tfNumero = new JTextField();
		tfNumero.setBounds(129, 117, 128, 20);
		contentPane.add(tfNumero);
		tfNumero.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Complemento");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(15, 145, 104, 14);
		contentPane.add(lblNewLabel_3);
		
		tfComplemento = new JTextField();
		tfComplemento.setBounds(129, 145, 128, 20);
		contentPane.add(tfComplemento);
		tfComplemento.setColumns(10);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaLoginKartodromo();
			}
		});
		btnNewButton.setBounds(10, 235, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Pr\u00F3ximo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar(kartodromo);
				proximo(kartodromo);
			}
		});
		btnNewButton_1.setBounds(321, 227, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Cidade");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(15, 185, 84, 14);
		contentPane.add(lblNewLabel_4);
		
		comboBox = new JComboBox();
		combo();
		comboBox.setBounds(129, 176, 128, 22);
		contentPane.add(comboBox);
		
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		contentPane.add(lblFundo);
		setVisible(true);
	}
	public void combo() {
		CidadeBo cidadeBo=new CidadeBo();
		try {
			List <Cidade> lista= cidadeBo.listar();
			for(Cidade c: lista) {
				comboBox.addItem(c);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void proximo(Kartodromo kartodromo) {
		KartodromoBo kartodromoBo=new KartodromoBo();
		Cidade cidade = (Cidade) comboBox.getSelectedItem();
		kartodromo.setNome(tfNome.getText());
		try {
			kartodromoBo.cadastro("cadastro", kartodromo);
			
			new TelaCadastroKartodromo2(kartodromoBo.proximaTela(tfNome.getText(), tfSenha.getText(), tfRua.getText(),
					tfNumero.getText(), tfComplemento.getText(), cidade));
			
			dispose();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}

	}
	public void validar (Kartodromo kartodromo) {
		KartodromoBo kartodromoBo=new KartodromoBo();
		kartodromo.setNome(tfNome.getText());
		try {
			kartodromoBo.cadastro("cadastro", kartodromo);
		} catch (Exception e1) {
			System.out.println("Ja ta cadastrado");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
