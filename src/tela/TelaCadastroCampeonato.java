package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bo.CampeonatoBo;
import entity.Campeonato;
import entity.Piloto;

public class TelaCadastroCampeonato extends JFrame {

	private JPanel contentPane;
	private JTextField nomeTxt;
	private JTextField numeroTxt;
	private ImageIcon image;
	private JTextField pLiderancaTxt;
	private JTextField pVoltaTxt;
	private JTextField pPoleTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCampeonato frame = new TelaCadastroCampeonato(new Piloto(), new Campeonato());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param campeonato
	 */
	public TelaCadastroCampeonato(Piloto piloto, Campeonato campeonato) {

		setIconImage(
				Toolkit.getDefaultToolkit().getImage(TelaCadastroCampeonato.class.getResource("/tela/trofeu.jpg")));
		setTitle("Novo Campeonato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 58, 48, 14);
		contentPane.add(lblNewLabel);

		nomeTxt = new JTextField();
		nomeTxt.setBounds(169, 55, 96, 20);
		contentPane.add(nomeTxt);
		nomeTxt.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("N\u00FAmero de baterias");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 89, 113, 14);
		contentPane.add(lblNewLabel_1);

		numeroTxt = new HintTextField("0");
		numeroTxt.setBounds(169, 86, 96, 20);
		contentPane.add(numeroTxt);
		numeroTxt.setColumns(10);

		JButton proximo = new JButton("Pr\u00F3ximo");
		proximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarCampeonato(piloto);
			}
		});
		proximo.setBounds(320, 227, 89, 23);
		contentPane.add(proximo);

		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalPiloto(piloto);
				dispose();
			}
		});
		cancelar.setBounds(10, 227, 89, 23);
		contentPane.add(cancelar);

		JLabel lblNewLabel_2 = new JLabel("Pontos por mais voltas na lideran\u00E7a");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 178, 227, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Pontos pela volta mais r\u00E1pida");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setBackground(Color.WHITE);
		lblNewLabel_2_1.setBounds(10, 153, 227, 14);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Pontua\u00E7\u00E3o por pole position");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setBounds(10, 118, 227, 14);
		contentPane.add(lblNewLabel_2_1_1);

		pLiderancaTxt = new HintTextField("0");
		pLiderancaTxt.setColumns(10);
		pLiderancaTxt.setBounds(169, 117, 96, 20);
		contentPane.add(pLiderancaTxt);

		pVoltaTxt = new HintTextField("0");
		pVoltaTxt.setColumns(10);
		pVoltaTxt.setBounds(216, 147, 96, 20);
		contentPane.add(pVoltaTxt);

		pPoleTxt = new HintTextField("0");
		pPoleTxt.setColumns(10);
		pPoleTxt.setBounds(216, 175, 96, 20);
		contentPane.add(pPoleTxt);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);

		JLabel lblNewLabel_3 = new JLabel("Crie seu campeonato");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setBounds(0, 10, 434, 34);
		contentPane.add(lblNewLabel_3);
		contentPane.add(lblFundo);
		setVisible(true);
	}

	public void salvarCampeonato(Piloto piloto) {
		CampeonatoBo campeonatoBo = new CampeonatoBo();
		Campeonato campeonato = new Campeonato(0, nomeTxt.getText(), LocalDate.now(),
				Integer.parseInt(numeroTxt.getText()), true, Integer.parseInt(pPoleTxt.getText()),
				Integer.parseInt(pVoltaTxt.getText()), Integer.parseInt(pLiderancaTxt.getText()), piloto);
		try {
			new TelaCadastroCampeonato2(campeonatoBo.salvarCampeonato(campeonato));
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
}
