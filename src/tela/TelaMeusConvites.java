package tela;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

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

import bo.ConviteBo;
import bo.CorridaCampeonatoBo;
import entity.Convite;
import entity.Piloto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaMeusConvites extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMeusConvites frame = new TelaMeusConvites(new Piloto(1));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaMeusConvites(Piloto piloto) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMeusConvites.class.getResource("/tela/trofeu.jpg")));
		setTitle("Meus Convites");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setBounds(21, 52, 262, 146);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Campeonato", "Baterias", "Data", "Criador" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(102);
		table.getColumnModel().getColumn(1).setPreferredWidth(53);
		table.getColumnModel().getColumn(2).setPreferredWidth(61);
		carregarTabela(piloto);
		// contentPane.add(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 82, 315, 146);
		contentPane.add(scroll);

		JButton aceitarB = new JButton("Aceitar");
		aceitarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha("aceito", piloto);
			}
		});
		aceitarB.setBounds(335, 112, 89, 23);
		contentPane.add(aceitarB);

		JButton rejeitarB = new JButton("Rejeitar");
		rejeitarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha("rejeitado", piloto);
			}
		});
		rejeitarB.setBounds(335, 146, 89, 23);
		contentPane.add(rejeitarB);

		JButton btnNewButton_2 = new JButton("Voltar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalPiloto(piloto);
				dispose();
			}
		});
		btnNewButton_2.setBounds(335, 205, 89, 23);
		contentPane.add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("Todos convites");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(27, 57, 141, 23);
		contentPane.add(lblNewLabel);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);

		JLabel lblNewLabel_1 = new JLabel(piloto.getLogin());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 11, 443, 35);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblFundo);
		setVisible(true);
	}

	private void escolha(String tipo, Piloto piloto) {
		ConviteBo conviteBo=new ConviteBo();
		try {
			conviteBo.respostaConvite(tipo, table.getSelectedRow(), piloto);
			carregarTabela(piloto);
			JOptionPane.showMessageDialog(this, "O convite foi "+tipo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Selecione um convite");
		}
	}

	private void carregarTabela(Piloto piloto) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		ConviteBo conviteBo=new ConviteBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		try {
			for (Convite c : conviteBo.listar("convites", piloto, 0)) {
				modelo.addRow(new Object[] { c.getCampeonato().getNome(), c.getCampeonato().getTotalCorridas(),
						CorridaCampeonatoBo.dataTela(c.getDataR()), c.getCampeonato().getPiloto().getLogin()});
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
