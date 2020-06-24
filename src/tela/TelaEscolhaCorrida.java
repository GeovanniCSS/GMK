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

import bo.CampeonatoBo;
import bo.CorridaCampeonatoBo;
import entity.Campeonato;
import entity.CorridaCampeonato;
import entity.Piloto;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaEscolhaCorrida extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolhaCorrida frame = new TelaEscolhaCorrida(new Piloto(1), new Campeonato(1));
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
	public TelaEscolhaCorrida(Piloto piloto, Campeonato campeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaEscolhaCorrida.class.getResource("/tela/trofeu.jpg")));
		setTitle("Baterias do campeonato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 101, 230, 149);
		scrollPane.setToolTipText("PC=Posição de chegada");
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Bateria", "Data", "Criador" }));
		scrollPane.setViewportView(table);
		carregarTabela(piloto, campeonato);

		JLabel lblNewLabel = new JLabel(campeonato.getNome());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 38, 434, 37);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCorrida(piloto, campeonato);
			}
		});
		btnNewButton.setBounds(302, 124, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalCampeonato(piloto, campeonato);
				dispose();
			}
		});
		btnNewButton_1.setBounds(302, 209, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Baterias do campeonato");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(24, 77, 230, 23);
		contentPane.add(lblNewLabel_1);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		contentPane.add(lblFundo);
		setVisible(true);
	}

	public void carregarTabela(Piloto piloto, Campeonato campeonato) {
		CorridaCampeonatoBo corridaCampeonatoBo=new CorridaCampeonatoBo();
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		modelo.setRowCount(0);
		try {
			for (CorridaCampeonato c : corridaCampeonatoBo.listar("numeroCorrida", null, campeonato.getId())) {
				modelo.addRow(new Object[] { c.getNome(),CorridaCampeonatoBo.dataTela(c.getData()), c.getPiloto().getNome() });
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void selecionarCorrida(Piloto piloto, Campeonato campeonato) {
		CorridaCampeonatoBo corridaCampeonatoBo=new CorridaCampeonatoBo();
		try {
			new TelaResultadosDaCorrida(piloto,
					corridaCampeonatoBo.listar("numeroCorrida", null, campeonato.getId()).get(table.getSelectedRow()));
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Nenhum bateria selecionada");
		}
	}
}
