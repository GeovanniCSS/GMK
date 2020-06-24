package tela;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.ConviteBo;
import bo.CorridaCampeonatoBo;
import entity.Convite;
import entity.CorridaCampeonato;
import entity.Piloto;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaPrincipalPiloto extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalPiloto frame = new TelaPrincipalPiloto(new Piloto());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipalPiloto(Piloto piloto) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipalPiloto.class.getResource("/tela/trofeu.jpg")));
		setTitle("Tela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu_1 = new JMenu("Agenda");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Meus convites");
		mnNewMenu_1.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaMeusConvites(piloto);
				dispose();
			}
		});

		JMenu mnNewMenu = new JMenu("Campeonato");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Criar campeonato");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroCampeonato(piloto,null);
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Meus campeonatos");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaMeusCampeonatos(piloto);
				dispose();
			}
		});
		
				JMenuItem mntmNewMenuItem_3 = new JMenuItem("Classifica\u00E7\u00E3o");
				mnNewMenu.add(mntmNewMenuItem_3);
				mntmNewMenuItem_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					new TelaEscolherCampeonatoP(piloto);
					dispose();
					}
				});
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenu mnNewMenu_3 = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Logout");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaLoginPiloto();
				dispose();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Kart\u00F3dromo");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaKartodromo(piloto);
				dispose();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 414, 112);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Bateria", "Data", "Hora", "Kartódromo", "Campeonato" }));
		scrollPane.setViewportView(table);
		
		contentPane.add(scrollPane);
		JLabel lblNewLabel = new JLabel("Pr\u00F3ximas baterias");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 95, 176, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(piloto.getLogin());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		// JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(0, 32, 434, 29);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(piloto.getNome());
		// JLabel lblNewLabel_2 = new JLabel("nome");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 79, 218, 14);
		contentPane.add(lblNewLabel_2);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		carregarTabela(piloto);
		contentPane.add(lblFundo);

		setVisible(true);
	}

	public void carregarTabela(Piloto piloto) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		CorridaCampeonatoBo corridaCampeonatoBo=new CorridaCampeonatoBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		try {
			for (CorridaCampeonato c : corridaCampeonatoBo.listar("listaPiloto", null, piloto.getId())) {
				modelo.addRow(new Object[] {c.getNome() ,CorridaCampeonatoBo.dataTela(c.getData()),c.getHora(),
						c.getKartodromo().getNome(), c.getCampeonato().getNome() 
						});
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
