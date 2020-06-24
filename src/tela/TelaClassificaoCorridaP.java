package tela;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import bo.CorridaCampeonatoBo;
import bo.PilotoCorridaBo;
import entity.Campeonato;
import entity.CorridaCampeonato;
import entity.Piloto;
import entity.PilotoCorrida;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TelaClassificaoCorridaP extends JFrame {

	private JPanel contentPane;
	private JTextField dataTxt;
	private JComboBox comboBox;
	private JTable table;
	private JTextField kTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaClassificaoCorridaP frame = new TelaClassificaoCorridaP(new Piloto(),new Campeonato());
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
	public TelaClassificaoCorridaP(Piloto piloto, Campeonato campeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaClassificaoCorridaP.class.getResource("/tela/trofeu.jpg")));
		setTitle("Classifica\u00E7\u00E3o das baterias");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 258, 146);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setToolTipText("Tabela da bateria");
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"PC", "Piloto", "P", "PL", "+R", "+L", "Tempo" 
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(27);
		table.getColumnModel().getColumn(1).setPreferredWidth(107);
		table.getColumnModel().getColumn(2).setPreferredWidth(27);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(27);
		table.getColumnModel().getColumn(5).setPreferredWidth(25);
		table.getColumnModel().getColumn(6).setPreferredWidth(52);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel(campeonato.getNome());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 11, 434, 39);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CorridaCampeonato corridaCampeonato=(CorridaCampeonato) comboBox.getSelectedItem();
				carregarTabela(piloto,corridaCampeonato);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				CorridaCampeonato corridaCampeonato=(CorridaCampeonato) comboBox.getSelectedItem();
				carregarTabela(piloto,corridaCampeonato);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				CorridaCampeonato corridaCampeonato=(CorridaCampeonato) comboBox.getSelectedItem();
				carregarTabela(piloto,corridaCampeonato);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				CorridaCampeonato corridaCampeonato=(CorridaCampeonato) comboBox.getSelectedItem();
				carregarTabela(piloto,corridaCampeonato);
			}
		});
		comboBox.setBounds(293, 61, 118, 22);
		contentPane.add(comboBox);
		carregarCombo(piloto, campeonato);
		
		JLabel lblNewLabel_1 = new JLabel("Classifica\u00E7\u00E3o da bateria");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 56, 258, 27);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Tela principal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalPiloto(piloto);
			}
		});
		btnNewButton.setBounds(293, 214, 118, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaEscolherCampeonatoP(piloto);
				dispose();
			}
		});
		btnVoltar.setBounds(293, 180, 118, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel_2 = new JLabel("Baterias");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(293, 48, 118, 14);
		contentPane.add(lblNewLabel_2);
		
		dataTxt = new JTextField();
		dataTxt.setHorizontalAlignment(SwingConstants.CENTER);
		dataTxt.setBounds(293, 153, 118, 20);
		contentPane.add(dataTxt);
		dataTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Data");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(293, 138, 118, 14);
		contentPane.add(lblNewLabel_3);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CorridaCampeonato corridaCampeonato=(CorridaCampeonato) comboBox.getSelectedItem();
				carregarTabela(piloto,corridaCampeonato);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				CorridaCampeonato corridaCampeonato=(CorridaCampeonato) comboBox.getSelectedItem();
				carregarTabela(piloto,corridaCampeonato);
			}
		});
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		JLabel legenda = new JLabel("> Legenda das colunas <");
		legenda.setToolTipText("V:Vitorias, P:Pontos, +V:Volta mais r\u00E1pida, +L:Mais voltas na lideren\u00E7a");
		legenda.setForeground(Color.WHITE);
		legenda.setBounds(10, 236, 262, 14);
		contentPane.add(legenda);
		
		
		JLabel lblNewLabel_4 = new JLabel("Kart\u00F3dromo");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(293, 96, 118, 14);
		contentPane.add(lblNewLabel_4);
		
		kTxt = new JTextField();
		kTxt.setBackground(new Color(255, 255, 255));
		kTxt.setHorizontalAlignment(SwingConstants.CENTER);
		kTxt.setBounds(293, 115, 118, 20);
		contentPane.add(kTxt);
		kTxt.setColumns(10);
		contentPane.add(lblFundo);
		setVisible(true);
	}
	private void carregarCombo(Piloto piloto, Campeonato campeonato) {
		CorridaCampeonatoBo corridaCampeonatoBo=new CorridaCampeonatoBo();
		try {
			//List<CorridaCampeonato> lista = corridaCampeonatoBo.listar("numeroCorrida", null,campeonato.getId());
			for (CorridaCampeonato c : corridaCampeonatoBo.listar("numeroCorrida", null,campeonato.getId())) {
				comboBox.addItem(c);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar as corridas");
			e.printStackTrace();
		}
	}
	private void carregarTabela(Piloto piloto, CorridaCampeonato corridaCampeonato) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		modelo.setRowCount(0);
		table.setModel(modelo);
		PilotoCorridaBo pilotoCorridaBo=new PilotoCorridaBo();
		try {
			for (PilotoCorrida pc : pilotoCorridaBo.listar("salvos", null, corridaCampeonato.getId())) {
				modelo.addRow(new Object[] {  pc.getChegada(), pc.getPiloto().getLogin(),
						pilotoCorridaBo.calcularPontuacao(pc), pc.getLargada(), pilotoCorridaBo.sinal(pc.getvMRapida()),
						pilotoCorridaBo.sinal(pc.getpLideranca()), pc.getTempoCorrida()});
			}
			dataTxt.setText(CorridaCampeonatoBo.dataTela(corridaCampeonato.getData()));
			kTxt.setText(corridaCampeonato.getKartodromo().getNome());
		} catch (Exception e) {
			
		}
	}
}
