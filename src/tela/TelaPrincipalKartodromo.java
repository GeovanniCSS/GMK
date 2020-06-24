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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.CorridaCampeonatoBo;
import bo.KartodromoBo;
import entity.CorridaCampeonato;
import entity.Kartodromo;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaPrincipalKartodromo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String corrida;
	private Object obj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalKartodromo frame = new TelaPrincipalKartodromo(new Kartodromo());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipalKartodromo(Kartodromo kartodromo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipalKartodromo.class.getResource("/tela/trofeu.jpg")));
		setTitle("Tela princial");
		CorridaCampeonato corridacampeonato = new CorridaCampeonato();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_2 = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnNewMenu_2);
		
		JLabel lblNewLabel_1 = new JLabel(kartodromo.getNome());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(0, 37, 434, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Pr\u00F3ximas baterias");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 91, 223, 22);
		contentPane.add(lblNewLabel);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Logout");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaInicial();
				dispose();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		mnNewMenu_2.add(sair);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 114, 402, 108);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Bateria", "Data", "Hora", "Piloto"
			}
		));
		scrollPane.setViewportView(table);
		tabela(kartodromo);
		
		JButton btnNewButton = new JButton("Informações");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informacoes(corridacampeonato);
				dispose();
			}
		});
		btnNewButton.setBounds(295, 87, 115, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		contentPane.add(lblFundo);
		
		setVisible(true);
	}
	public void tabela(Kartodromo kartodromo) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		CorridaCampeonatoBo corridaCampeonatoBo=new CorridaCampeonatoBo();
		KartodromoBo kartodromoBo=new KartodromoBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		try {
			for (CorridaCampeonato c : corridaCampeonatoBo.listar("listaKartodromo", null, kartodromo.getId())) {
				
				
				if (c.getKartodromo().getId() == kartodromo.getId()) {
					modelo.addRow(new Object[] {c.getNome() ,CorridaCampeonatoBo.dataTela(c.getData()),c.getHora(),
							c.getPiloto().getNome() });
				}
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	public void informacoes(CorridaCampeonato corridacampeonato) {
			obj= table.getValueAt(table.getSelectedRow(), 0);
			corrida = obj.toString();
			corridacampeonato.setNome(corrida);
			CorridaCampeonatoBo corridaCampeonatoBo=new CorridaCampeonatoBo();
		try {
			corridaCampeonatoBo.listar("informacoes", corridacampeonato, 0);
			//CorridaCampeonatoDao.listar("informacoes", corridacampeonato, 0)
			new TelaInformacoes(corridaCampeonatoBo.listar("informacoes", corridacampeonato, 0).get(0));
		} catch (Exception e) {
			JOptionPane.showMessageDialog (null, "Deve ser selecionado uma corrida para prosseguir!");
			e.printStackTrace();
		}
	}
			
}
