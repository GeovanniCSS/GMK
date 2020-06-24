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

import bo.ConviteBo;
import bo.CorridaCampeonatoBo;
import bo.PilotoCampeonatoBo;
import entity.Campeonato;
import entity.Convite;
import entity.Piloto;
import entity.PilotoCampeonato;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Toolkit;

public class TelaMeusPilotos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton tornarB;
	private String nome;
	private JRadioButton rejeitaramR,convidadosR,participantesR;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMeusPilotos frame = new TelaMeusPilotos(null, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaMeusPilotos(Piloto piloto, Campeonato campeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMeusPilotos.class.getResource("/tela/trofeu.jpg")));
		setTitle("Meus pilotos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 291, 160);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Piloto", "Nome", "Data" }));
		scrollPane.setViewportView(table);
		carregarTabela("aceito", piloto, campeonato);
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalCampeonato(piloto, campeonato);
				dispose();
			}
		});
		btnNewButton.setBounds(311, 227, 113, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Todos pilotos ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 69, 160, 23);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(campeonato.getNome());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 24, 434, 34);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton_1 = new JButton("Participantes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarTabela("aceito", piloto, campeonato);
				tornarB.setEnabled(true);
			}
		});
		btnNewButton_1.setBounds(311, 107, 113, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Convidados");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarTabela("convidados", piloto, campeonato);
				tornarB.setEnabled(false);
			}
		});
		btnNewButton_2.setBounds(311, 136, 113, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Rejeitaram");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarTabela("rejeitado", piloto, campeonato);
				tornarB.setEnabled(false);
			}
		});
		btnNewButton_3.setBounds(311, 165, 113, 23);
		contentPane.add(btnNewButton_3);

		tornarB = new JButton("Tornar ADM");
		tornarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tornarAdm(piloto, campeonato);
			}
		});
		tornarB.setBounds(311, 193, 113, 23);
		contentPane.add(tornarB);
		
		convidadosR = new JRadioButton("Convidados");
		convidadosR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarTabela("convidados", piloto, campeonato);
				tornarB.setEnabled(false);
				rejeitaramR.getDisabledIcon();
				participantesR=new JRadioButton();
			}
		});
		convidadosR.setBounds(311, 134, 113, 23);
		//contentPane.add(convidadosR);
		
		rejeitaramR = new JRadioButton("Rejeitaram");
		rejeitaramR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarTabela("rejeitado", piloto, campeonato);
				tornarB.setEnabled(false);
			}
		});
		rejeitaramR.setBounds(311, 160, 113, 23);
		//contentPane.add(rejeitaramR);
		
		participantesR = new JRadioButton("Participantes");
		participantesR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarTabela("aceito", piloto, campeonato);
				tornarB.setEnabled(true);
			}
		});
		participantesR.setBounds(311, 107, 113, 23);
		//contentPane.add(participantesR);
		contentPane.add(lblFundo);
		setVisible(true);
	}

	private void carregarTabela(String tipo, Piloto piloto, Campeonato campeonato) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		ConviteBo conviteBo=new ConviteBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		nome = "Data resposta";
		try {
			for (Convite c : conviteBo.listar(tipo, piloto, campeonato.getId())) {
				modelo.addRow(new Object[] { c.getPiloto().getLogin(), c.getPiloto().getNome(),CorridaCampeonatoBo.dataTela(c.getDataR())});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar os pilotos");
			e.printStackTrace();
		}
	}

	private void tornarAdm(Piloto piloto, Campeonato campeonato) {
		ConviteBo conviteBo=new ConviteBo();
		PilotoCampeonatoBo pilotoCampeonatoBo=new PilotoCampeonatoBo();
		try {
			Convite c = conviteBo.listar("aceito", piloto, campeonato.getId()).get(table.getSelectedRow());
			PilotoCampeonato pc = pilotoCampeonatoBo.listar("encontrar", c.getPiloto(), campeonato.getId()).get(0);
			pilotoCampeonatoBo.salvarPiloto("tornar", null, null, pc);
			JOptionPane.showMessageDialog(this,
					"O piloto " + pc.getPiloto().getLogin() + " tornou-se ADM do " + pc.getCampeonato().getNome());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Selecione um piloto");
			e.printStackTrace();
		}

	}
}
