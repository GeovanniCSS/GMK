package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import bo.CorridaCampeonatoBo;
import bo.KartodromoBo;
import bo.PilotoCorridaBo;
import entity.Campeonato;
import entity.Kartodromo;
import entity.Piloto;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaNovaCorrida extends JFrame {

	private JPanel contentPane;
	private JTextField nomeTxt;
	private JComboBox<Object> comboBox, comboHBox, comboDia,comboMes,comboAno;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaNovaCorrida frame = new TelaNovaCorrida(new Piloto(0), new Campeonato(0));
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
	 * @param piloto
	 */
	public TelaNovaCorrida(Piloto piloto, Campeonato campeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaNovaCorrida.class.getResource("/tela/trofeu.jpg")));
		setTitle("Nova bateria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(campeonato.getNome());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 20, 433, 27);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(37, 92, 79, 14);
		contentPane.add(lblNewLabel_1);

		comboHBox = new JComboBox();
		comboHBox.setBounds(140, 119, 137, 22);
		comboHorarios();
		contentPane.add(comboHBox);

		JLabel lblNewLabel_2 = new JLabel("Hor\u00E1rio");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(37, 123, 48, 14);
		contentPane.add(lblNewLabel_2);

		JButton voltarB = new JButton("Voltar");
		voltarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalCampeonato(piloto, campeonato);
				dispose();
			}
		});
		voltarB.setBounds(37, 198, 89, 23);
		contentPane.add(voltarB);

		JButton marcarB = new JButton("Marcar");
		marcarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marcarCorrida(piloto, campeonato);
			}
		});
		marcarB.setBounds(269, 198, 89, 23);
		contentPane.add(marcarB);

		nomeTxt = new JTextField();
		nomeTxt.setBounds(140, 58, 145, 20);
		contentPane.add(nomeTxt);
		nomeTxt.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Nome");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(37, 61, 48, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Kart\u00F3dromo");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(37, 155, 89, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);

		comboBox = new JComboBox();
		comboBox.setBounds(140, 152, 137, 22);
		contentPane.add(comboBox);
		combokartodromo();

		comboDia = new JComboBox();
		comboDia.setBounds(139, 88, 40, 22);
		contentPane.add(comboDia);

		comboMes = new JComboBox();
		comboMes.setBounds(184, 88, 39, 22);
		contentPane.add(comboMes);

		comboAno = new JComboBox();
		comboAno.setBounds(229, 88, 56, 22);
		contentPane.add(comboAno);

		comboDia();
		comboMes();
		comboAno();
		contentPane.add(lblFundo);
		setVisible(true);
	}

	private void marcarCorrida(Piloto piloto, Campeonato campeonato) {
		CorridaCampeonatoBo cb=new CorridaCampeonatoBo();
		try {
			Kartodromo kartodromo = (Kartodromo) comboBox.getSelectedItem();
			String hora = (String) comboHBox.getSelectedItem();
			String dia=(String) comboDia.getSelectedItem();
			String mes=(String) comboMes.getSelectedItem();
			int ano=(int) comboAno.getSelectedItem();
			cb.marcarCorrida(nomeTxt.getText(), CorridaCampeonatoBo.dataCorrida(dia, mes, ano), hora, kartodromo, piloto, campeonato);
			JOptionPane.showMessageDialog(this, "Bateria marcada");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	public void combokartodromo() {
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
	public void comboHorarios() {
		try {
			List<String> lista = KartodromoBo.horarios();
			for (String c : lista) {
				comboHBox.addItem(c);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar os horários");
			e.printStackTrace();
		}
	}

	public void comboDia() {
		try {
			List<String> lista = CorridaCampeonatoBo.dia();
			for (String c : lista) {
				comboDia.addItem(c);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar os dias");
			e.printStackTrace();
		}
	}

	public void comboMes() {
		try {
			List<String> lista = CorridaCampeonatoBo.mes();
			for (String c : lista) {
				comboMes.addItem(c);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar os dias");
			e.printStackTrace();
		}
	}

	public void comboAno() {
		try {
			List<Integer> lista = CorridaCampeonatoBo.ano();
			for (int c : lista) {
				comboAno.addItem(c);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar os dias");
			e.printStackTrace();
		}
	}
}