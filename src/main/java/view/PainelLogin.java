package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.Timer;
import java.util.TimerTask;
import controller.PessoaController;
import model.exception.CampoInvalidoException;
import model.vo.Pessoa;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelLogin extends JPanel {

	private JFrame frmSistemaDeEstamparia;
	private JTextField txtEmailLogin;
	private JLabel lblEmailLogin;
	private JLabel lblSenhaLogin;
	private JButton btnEntrarLogin;
	private JButton btnNovoUsuario;
	private JLabel lblImagemLogin;
	private JLabel lblOu;
	private JPasswordField passwordField;
	private Timer timerFim;
	private Timer timerInicio;
	private JLabel lblErro;
	private PainelCadastroPessoa painelCadastro;
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
		btnNovoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastro = new PainelCadastroPessoa();
				painelCadastro.setVisible(true);
				frmSistemaDeEstamparia.setContentPane(painelCadastro);
				frmSistemaDeEstamparia.revalidate();
			}
		});
		btnNovoUsuario.setBounds(229, 203, 127, 25);
		this.add(btnNovoUsuario);

		lblImagemLogin = new JLabel("");
		lblImagemLogin.setIcon(new ImageIcon(PainelLogin.class.getResource("/icones/icons8-usuário.png")));
		lblImagemLogin.setBounds(191, 12, 57, 54);
		this.add(lblImagemLogin);

		lblOu = new JLabel("ou");
		lblOu.setBounds(200, 208, 31, 15);
		this.add(lblOu);
		
		lblErro = new JLabel("Erro ao validar usuário!");
		lblErro.setBounds(150, 252, 190, 14);
		this.add(lblErro);
		lblErro.setVisible(false);
		

	}
	public JButton getBtnNovoUsuario() {
		return btnNovoUsuario;
	}
	
	
	public JButton getBtnLogar() {
		return btnEntrarLogin;
	}

	public Pessoa autenticar() throws CampoInvalidoException {
		Pessoa usuarioAutenticado = null;
		usuarioAutenticado = new PessoaController().consultarPorLoginSenha(this.txtEmailLogin.getText(),
				this.passwordField.getText());
		if(usuarioAutenticado == null) {
			timerErro();
			limparTela();
			timerLimpar();
		}
		return usuarioAutenticado;
	}

	

	protected void limparTela() {
		txtEmailLogin.setText("");
		passwordField.setText("");
	}

	public void timerErro() {
		int delay = 0;
		int interval = 1000;
		timerInicio = new Timer();
		timerInicio.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				lblErro.setVisible(true);
				cancel();
			}
		}, delay, interval);
	}

	public void timerLimpar() {
		int delay = 4000;
		int interval = 1000;
		timerFim = new Timer();
		timerFim.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				lblErro.setVisible(false);
				cancel();
			}
		}, delay, interval);
	}
}
