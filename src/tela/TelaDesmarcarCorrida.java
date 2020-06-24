package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.CorridaCampeonatoBo;
import entity.Campeonato;
import entity.CorridaCampeonato;
import entity.Piloto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaDesmarcarCorrida extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDesmarcarCorrida frame = new TelaDesmarcarCorrida(new Piloto(), new Campeonato());

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
	 * @param piloto
	 */
	public TelaDesmarcarCorrida(Piloto piloto, Campeonato campeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaDesmarcarCorrida.class.getResource("/tela/trofeu.jpg")));
		setTitle("Desmarcar baterias");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 248, 150);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Bateria", "Data", "Criador" }));
		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel(campeonato.getNome());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 33, 434, 28);
		contentPane.add(lblNewLabel);

		btnNewButton = new JButton("Desmarcar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCorrida(piloto, campeonato);
			}
		});
		btnNewButton.setBounds(297, 124, 109, 23);
		contentPane.add(btnNewButton);

		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);

		btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalCampeonato(piloto, campeonato);
				dispose();
			}
		});
		btnNewButton_1.setBounds(297, 216, 106, 23);
		contentPane.add(btnNewButton_1);
		carregarTabela(piloto, campeonato);
		
		lblNewLabel_1 = new JLabel("Pr\u00F3ximas baterias");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 72, 248, 15);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblFundo);
		setVisible(true);
	}

	public void carregarTabela(Piloto piloto, Campeonato campeonato) {
		CorridaCampeonatoBo corridaCampeonatoBo = new CorridaCampeonatoBo();
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		modelo.setRowCount(0);
		try {
			for (CorridaCampeonato c : corridaCampeonatoBo.listar("listaCampeonato", null, campeonato.getId())) {
				modelo.addRow(new Object[] { c.getNome(),CorridaCampeonatoBo.dataTela(c.getData()), c.getPiloto().getNome() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void selecionarCorrida(Piloto piloto, Campeonato campeonato) {
		    CorridaCampeonatoBo corridaCampeonatoBo = new CorridaCampeonatoBo();
		try {
			CorridaCampeonato cc=corridaCampeonatoBo.listar("listaCampeonato", null, campeonato.getId()).get(table.getSelectedRow());
			corridaCampeonatoBo.apagarCorrida(cc);
			JOptionPane.showMessageDialog(this, "A bateria "+cc.getNome()+" foi desmarcada");
			carregarTabela(piloto, campeonato);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Nenhuma corrida selecionada");
		}
	}

}
