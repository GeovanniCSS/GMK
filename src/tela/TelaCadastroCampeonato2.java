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

import bo.CampeonatoPontuacaoBo;
import entity.Campeonato;
import entity.CampeonatoPontuacao;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaCadastroCampeonato2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField posicaoTxt;
	private JTextField pontuacaoTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCampeonato2 frame = new TelaCadastroCampeonato2(new Campeonato(2));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param object
	 */
	public TelaCadastroCampeonato2(Campeonato campeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroCampeonato2.class.getResource("/tela/trofeu.jpg")));
		setTitle("Pontos do campeonato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(campeonato.getNome());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(0, 11, 434, 27);
		contentPane.add(lblNewLabel);

		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);

		JLabel lblNewLabel_2 = new JLabel("Adicione quantos pontos valem cada posi\u00E7\u00E3o");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(32, 49, 277, 28);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Pr\u00F3ximo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximaTela(campeonato);
			}
		});
		btnNewButton.setBounds(318, 227, 89, 23);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 78, 218, 136);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Posi\u00E7\u00E3o", "Pontua\u00E7\u00E3o" }));
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("Posi\u00E7\u00E3o");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(273, 88, 65, 14);
		contentPane.add(lblNewLabel_1);

		posicaoTxt = new HintTextField("0");
		posicaoTxt.setBounds(338, 85, 58, 20);
		posicaoTxt.setDocument(new Controller());
		contentPane.add(posicaoTxt);
		posicaoTxt.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Pontua\u00E7\u00E3o");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(273, 120, 65, 14);
		contentPane.add(lblNewLabel_1_1);

		pontuacaoTxt = new HintTextField("0");
		pontuacaoTxt.setColumns(10);
		pontuacaoTxt.setDocument(new Controller());
		pontuacaoTxt.setBounds(338, 117, 58, 20);
		contentPane.add(pontuacaoTxt);

		JButton btnNewButton_1 = new JButton("Salvar pontua\u00E7\u00E3o");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarPontuacao(campeonato);
			}
		});
		btnNewButton_1.setBounds(273, 144, 135, 23);
		contentPane.add(btnNewButton_1);
		contentPane.add(lblFundo);
		setVisible(true);
	}

	public void carregarTabela(Campeonato campeonato) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		CampeonatoPontuacaoBo campeonatoPontuacaoBo = new CampeonatoPontuacaoBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		try {
			for (CampeonatoPontuacao c : campeonatoPontuacaoBo.listar("todosCampeonato", null, campeonato.getId())) {
				modelo.addRow(new Object[] { c.getPosicao(), c.getPontuacao() });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void salvarPontuacao(Campeonato campeonato) {
		CampeonatoPontuacaoBo campeonatoPontuacaoBo = new CampeonatoPontuacaoBo();
		CampeonatoPontuacao cp = new CampeonatoPontuacao(0, Integer.parseInt(posicaoTxt.getText()),
				Integer.parseInt(pontuacaoTxt.getText()), campeonato);
		try {
			campeonatoPontuacaoBo.salvarPontuacao(cp);
			carregarTabela(campeonato);
			posicaoTxt.setText("");
			pontuacaoTxt.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	private void proximaTela(Campeonato campeonato) {
		CampeonatoPontuacaoBo campeonatoPontuacaoBo = new CampeonatoPontuacaoBo();
		try {
			if (campeonatoPontuacaoBo.listar("todosCampeonato", null, campeonato.getId()).size() < 3) {
				JOptionPane.showMessageDialog(this, "O Campeonato deve ter no mínimo 3 posições salvas");
			} else {
				new TelaConvidar(campeonato);
				dispose();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}
}
