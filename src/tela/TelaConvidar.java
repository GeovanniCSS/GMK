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

import bo.ConviteBo;
import bo.pilotoBo;
import bo.PilotoCampeonatoBo;
import entity.Campeonato;
import entity.Piloto;
import java.awt.Toolkit;

public class TelaConvidar extends JFrame {

	private JPanel contentPane;
	private JTextField loginTxt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConvidar frame = new TelaConvidar(new Campeonato(3));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaConvidar(Campeonato campeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaConvidar.class.getResource("/tela/trofeu.jpg")));
		setTitle("Pilotos do Campeonato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Piloto:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(26, 65, 96, 31);
		contentPane.add(lblNewLabel);

		loginTxt = new JTextField();
		loginTxt.setBounds(69, 71, 195, 20);
		contentPane.add(loginTxt);
		loginTxt.setColumns(10);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setBounds(21, 52, 262, 146);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {  "Piloto","Nome" }));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(26, 107, 238, 128);
		contentPane.add(scroll);

		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 adicionarPiloto(campeonato);
			}
		});
		btnNewButton.setBounds(287, 110, 115, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Convidados");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarTabela(campeonato);
			}
		});
		btnNewButton_1.setBounds(287, 139, 115, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_3 = new JButton("Pesquisar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnNewButton_3.setBounds(287, 70, 115, 23);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Finalizar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalCampeonato(campeonato.getPiloto(), campeonato);
				dispose();
			}
		});
		btnNewButton_4.setBounds(287, 212, 115, 23);
		contentPane.add(btnNewButton_4);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		
		
		JLabel lblNewLabel_2 = new JLabel(campeonato.getPiloto().getLogin());
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 11, 210, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(campeonato.getNome());
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(116, 21, 171, 31);
		contentPane.add(lblNewLabel_3);
		contentPane.add(lblFundo);
		setVisible(true);
	}

	private void pesquisar() {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		pilotoBo pilotoBo=new pilotoBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		try {
			for (Piloto piloto : pilotoBo.listar( loginTxt.getText(), null, 0)) {
				modelo.addRow(new Object[] { piloto.getLogin(),piloto.getNome() });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Digite algum nome");
		}
	}
	private void adicionarPiloto(Campeonato campeonato) {
		pilotoBo pilotoBo=new pilotoBo();
		ConviteBo conviteBo=new ConviteBo();
		try {
			conviteBo.salvarConvite("repetido", pilotoBo.listar( loginTxt.getText(),null, 0).get(table.getSelectedRow()),
					campeonato);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
	}
	private void carregarTabela(Campeonato campeonato) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		pilotoBo pilotoBo=new pilotoBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		try {
			for (Piloto piloto : pilotoBo.listar("campeonato",null, campeonato.getId())) {
				modelo.addRow(new Object[] { piloto.getNome(), piloto.getLogin() });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar tabela");
		}
	}	
}
