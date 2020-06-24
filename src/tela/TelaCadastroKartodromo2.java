
package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bo.KartodromoBo;
import entity.Kartodromo;
import entity.Piloto;
import java.awt.Toolkit;

public class TelaCadastroKartodromo2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroKartodromo2 frame = new TelaCadastroKartodromo2(new Kartodromo());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroKartodromo2(Kartodromo kartodromo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroKartodromo2.class.getResource("/tela/trofeu.jpg")));
		setTitle("Informa\u00E7\u00F5es do  Kart\u00F3dromo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Telefone");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(23, 41, 137, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Whatsapp");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(23, 66, 98, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("Url site");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(23, 95, 81, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00FAmero m\u00E1ximo de pilotos por corrida");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(23, 132, 251, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("N\u00FAmero m\u00EDnimo de pilotos por corrida");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(23, 157, 251, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(251, 129, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(251, 157, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(104, 38, 154, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(104, 66, 154, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(104, 92, 154, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroKartodromo();
				dispose();
			}
		});
		btnNewButton.setBounds(23, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Finalizar cadastro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar(kartodromo);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setBounds(278, 227, 131, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		contentPane.add(lblFundo);
		setVisible(true);
	}
	public void salvar(Kartodromo kartodromo) {
		KartodromoBo kartodromobo = new KartodromoBo();
		try {
			kartodromo.setTelefone(Integer.parseInt(textField_2.getText()));
			kartodromo.setWhatsapp(Integer.parseInt(textField_3.getText()));
			kartodromo.setUrlSite(textField_4.getText());
			kartodromo.setNumMax(Integer.parseInt(textField.getText()));
			kartodromo.setNumMinimo(Integer.parseInt(textField_1.getText()));
			//kartodromo.setTelefone(textField_2.getText());
			
			kartodromobo.saveOrUpadte(kartodromo);
			
			new TelaPrincipalKartodromo(kartodromo);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

}
