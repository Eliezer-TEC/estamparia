package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class PainelAtualizarUsuarios {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textEmail;
	private JTextField textSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelAtualizarUsuarios window = new PainelAtualizarUsuarios();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PainelAtualizarUsuarios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAtualizarUsuario = new JLabel("Atualizar usuário");
		lblAtualizarUsuario.setBounds(129, 12, 134, 15);
		frame.getContentPane().add(lblAtualizarUsuario);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(38, 41, 70, 15);
		frame.getContentPane().add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(103, 39, 206, 19);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(48, 73, 70, 15);
		frame.getContentPane().add(lblCpf);
		
		textCpf = new JTextField();
		textCpf.setColumns(10);
		textCpf.setBounds(103, 71, 206, 19);
		frame.getContentPane().add(textCpf);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(38, 102, 70, 24);
		frame.getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(103, 105, 206, 19);
		frame.getContentPane().add(textEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(38, 138, 70, 19);
		frame.getContentPane().add(lblSenha);
		
		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(103, 133, 206, 19);
		frame.getContentPane().add(textSenha);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(48, 169, 70, 15);
		frame.getContentPane().add(lblTipo);
		
		JComboBox comboBoxTipo = new JComboBox();
		comboBoxTipo.setBounds(103, 164, 206, 24);
		frame.getContentPane().add(comboBoxTipo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(146, 220, 117, 25);
		frame.getContentPane().add(btnSalvar);
	}
}
