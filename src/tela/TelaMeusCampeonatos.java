package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.persistence.Table;
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
import entity.Campeonato;
import entity.Piloto;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaMeusCampeonatos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField pesquisarTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMeusCampeonatos frame = new TelaMeusCampeonatos(new Piloto(2));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param piloto
	 */
	public TelaMeusCampeonatos(Piloto piloto) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMeusCampeonatos.class.getResource("/tela/trofeu.jpg")));
		setTitle("Meus Campeonatos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		pesquisarTxt = new JTextField();
		pesquisarTxt.setBounds(97, 86, 210, 20);
		contentPane.add(pesquisarTxt);
		pesquisarTxt.setColumns(10);
		table = new JTable();
		table.setBounds(10, 63, 313, 176);
		contentPane.add(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Campeonato", "Baterias", "Inicio", "Criador" }));
		carregarTabela(piloto);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 108, 297, 142);
		contentPane.add(scroll);
		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCampeonato(piloto);

			}
		});
		btnNewButton.setBounds(317, 139, 89, 23);
		contentPane.add(btnNewButton);


		JLabel lblNewLabel = new JLabel("Campeonato:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 87, 86, 14);
		contentPane.add(lblNewLabel);

		JButton pesquisarB = new JButton("Pesquisar");
		pesquisarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarTabela(piloto);
			}
		});
		pesquisarB.setBounds(314, 85, 110, 23);
		contentPane.add(pesquisarB);

		JButton voltarB = new JButton("Voltar");
		voltarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalPiloto(piloto);
				dispose();
			}
		});
		voltarB.setBounds(314, 227, 89, 23);
		contentPane.add(voltarB);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);

		JLabel nomePilotoLabel = new JLabel(piloto.getLogin());
		nomePilotoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nomePilotoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nomePilotoLabel.setForeground(Color.WHITE);
		nomePilotoLabel.setBackground(Color.WHITE);
		nomePilotoLabel.setBounds(0, 33, 434, 29);
		contentPane.add(nomePilotoLabel);
		contentPane.add(lblFundo);
		setVisible(true);
	}

	private void carregarTabela(Piloto piloto) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		CampeonatoBo campeonatoBo=new CampeonatoBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		Campeonato campeonato=new Campeonato();
		campeonato.setNome(pesquisarTxt.getText());
		try {
			for (Campeonato c : campeonatoBo.listar("administrar", campeonato,piloto.getId())) {
				modelo.addRow(new Object[] { c.getNome(), c.getTotalCorridas(),CorridaCampeonatoBo.dataTela(c.getDataCadastrado()),
						c.getPiloto().getNome() });
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void selecionarCampeonato(Piloto piloto) {
		try {
			Campeonato campeonato=new Campeonato();
			CampeonatoBo campeonatoBo=new CampeonatoBo();
			campeonato.setNome(pesquisarTxt.getText());
			new TelaPrincipalCampeonato(piloto,
					campeonatoBo.listar("administrar", campeonato,piloto.getId()).get(table.getSelectedRow()));
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Selecione um campeonato");
		}

	}

}
