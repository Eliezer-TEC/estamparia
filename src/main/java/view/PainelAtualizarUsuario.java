package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class PainelAtualizarUsuario extends JPanel {
	private JTextField textNome;
	private JTextField textCPF;
	private JTextField textEmail;
	private JTextField textSenha;

	/**
	 * Create the panel.
	 */
	public PainelAtualizarUsuario() {
		setLayout(null);
		
		JLabel lblAtualizarCadastro = new JLabel("Atualizar cadastro");
		lblAtualizarCadastro.setBounds(139, 12, 172, 15);
		add(lblAtualizarCadastro);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(59, 41, 70, 15);
		add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(119, 39, 208, 19);
		add(textNome);
		textNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(72, 80, 70, 15);
		add(lblCpf);
		
		textCPF = new JTextField();
		textCPF.setColumns(10);
		textCPF.setBounds(119, 78, 208, 19);
		add(textCPF);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(59, 116, 70, 15);
		add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(119, 114, 208, 19);
		add(textEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(59, 157, 70, 15);
		add(lblSenha);
		
		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(119, 155, 208, 19);
		add(textSenha);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(72, 196, 70, 15);
		add(lblTipo);
		
		JComboBox comboBoxTipo = new JComboBox();
		comboBoxTipo.setBounds(119, 191, 208, 24);
		add(comboBoxTipo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(86, 246, 117, 25);
		add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(248, 248, 117, 25);
		add(btnCancelar);

	}
}
