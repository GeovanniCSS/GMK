package tela;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.PilotoCampeonatoBo;
import bo.PilotoCorridaBo;
import entity.CorridaCampeonato;
import entity.Piloto;
import entity.PilotoCorrida;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaClassificacaoCorridaC extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaClassificacaoCorridaC frame = new TelaClassificacaoCorridaC(new Piloto(),
							new CorridaCampeonato());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param corridaCampeonato
	 * @param piloto
	 */
	public TelaClassificacaoCorridaC(Piloto piloto, CorridaCampeonato corridaCampeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaClassificacaoCorridaC.class.getResource("/tela/trofeu.jpg")));
		setTitle("Classifica\u00E7\u00E3o da bateria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 76, 277, 153);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setToolTipText("Tabela da bateria");
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "PC", "Piloto", "P", "PL", "+R", "+L", "Tempo" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(27);
		table.getColumnModel().getColumn(1).setPreferredWidth(107);
		table.getColumnModel().getColumn(2).setPreferredWidth(27);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(27);
		table.getColumnModel().getColumn(5).setPreferredWidth(25);
		table.getColumnModel().getColumn(6).setPreferredWidth(52);
		scrollPane.setViewportView(table);
		carregarTabela(piloto, corridaCampeonato);
		JLabel lblNewLabel = new JLabel(corridaCampeonato.getCampeonato().getNome());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(0, 11, 434, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(corridaCampeonato.getNome());
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(22, 52, 277, 26);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaEscolherClassificacaoC(piloto, corridaCampeonato.getCampeonato());
			}
		});
		btnNewButton.setBounds(309, 204, 115, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Campeonato");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalCampeonato(piloto, corridaCampeonato.getCampeonato());
				dispose();
			}
		});
		btnNewButton_1.setBounds(309, 170, 115, 23);
		contentPane.add(btnNewButton_1);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		JLabel legenda = new JLabel("> Legenda das colunas <");
		legenda.setToolTipText("V:Vitorias, P:Pontos, +V:Volta mais r\u00E1pida, +L:Mais voltas na lideren\u00E7a");
		legenda.setForeground(Color.WHITE);
		legenda.setBounds(22, 240, 413, 14);
		contentPane.add(legenda);
		contentPane.add(lblFundo);
		setVisible(true);
	}

	private void carregarTabela(Piloto piloto, CorridaCampeonato corridaCampeonato) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		modelo.setRowCount(0);
		table.setModel(modelo);
		PilotoCorridaBo pilotoCorridaBo=new PilotoCorridaBo();
		try {
			for (PilotoCorrida pc : pilotoCorridaBo.listar("salvos", null, corridaCampeonato.getId())) {
				modelo.addRow(new Object[] { pc.getChegada(), pc.getPiloto().getLogin(),
						pilotoCorridaBo.calcularPontuacao(pc), pc.getLargada(), pilotoCorridaBo.sinal(pc.getvMRapida()),
						pilotoCorridaBo.sinal(pc.getpLideranca()), pc.getTempoCorrida() });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar tabela");
		}
	}
}
