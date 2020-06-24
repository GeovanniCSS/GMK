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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.CorridaCampeonatoBo;
import bo.PilotoCampeonatoBo;
import bo.PilotoCorridaBo;
import entity.Campeonato;
import entity.Piloto;
import entity.PilotoCampeonato;
import java.awt.Toolkit;

public class TelaClassificacaoCampeonatoP extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tbcTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaClassificacaoCampeonatoP frame = new TelaClassificacaoCampeonatoP(new Piloto(),new Campeonato());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param campeonato 
	 * @param piloto 
	 */
	public TelaClassificacaoCampeonatoP(Piloto piloto, Campeonato campeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaClassificacaoCampeonatoP.class.getResource("/tela/trofeu.jpg")));
		setTitle("Classifica\u00E7\u00E3o do Campeonato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("HAHAHAHHAHAHAHAHAHAHAHA\r\n");
		scrollPane.setBounds(23, 61, 279, 155);
		contentPane.add(scrollPane);
		tbcTxt = new JTextField();
		tbcTxt.setEditable(false);
		tbcTxt.setBounds(398, 101, 26, 20);
		contentPane.add(tbcTxt);
		tbcTxt.setColumns(10);
		table = new JTable();
		table.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel(campeonato.getNome());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 11, 434, 27);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaEscolherCampeonatoP(piloto);
				dispose();
			}
		});
		btnNewButton.setBounds(309, 193, 115, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Tela principal");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalPiloto(piloto);
				dispose();
			}
		});
		btnNewButton_1.setBounds(309, 169, 115, 23);
		contentPane.add(btnNewButton_1);
		carregarTabela(campeonato);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		
		
		JLabel TBC = new JLabel("Total de baterias");
		TBC.setHorizontalAlignment(SwingConstants.CENTER);
		TBC.setToolTipText("");
		TBC.setForeground(Color.WHITE);
		TBC.setBounds(312, 77, 105, 27);
		contentPane.add(TBC);
		JLabel legenda = new JLabel("> Legenda das colunas <");
		legenda.setToolTipText("V:Vitorias, P:Pontos, +V:Volta mais r\u00E1pida, +L:Mais voltas na lideren\u00E7a");
		legenda.setForeground(Color.WHITE);
		legenda.setBounds(21, 230, 413, 14);
		contentPane.add(legenda);
		
		JLabel lblNewLabel_1 = new JLabel("completas:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(322, 104, 112, 14);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblFundo);
		setVisible(true);
	}
	private void carregarTabela(Campeonato campeonato) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		modelo.setRowCount(0);
		table.setModel(modelo);
		PilotoCampeonatoBo pilotoCampeonatoBo=new PilotoCampeonatoBo();
		CorridaCampeonatoBo corridaCampeonatoBo=new CorridaCampeonatoBo();
		PilotoCorridaBo pilotoCorridaBo=new PilotoCorridaBo();
		try {
			for (PilotoCampeonato piloto : pilotoCampeonatoBo.listar("pilotoCampeonato", null, campeonato.getId())) {
				modelo.addRow(new Object[] { piloto.getPosicao(), piloto.getPiloto().getLogin(), piloto.getPontucao(),
						pilotoCorridaBo.calcularVitorias("primeiro", piloto.getPiloto(), campeonato),
						pilotoCorridaBo.calcularVitorias("VMR", piloto.getPiloto(), campeonato),
						pilotoCorridaBo.calcularVitorias("ML", piloto.getPiloto(), campeonato),
						pilotoCorridaBo.calcularVitorias("Pole", piloto.getPiloto(), campeonato) 
						});
			}
			tbcTxt.setText(""+corridaCampeonatoBo.listar("listaCampeonato", null, campeonato.getId()).size());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar tabela");
			e.printStackTrace();
		}
	}
}
