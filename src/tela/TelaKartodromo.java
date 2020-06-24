package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.KartodromoBo;
import entity.Kartodromo;
import entity.Piloto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class TelaKartodromo extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JTextField cidadeTxt;
	private JTextField ufTxt;
	private JTextField enderecoTxt;
	private JTextField complementoTxt;
	private JTextField telefoneTxt;
	private JTextField whatsTxt;
	private JTextField siteTxt;
	private JTextField minTxt;
	private JTextField maxTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaKartodromo frame = new TelaKartodromo(new Piloto());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param piloto 
	 */
	public TelaKartodromo(Piloto piloto) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaKartodromo.class.getResource("/tela/trofeu.jpg")));
		setTitle("Kart\u00F3dromo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kart\u00F3dromo:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(20, 24, 94, 14);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				carregarKartodromo();
			}
		});
		comboBox.setBounds(93, 20, 160, 22);
		contentPane.add(comboBox);
		
		JLabel endereco = new JLabel("Endere\u00E7o:");
		endereco.setForeground(Color.WHITE);
		endereco.setBounds(20, 77, 70, 14);
		contentPane.add(endereco);
		
		JLabel complemento = new JLabel("Complemento:");
		complemento.setForeground(Color.WHITE);
		complemento.setBounds(20, 102, 323, 14);
		contentPane.add(complemento);
		
		JLabel telefone = new JLabel("Telefone:");
		telefone.setForeground(Color.WHITE);
		telefone.setBounds(20, 127, 53, 14);
		contentPane.add(telefone);
		
		JLabel whatsapp = new JLabel("Whatsapp:");
		whatsapp.setForeground(Color.WHITE);
		whatsapp.setBounds(224, 127, 64, 14);
		contentPane.add(whatsapp);
		
		JLabel lblNewLabel_6 = new JLabel("N\u00FAmero min\u00EDmo de pilotos por bateria:");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(20, 183, 323, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel cidade = new JLabel("Cidade:");
		cidade.setForeground(Color.WHITE);
		cidade.setBounds(20, 52, 59, 14);
		contentPane.add(cidade);
		
		JLabel lblNewLabel_6_1 = new JLabel("N\u00FAmero m\u00E1ximo de pilotos por bateria:");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setBounds(20, 208, 292, 14);
		contentPane.add(lblNewLabel_6_1);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalPiloto(piloto);
				dispose();
			}
		});
		btnNewButton.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				carregarKartodromo();
			}
		});
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		
		JLabel site = new JLabel("Site:");
		site.setForeground(Color.WHITE);
		site.setBounds(20, 152, 48, 14);
		contentPane.add(site);
		
		cidadeTxt = new JTextField();
		cidadeTxt.setBounds(63, 46, 96, 20);
		contentPane.add(cidadeTxt);
		cidadeTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("UF:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(187, 52, 48, 14);
		contentPane.add(lblNewLabel_1);
		
		ufTxt = new JTextField();
		ufTxt.setBounds(209, 49, 38, 20);
		contentPane.add(ufTxt);
		ufTxt.setColumns(10);
		
		enderecoTxt = new JTextField();
		enderecoTxt.setColumns(10);
		enderecoTxt.setBounds(82, 74, 279, 20);
		contentPane.add(enderecoTxt);
		
		complementoTxt = new JTextField();
		complementoTxt.setColumns(10);
		complementoTxt.setBounds(107, 99, 96, 20);
		contentPane.add(complementoTxt);
		
		telefoneTxt = new JTextField();
		telefoneTxt.setColumns(10);
		telefoneTxt.setBounds(72, 124, 135, 20);
		contentPane.add(telefoneTxt);
		
		whatsTxt = new JTextField();
		whatsTxt.setColumns(10);
		whatsTxt.setBounds(286, 124, 126, 20);
		contentPane.add(whatsTxt);
		
		siteTxt = new JTextField();
		siteTxt.setColumns(10);
		siteTxt.setBounds(49, 152, 256, 20);
		contentPane.add(siteTxt);
		
		minTxt = new JTextField();
		minTxt.setColumns(10);
		minTxt.setBounds(240, 180, 48, 20);
		contentPane.add(minTxt);
		
		maxTxt = new JTextField();
		maxTxt.setColumns(10);
		maxTxt.setBounds(241, 205, 47, 20);
		contentPane.add(maxTxt);
		contentPane.add(lblFundo);
		comboKartodromo();
		setVisible(true);
	}
	private void comboKartodromo() {
		KartodromoBo kartodromoBo=new KartodromoBo();
		try {
			List<Kartodromo> lista = (List<Kartodromo>) kartodromoBo.listar("", null,0);
			for (Kartodromo c : lista) {
				comboBox.addItem(c);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	private void carregarKartodromo() {
		Kartodromo k= (Kartodromo) comboBox.getSelectedItem();
		try {
			cidadeTxt.setText(k.getCidade().getNome());
			ufTxt.setText(k.getCidade().getUf());
			complementoTxt.setText(k.geteComplemento());
			enderecoTxt.setText(k.geteRua()+" N "+k.geteNumero());
			siteTxt.setText(k.getUrlSite());
			maxTxt.setText(k.getNumMax()+"");
			minTxt.setText(k.getNumMinimo()+"");
			whatsTxt.setText(k.getWhatsapp()+"");
			telefoneTxt.setText(k.getTelefone()+"");
		} catch (Exception e) {
			
		}
	}
}
