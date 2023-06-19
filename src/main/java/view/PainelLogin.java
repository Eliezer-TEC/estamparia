package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.PessoaController;
import model.exception.CampoInvalidoException;
import model.vo.Pessoa;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PainelLogin extends JPanel {

	private JTextField txtEmailLogin;
	private JLabel lblEmailLogin;
	private JLabel lblSenhaLogin;
	private JButton btnEntrarLogin;
	private JButton btnNovoUsuario;
	private JLabel lblImagemLogin;
	private JLabel lblOu;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public PainelLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setLayout(null);

		lblEmailLogin = new JLabel("E-mail:");
		lblEmailLogin.setBounds(116, 73, 70, 15);
		this.add(lblEmailLogin);

		txtEmailLogin = new JTextField();
		txtEmailLogin.setBounds(129, 100, 162, 19);
		this.add(txtEmailLogin);
		txtEmailLogin.setColumns(10);

		lblSenhaLogin = new JLabel("Senha:");
		lblSenhaLogin.setBounds(116, 134, 70, 15);
		this.add(lblSenhaLogin);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(129, 161, 162, 19);
		this.add(passwordField);

		btnEntrarLogin = new JButton("Entrar");
		btnEntrarLogin.setBounds(75, 203, 117, 25);
		this.add(btnEntrarLogin);

		btnNovoUsuario = new JButton("Cadastrar-se");
		btnNovoUsuario.setBounds(229, 203, 127, 25);
		this.add(btnNovoUsuario);

		lblImagemLogin = new JLabel("");
		lblImagemLogin.setIcon(new ImageIcon(PainelLogin.class.getResource("/icones/icons8-usuário.png")));
		lblImagemLogin.setBounds(191, 12, 57, 54);
		this.add(lblImagemLogin);

		lblOu = new JLabel("ou");
		lblOu.setBounds(200, 208, 31, 15);
		this.add(lblOu);
	}

	
	
	public JButton getBtnLogar() {
		return btnEntrarLogin;
	}
	
	public Pessoa autenticar() throws CampoInvalidoException {
		Pessoa usuarioAutenticado = null;
		usuarioAutenticado = new PessoaController().consultarPorLoginSenha(this.txtEmailLogin.getText(),
				this.passwordField.getText());
		return usuarioAutenticado;
	}
}
