package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.CorridaCampeonatoBo;
import entity.Campeonato;
import entity.CorridaCampeonato;
import entity.Piloto;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class TelaPrincipalCampeonato extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalCampeonato frame = new TelaPrincipalCampeonato(new Piloto(1),new Campeonato(1));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param piloto 
	 * @param campeonato 
	 */
	public TelaPrincipalCampeonato(Piloto piloto, Campeonato campeonato) {
		setForeground(Color.BLACK);
		setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipalCampeonato.class.getResource("/tela/trofeu.jpg")));
		setTitle("Campeonato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Bateria");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Desmarcar");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaDesmarcarCorrida(piloto, campeonato);
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nova");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaNovaCorrida(piloto,campeonato);
				dispose();
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Salvar Resultado");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaEscolhaCorrida(piloto,campeonato);
				dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("Classifica\u00E7\u00E3o");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Campeonato");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaClassificacaoCampeonatoC(piloto,campeonato);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Pontos");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaValorDosPontos(piloto, campeonato);
				dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Baterias");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaEscolherClassificacaoC(piloto,campeonato);
				dispose();
			}
		});
		
		JMenu mnNewMenu_3 = new JMenu("Pilotos");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Meus Pilotos");
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Novo piloto");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaNovoPiloto(piloto, campeonato);
				dispose();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_7);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaMeusPilotos(piloto, campeonato);
				dispose();
			}
		});
		
		JMenu mnNewMenu_2 = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Tela principal");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalPiloto(piloto);
				dispose();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Meus campeonatos");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaMeusCampeonatos(piloto);
				dispose();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Logout");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaLoginPiloto();
				dispose();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_8);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pr\u00F3ximas baterias");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 91, 223, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(campeonato.getNome());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(0, 11, 434, 38);
		contentPane.add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_2 = new JLabel(piloto.getLogin());
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 66, 190, 14);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 114, 414, 108);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Bateria", "Data", "Hora", "Kart\u00F3dromo", "Criador"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 434, 256);
		carregarTabela(campeonato);
		contentPane.add(lblFundo);
		
		setVisible(true);
	}
	public void carregarTabela(Campeonato campeonato) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		CorridaCampeonatoBo corridaCampeonatoBo=new CorridaCampeonatoBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		try {
			for (CorridaCampeonato c : corridaCampeonatoBo.listar("listaCampeonato", null, campeonato.getId())) {
				modelo.addRow(new Object[] {c.getNome() , CorridaCampeonatoBo.dataTela(c.getData()),c.getHora(),
						c.getKartodromo().getNome(), c.getPiloto().getNome() });
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
