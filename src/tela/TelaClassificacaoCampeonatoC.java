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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.PilotoCampeonatoBo;
import bo.PilotoCorridaBo;
import entity.Campeonato;
import entity.Piloto;
import entity.PilotoCampeonato;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaClassificacaoCampeonatoC extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaClassificacaoCampeonatoC frame = new TelaClassificacaoCampeonatoC(new Piloto(), new Campeonato());
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
	public TelaClassificacaoCampeonatoC(Piloto piloto, Campeonato campeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaClassificacaoCampeonatoC.class.getResource("/tela/trofeu.jpg")));
		setTitle("Classifica\u00E7\u00E3o do Campeonato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 55, 267, 164);
		contentPane.add(scrollPane);

		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		table = new JTable();
		table.setToolTipText("Tabela do campeonato");
		table.setModel(
				new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "Piloto", "P", "V", "+R", "+L", "Pole"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(36);
		table.getColumnModel().getColumn(5).setPreferredWidth(31);
		table.getColumnModel().getColumn(6).setPreferredWidth(31);
		carregarTabela(campeonato);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel(campeonato.getNome());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 11, 434, 33);
		contentPane.add(lblNewLabel);

		JButton voltarB = new JButton("Voltar");
		voltarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalCampeonato(piloto, campeonato);
			}
		});
		voltarB.setBounds(309, 196, 89, 23);
		contentPane.add(voltarB);
		

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(32, 59, 179, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel legenda = new JLabel("> Legenda das colunas <");
		legenda.setToolTipText("V:Vitorias, P:Pontos, +V:Volta mais r\u00E1pida, +L:Mais voltas na lideren\u00E7a");
		legenda.setForeground(Color.WHITE);
		legenda.setBounds(21, 230, 413, 14);
		contentPane.add(legenda);
		
		JLabel lblNewLabel_3 = new JLabel("P");
		lblNewLabel_3.setBounds(130, 230, 22, 14);
		contentPane.add(lblNewLabel_3);
		contentPane.add(lblFundo);
		setVisible(true);
	}

	private void carregarTabela(Campeonato campeonato) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		PilotoCampeonatoBo pilotoCampeonatoBo=new PilotoCampeonatoBo();
		PilotoCorridaBo pilotoCorridaBo=new PilotoCorridaBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		try {
			for (PilotoCampeonato piloto : pilotoCampeonatoBo.listar("pilotoCampeonato", null, campeonato.getId())) {
				modelo.addRow(new Object[] { piloto.getPosicao(), piloto.getPiloto().getLogin(), piloto.getPontucao(),
						pilotoCorridaBo.calcularVitorias("primeiro", piloto.getPiloto(), campeonato),
						pilotoCorridaBo.calcularVitorias("VMR", piloto.getPiloto(), campeonato),
						pilotoCorridaBo.calcularVitorias("ML", piloto.getPiloto(), campeonato),
						pilotoCorridaBo.calcularVitorias("Pole", piloto.getPiloto(), campeonato) 
						});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar tabela");
		}
	}
}
