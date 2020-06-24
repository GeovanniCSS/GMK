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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.CampeonatoBo;
import bo.CorridaCampeonatoBo;
import bo.PilotoCorridaBo;
import entity.Campeonato;
import entity.Piloto;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaEscolherCampeonatoP extends JFrame {

	private JPanel contentPane;
	private JTextField pesquisarTxt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolherCampeonatoP frame = new TelaEscolherCampeonatoP(new Piloto());
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
	public TelaEscolherCampeonatoP(Piloto piloto) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaEscolherCampeonatoP.class.getResource("/tela/trofeu.jpg")));
		setTitle("Classifica\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 286, 134);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Campeonato", "Data", "Baterias"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Todos os campeonatos");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 54, 212, 23);
		contentPane.add(lblNewLabel);
		
		pesquisarTxt = new JTextField();
		pesquisarTxt.setBounds(10, 218, 171, 20);
		contentPane.add(pesquisarTxt);
		pesquisarTxt.setColumns(10);
		carregarTabela(piloto);
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarTabela(piloto);
			}
		});
		btnNewButton.setBounds(191, 217, 105, 23);
		contentPane.add(btnNewButton);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		
		JLabel lblNewLabel_1 = new JLabel(piloto.getLogin());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 11, 434, 23);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalPiloto(piloto);
				dispose();
			}
		});
		btnNewButton_1.setBounds(306, 217, 118, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Campeonato");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCampeonato("",piloto);
			}
		});
		btnNewButton_2.setBounds(306, 113, 118, 23);
		contentPane.add(btnNewButton_2);
		
		
		JButton btnNewButton_2_1 = new JButton("Baterias");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCampeonato("corrida", piloto);
			}
		});
		btnNewButton_2_1.setBounds(306, 147, 118, 23);
		contentPane.add(btnNewButton_2_1);
		contentPane.add(lblFundo);
		setVisible(true);
	}
	public void carregarTabela(Piloto piloto) {
		DefaultTableModel modelo = (DefaultTableModel)this.table.getModel();
		CampeonatoBo campeonatoBo=new CampeonatoBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		Campeonato campeonato=new Campeonato();
		campeonato.setNome(pesquisarTxt.getText());
		try {
			for(Campeonato c: campeonatoBo.listar("participo",campeonato,piloto.getId())) {
				modelo.addRow(new Object[] {c.getNome(),CorridaCampeonatoBo.dataTela(c.getDataCadastrado()),c.getTotalCorridas()});	
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	private void selecionarCampeonato(String tipo,Piloto piloto) {
		Campeonato campeonato=new Campeonato();
		CampeonatoBo campeonatoBo=new CampeonatoBo();
		campeonato.setNome(pesquisarTxt.getText());
		try {
			if(tipo.equals("corrida")) {
				new TelaClassificaoCorridaP(piloto,campeonatoBo.listar("participo",campeonato, piloto.getId()).get(table.getSelectedRow()));
			}else {
				new TelaClassificacaoCampeonatoP(piloto, campeonatoBo.listar("participo", campeonato, piloto.getId()).get(table.getSelectedRow()));
			}
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Selecione um campeonato");
		}
		
	}
}
