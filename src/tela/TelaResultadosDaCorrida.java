package tela;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.PilotoCorridaBo;
import entity.CorridaCampeonato;
import entity.Piloto;
import entity.PilotoCorrida;
import java.awt.Toolkit;

public class TelaResultadosDaCorrida extends JFrame {

	private JPanel contentPane;
	private JLabel PL;
	private JLabel TC;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JScrollPane scroll_1;
	private JTable table2;
	private JTextField largadaTxt;
	private JTextField chegadaTxt;
	private JTextField tempoCorridaTxt;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaResultadosDaCorrida frame = new TelaResultadosDaCorrida(new Piloto(), new CorridaCampeonato());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param list
	 * @param piloto
	 */
	public TelaResultadosDaCorrida(Piloto piloto, CorridaCampeonato corridaCampeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaResultadosDaCorrida.class.getResource("/tela/trofeu.jpg")));
		setTitle("Resultados da bateria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(corridaCampeonato.getCampeonato().getNome());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 11, 443, 22);
		contentPane.add(lblNewLabel);

		JLabel PC = new JLabel("PC:");
		PC.setToolTipText("Posição de chegada");
		PC.setForeground(Color.WHITE);
		PC.setBounds(83, 100, 22, 14);
		contentPane.add(PC);

		PL = new JLabel("PL:");
		PL.setToolTipText("Posição de largada");
		PL.setForeground(Color.WHITE);
		PL.setBounds(17, 100, 22, 14);
		contentPane.add(PL);

		TC = new JLabel("TC:");
		TC.setToolTipText("Tempo de corrida");
		TC.setForeground(Color.WHITE);
		TC.setBounds(17, 128, 28, 14);
		contentPane.add(TC);

		btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaEscolhaCorrida(piloto, corridaCampeonato.getCampeonato());
				dispose();
			}
		});
		btnNewButton.setBounds(36, 227, 73, 23);
		contentPane.add(btnNewButton);

		btnNewButton_2 = new JButton("Finalizar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalCampeonato(piloto, corridaCampeonato.getCampeonato());
				dispose();
			}
		});
		btnNewButton_2.setBounds(344, 227, 89, 23);
		contentPane.add(btnNewButton_2);

		scroll_1 = new JScrollPane((Component) null);
		scroll_1.setBounds(233, 44, 200, 147);
		contentPane.add(scroll_1);

		table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Piloto", "P", "PL", "PC" }));
		table2.getColumnModel().getColumn(1).setPreferredWidth(25);
		table2.getColumnModel().getColumn(2).setPreferredWidth(25);
		table2.getColumnModel().getColumn(3).setPreferredWidth(25);
		carregarTabela2(piloto, corridaCampeonato);
		scroll_1.setViewportView(table2);

		JButton btnNewButton_1_1 = new JButton("<");
		btnNewButton_1_1.setBounds(167, 96, 62, 23);
		contentPane.add(btnNewButton_1_1);

		JCheckBox VMRcheck = new JCheckBox("+V");
		VMRcheck.setToolTipText("Volta mais rápida");
		VMRcheck.setBounds(17, 168, 52, 23);
		contentPane.add(VMRcheck);

		JLabel lblFundo = new JLabel("Informa\u00E7\u00F5es");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);

		JCheckBox ML = new JCheckBox("+L");
		ML.setToolTipText("Mais voltas na liderença");
		ML.setBounds(69, 168, 52, 23);
		contentPane.add(ML);

		largadaTxt = new JTextField();
		largadaTxt.setToolTipText("Digite a posição de largada");
		largadaTxt.setDocument(new Controller());
		largadaTxt.setHorizontalAlignment(SwingConstants.CENTER);
		largadaTxt.setBounds(36, 97, 32, 20);
		contentPane.add(largadaTxt);
		largadaTxt.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setBounds(17, 67, 118, 22);
		contentPane.add(comboBox);
		carregarCombo(piloto, corridaCampeonato);
		chegadaTxt = new JTextField();
		chegadaTxt.setToolTipText("Digite a posição de chegada");
		chegadaTxt.setHorizontalAlignment(SwingConstants.CENTER);
		chegadaTxt.setColumns(10);
		chegadaTxt.setBounds(103, 97, 32, 20);
		contentPane.add(chegadaTxt);

		tempoCorridaTxt = new HintTextField("01:00:00");
		tempoCorridaTxt.setToolTipText("Digite o tempo de corrida");
		tempoCorridaTxt.setHorizontalAlignment(SwingConstants.CENTER);
		tempoCorridaTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tempoCorridaTxt.setText("");
			}
		});
		tempoCorridaTxt.setColumns(10);
		tempoCorridaTxt.setBounds(36, 125, 73, 20);
		contentPane.add(tempoCorridaTxt);
		JCheckBox ausente = new JCheckBox("A");
		ausente.setToolTipText("Piloto ausente da bateria");
		ausente.setBounds(118, 168, 52, 23);
		contentPane.add(ausente);

		JButton salvarB = new JButton(">");
		salvarB.setToolTipText("Salvar resultado");
		salvarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarResultado(corridaCampeonato, VMRcheck.isSelected(), ML.isSelected(), ausente.isSelected());
				carregarTabela2(piloto, corridaCampeonato);
			}
		});
		salvarB.setBounds(167, 67, 62, 23);
		contentPane.add(salvarB);

		contentPane.add(lblFundo);

		setVisible(true);

	}

	private void salvarResultado(CorridaCampeonato cc, boolean VMR, boolean LM, boolean ausente) {
		PilotoCorridaBo pilotoCorridaBo = new PilotoCorridaBo();
		try {
			PilotoCorrida piloto = (PilotoCorrida) comboBox.getSelectedItem();
			pilotoCorridaBo.pegarResultado(largadaTxt.getText(), chegadaTxt.getText(), tempoCorridaTxt.getText(), VMR,
					LM, ausente, piloto);
			comboBox.removeItem(piloto);
			chegadaTxt.setText("");
			largadaTxt.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void carregarTabela2(Piloto piloto, CorridaCampeonato corridaCampeonato) {
		DefaultTableModel modelo = (DefaultTableModel) this.table2.getModel();
		PilotoCorridaBo pilotoCorridaBo = new PilotoCorridaBo();
		modelo.setRowCount(0);
		table2.setModel(modelo);
		try {
			for (PilotoCorrida pc : pilotoCorridaBo.listar("salvos", null, corridaCampeonato.getId())) {
				modelo.addRow(new Object[] { pc.getPiloto().getLogin(), pilotoCorridaBo.calcularPontuacao(pc),
						pc.getLargada(), pc.getChegada() });
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void carregarCombo(Piloto piloto, CorridaCampeonato corridaCampeonato) {
		PilotoCorridaBo pilotoCorridaBo = new PilotoCorridaBo();
		try {
			List<PilotoCorrida> lista = pilotoCorridaBo.listar("todosCorrida", null, corridaCampeonato.getId());
			for (PilotoCorrida c : lista) {
				comboBox.addItem(c);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar os horários");
			e.printStackTrace();
		}
	}
}
