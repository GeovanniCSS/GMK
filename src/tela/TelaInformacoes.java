package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.CorridaCampeonatoBo;
import entity.CorridaCampeonato;
import java.awt.Toolkit;

public class TelaInformacoes extends JFrame {

	private JPanel contentPane;
	private JLabel lblNomeDoKartodromo, lblDatadaBateria, lblDataget, lblDataDeCadastro, lblDataCadastroget;
	private JLabel lblHorario;
	private JLabel lblHoraget;
	private JLabel lblNomeBateria;
	private JLabel lblNomeget;
	private JLabel lblNomeCamp;
	private JLabel lblNomecampget;
	private JLabel lblNomedo;
	private JLabel lblNomedopilotoget;
	private JLabel lblWhatsDoPiloto;
	private JLabel lblWhatsget;
	private JLabel lblFundo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInformacoes frame = new TelaInformacoes(new CorridaCampeonato());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInformacoes(CorridaCampeonato corridacampeonato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInformacoes.class.getResource("/tela/trofeu.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu_2 = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnNewMenu_2);

		JMenuItem mnLogout = new JMenuItem("Logout");
		mnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaInicial();
				dispose();
			}
		});
		mnNewMenu_2.add(mnLogout);

		JMenuItem mnVoltar = new JMenuItem("Voltar");
		mnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalKartodromo(corridacampeonato.getKartodromo());
				dispose();
			}
		});
		mnNewMenu_2.add(mnVoltar);

		JMenuItem mnSair = new JMenuItem("Sair");
		mnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnNewMenu_2.add(mnSair);

		lblNomeDoKartodromo = new JLabel("");
		lblNomeDoKartodromo.setForeground(Color.WHITE);
		lblNomeDoKartodromo.setBounds(167, 11, 118, 14);
		contentPane.add(lblNomeDoKartodromo);

		lblDatadaBateria = new JLabel("");
		lblDatadaBateria.setForeground(Color.WHITE);
		lblDatadaBateria.setBounds(53, 53, 125, 14);
		contentPane.add(lblDatadaBateria);

		lblDataget = new JLabel();
		lblDataget.setForeground(Color.WHITE);
		lblDataget.setBounds(240, 53, 161, 14);
		contentPane.add(lblDataget);

		lblDataDeCadastro = new JLabel();
		lblDataDeCadastro.setForeground(Color.WHITE);
		lblDataDeCadastro.setBounds(53, 78, 125, 14);
		contentPane.add(lblDataDeCadastro);

		lblDataCadastroget = new JLabel("");
		lblDataCadastroget.setForeground(Color.WHITE);
		lblDataCadastroget.setBounds(239, 78, 162, 14);
		contentPane.add(lblDataCadastroget);

		lblHorario = new JLabel("Hora da Bateria");
		lblHorario.setForeground(Color.WHITE);
		lblHorario.setBounds(53, 103, 125, 14);
		contentPane.add(lblHorario);

		lblHoraget = new JLabel(corridacampeonato.getHora() + "");
		lblHoraget.setForeground(Color.WHITE);
		lblHoraget.setBounds(239, 103, 137, 14);
		contentPane.add(lblHoraget);

		lblNomeBateria = new JLabel("Nome da Bateria");
		lblNomeBateria.setForeground(Color.WHITE);
		lblNomeBateria.setBounds(53, 128, 110, 14);
		contentPane.add(lblNomeBateria);

		lblNomeget = new JLabel(corridacampeonato.getNome());
		lblNomeget.setForeground(Color.WHITE);
		lblNomeget.setBounds(239, 128, 137, 14);
		contentPane.add(lblNomeget);

		lblNomeCamp = new JLabel("Nome do campeonato");
		lblNomeCamp.setForeground(Color.WHITE);
		lblNomeCamp.setBounds(53, 153, 127, 14);
		contentPane.add(lblNomeCamp);

		lblNomecampget = new JLabel(corridacampeonato.getCampeonato().getNome());
		lblNomecampget.setForeground(Color.WHITE);
		lblNomecampget.setBounds(239, 153, 137, 14);
		contentPane.add(lblNomecampget);

		lblNomedo = new JLabel("Nome do Piloto");
		lblNomedo.setForeground(Color.WHITE);
		lblNomedo.setBounds(53, 178, 127, 14);
		contentPane.add(lblNomedo);

		lblNomedopilotoget = new JLabel(corridacampeonato.getPiloto().getNome());
		lblNomedopilotoget.setForeground(Color.WHITE);
		lblNomedopilotoget.setBounds(239, 178, 162, 14);
		contentPane.add(lblNomedopilotoget);

		lblWhatsDoPiloto = new JLabel("Numero do Piloto");
		lblWhatsDoPiloto.setForeground(Color.WHITE);
		lblWhatsDoPiloto.setBounds(53, 203, 125, 14);
		contentPane.add(lblWhatsDoPiloto);

		lblWhatsget = new JLabel(corridacampeonato.getPiloto().getCelular() + "");
		lblWhatsget.setForeground(Color.WHITE);
		lblWhatsget.setBounds(240, 203, 104, 14);
		contentPane.add(lblWhatsget);

		lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(TelaInicial.class.getResource("/tela/fundosemnum.jpg")));
		lblFundo.setBounds(0, 0, 443, 270);
		contentPane.add(lblFundo);

		preencher(corridacampeonato);

		setVisible(true);
	}

	private void preencher(CorridaCampeonato corridacampeonato) {

		lblNomeDoKartodromo.setText(corridacampeonato.getKartodromo().getNome());
		lblDatadaBateria.setText("Data da Bateria");
		lblDataget.setText(CorridaCampeonatoBo.dataTela(corridacampeonato.getData()));
		lblHorario.setText("Horario da Bateria");
		lblHoraget.setText(corridacampeonato.getHora() + "");
		lblNomeBateria.setText("Nome da Bateria");
		lblNomeget.setText(corridacampeonato.getNome());
		lblNomeCamp.setText("Nome do Campeonato");
		lblNomecampget.setText(corridacampeonato.getCampeonato().getNome());
		lblNomedo.setText("Nome do Piloto");
		lblNomedopilotoget.setText(corridacampeonato.getPiloto().getNome());
		lblWhatsDoPiloto.setText("Numero do Piloto");
		lblWhatsget.setText(corridacampeonato.getPiloto().getCelular() + "");

	}

}
