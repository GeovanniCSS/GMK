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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.CampeonatoBo;
import bo.CorridaCampeonatoBo;
import entity.Campeonato;
import entity.CorridaCampeonato;
import entity.Piloto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaEscolherClassificacaoC extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolherClassificacaoC frame = new TelaEscolherClassificacaoC(new Piloto(1),new Campeonato(1));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param piloto 
	 */
	public TelaEscolherClassificacaoC(Piloto piloto, Campeonato campeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaEscolherClassificacaoC.class.getResource("/tela/trofeu.jpg")));
		setTitle("Todas baterias");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecione a bateria");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(27, 50, 176, 26);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalCampeonato(piloto, campeonato);
				dispose();
			}
		});
		btnNewButton_1.setBounds(300, 216, 109, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Visualizar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCorrida(piloto,campeonato);
			}
		});
		btnNewButton_2.setBounds(300, 80, 109, 23);
		contentPane.add(btnNewButton_2);
		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/Fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 77, 237, 162);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Bateria", "Data", "Criador"
			}
		));
		scrollPane.setViewportView(table);
		carregarTabela(campeonato);
		JLabel lblNewLabel_1 = new JLabel(campeonato.getNome());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 11, 434, 39);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblFundo);
		setVisible(true);
	}
	public void carregarTabela(Campeonato campeonato) {
		DefaultTableModel modelo = (DefaultTableModel)this.table.getModel();
		CorridaCampeonatoBo corridaCampeonatoBo=new CorridaCampeonatoBo();
		modelo.setRowCount(0);
		table.setModel(modelo);
		try {
			for(CorridaCampeonato c: corridaCampeonatoBo.listar("numeroCorrida",null,campeonato.getId())) {
				modelo.addRow(new Object[] {c.getNome(),CorridaCampeonatoBo.dataTela(c.getData()),c.getPiloto().getNome()});	
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	private void selecionarCorrida(Piloto piloto,Campeonato campeonato) {
		CorridaCampeonatoBo corridaCampeonatoBo=new CorridaCampeonatoBo();
		try {
			new TelaClassificacaoCorridaC(piloto,
					corridaCampeonatoBo.listar("numeroCorrida", null, campeonato.getId()).get(table.getSelectedRow()));
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Nenhuma bateria selecionada");
		}
	}
	
}
