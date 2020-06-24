package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.ConviteBo;
import bo.pilotoBo;
import entity.Campeonato;
import entity.Piloto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TelaNovoPiloto extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField loginTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaNovoPiloto frame = new TelaNovoPiloto(new Piloto(), new Campeonato(1));
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
	public TelaNovoPiloto(Piloto piloto, Campeonato campeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaNovoPiloto.class.getResource("/tela/trofeu.jpg")));
		setTitle("Novo piloto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 292, 144);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Piloto", "Nome"
			}
		));
		scrollPane.setViewportView(table);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);

		JLabel lblNewLabel = new JLabel(campeonato.getNome());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 11, 434, 42);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarPiloto(campeonato);
			}
		});
		btnNewButton.setBounds(312, 127, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalCampeonato(piloto, campeonato);
				dispose();
			}
		});
		btnNewButton_1.setBounds(312, 227, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Piloto:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 83, 55, 23);
		contentPane.add(lblNewLabel_1);

		loginTxt = new JTextField();
		loginTxt.setBounds(67, 84, 235, 20);
		contentPane.add(loginTxt);
		loginTxt.setColumns(10);

		JButton btnNewButton_2 = new JButton("Pesquisar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarTabela();
			}
		});
		btnNewButton_2.setBounds(308, 83, 116, 23);
		contentPane.add(btnNewButton_2);
		contentPane.add(lblFundo);

		JTree tree = new JTree();
		tree.setBounds(-7, 11, 72, 64);
		contentPane.add(tree);
		setVisible(true);
	}

	private void carregarTabela() {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		pilotoBo pilotoBo=new pilotoBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		try {
			for (Piloto piloto : pilotoBo.listar(loginTxt.getText(),null, 0)) {
				modelo.addRow(new Object[] {piloto.getLogin(),piloto.getNome() });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar tabela");
		}
	}

	private void adicionarPiloto(Campeonato campeonato) {
		ConviteBo conviteBo=new ConviteBo();
		pilotoBo pilotoBo=new pilotoBo();
		try {
			conviteBo.salvarConvite("repetido", pilotoBo.listar(loginTxt.getText(),null, 0).get(table.getSelectedRow()),
					campeonato);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}
}
