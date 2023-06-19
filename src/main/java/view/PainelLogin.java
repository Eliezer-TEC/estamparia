package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PainelLogin extends JPanel {

	private JTextField txtEmailLogin;
	private JTextField textSenhaLogin;

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
		
		JLabel lblEmailLogin = new JLabel("E-mail:");
		lblEmailLogin.setBounds(116, 73, 70, 15);
		this.add(lblEmailLogin);
		
		txtEmailLogin = new JTextField();
		txtEmailLogin.setBounds(129, 100, 162, 19);
		this.add(txtEmailLogin);
		txtEmailLogin.setColumns(10);
		
		JLabel lblSenhaLogin = new JLabel("Senha:");
		lblSenhaLogin.setBounds(116, 134, 70, 15);
		this.add(lblSenhaLogin);
		
		textSenhaLogin = new JTextField();
		textSenhaLogin.setColumns(10);
		textSenhaLogin.setBounds(129, 161, 162, 19);
		this.add(textSenhaLogin);
		
		JButton btnEntrarLogin = new JButton("Entrar");
		btnEntrarLogin.setBounds(75, 203, 117, 25);
		this.add(btnEntrarLogin);
		
		JButton btnNovoUsuario = new JButton("Cadastrar-se");
		btnNovoUsuario.setBounds(229, 203, 127, 25);
		this.add(btnNovoUsuario);
		
		JLabel lblImagemLogin = new JLabel("");
		lblImagemLogin.setIcon(new ImageIcon(PainelLogin.class.getResource("/icones/icons8-usuário.png")));
		lblImagemLogin.setBounds(191, 12, 57, 54);
		this.add(lblImagemLogin);
		
		JLabel lblOu = new JLabel("ou");
		lblOu.setBounds(200, 208, 31, 15);
		this.add(lblOu);
	}
}
