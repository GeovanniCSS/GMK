package tela;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entity.Campeonato;
import entity.CampeonatoPontuacao;
import entity.Piloto;
import javax.swing.table.DefaultTableModel;

import bo.CampeonatoPontuacaoBo;

import java.awt.Color;
import java.awt.Toolkit;

public class TelaValorDosPontos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField vTxt;
	private JTextField pTxt;
	private JTextField lTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaValorDosPontos frame = new TelaValorDosPontos(new Piloto(),new Campeonato());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param campeonato 
	 * @param piloto 
	 */
	public TelaValorDosPontos(Piloto piloto, Campeonato campeonato) {
		setTitle("Pontos do campeonato");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaValorDosPontos.class.getResource("/tela/trofeu.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 82, 154, 151);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Posi\u00E7\u00E3o", "Pontua\u00E7\u00E3o"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Valor de cada posi\u00E7\u00E3o");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(42, 69, 154, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pontos por");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(250, 71, 154, 14);
		contentPane.add(lblNewLabel_1);
		
		vTxt = new JTextField();
		vTxt.setBounds(291, 105, 96, 20);
		contentPane.add(vTxt);
		vTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("+V:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setToolTipText("Volta mais r\u00E1pida");
		lblNewLabel_2.setBounds(250, 108, 48, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("+L:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setToolTipText("Mais voltas na lideran\u00E7a");
		lblNewLabel_3.setBounds(250, 149, 48, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Pole:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setToolTipText("Pole position");
		lblNewLabel_4.setBounds(250, 188, 48, 14);
		contentPane.add(lblNewLabel_4);
		
		pTxt = new JTextField();
		pTxt.setBounds(291, 185, 96, 20);
		contentPane.add(pTxt);
		pTxt.setColumns(10);
		
		lTxt = new JTextField();
		lTxt.setBounds(291, 146, 96, 20);
		contentPane.add(lTxt);
		lTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel(campeonato.getNome());
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(0, 11, 434, 35);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalCampeonato(piloto, campeonato);
				dispose();
			}
		});
		btnNewButton.setBounds(315, 227, 89, 23);
		contentPane.add(btnNewButton);
		JLabel lblFundo = new JLabel("Informa\u00E7\u00F5es");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		carregarTabela(campeonato);
		contentPane.add(lblFundo);
		this.setVisible(true);
	}
	private void carregarTabela(Campeonato campeonato) {
		DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
		CampeonatoPontuacaoBo campeonatoPontuacaoBo = new CampeonatoPontuacaoBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		try {
			for (CampeonatoPontuacao c : campeonatoPontuacaoBo.listar("todosCampeonato", null, campeonato.getId())) {
				modelo.addRow(new Object[] { c.getPosicao(), c.getPontuacao() });
			}
			lTxt.setText(campeonato.getpLideranca()+"");
			vTxt.setText(campeonato.getpVMRapida()+"");
			pTxt.setText(campeonato.getPole()+"");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}
