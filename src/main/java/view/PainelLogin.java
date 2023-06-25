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
	private PainelCadastroPessoa painelCadastroPessoa;
	private PainelLogin painelLogin;
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
		lblEmailLogin.setBounds(841, 129, 70, 15);
		this.add(lblEmailLogin);

		txtEmailLogin = new JTextField();
		txtEmailLogin.setBounds(841, 155, 204, 31);
		this.add(txtEmailLogin);
		txtEmailLogin.setColumns(10);

		lblSenhaLogin = new JLabel("Senha:");
		lblSenhaLogin.setBounds(841, 210, 70, 15);
		this.add(lblSenhaLogin);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(841, 236, 204, 31);
		this.add(passwordField);

		btnEntrarLogin = new JButton("Entrar");
		btnEntrarLogin.setBounds(777, 300, 134, 40);
		this.add(btnEntrarLogin);

		btnNovoUsuario = new JButton("Cadastrar-se");
		btnNovoUsuario.setBounds(979, 300, 127, 40);
		this.add(btnNovoUsuario);

		lblImagemLogin = new JLabel("");
		lblImagemLogin.setIcon(new ImageIcon(PainelLogin.class.getResource("/icones/icons8-usuário.png")));
		lblImagemLogin.setBounds(909, 44, 82, 74);
		this.add(lblImagemLogin);

		lblOu = new JLabel("ou");
		lblOu.setBounds(938, 313, 31, 15);
		this.add(lblOu);

		lblErro = new JLabel("Erro ao validar usuário!");
		lblErro.setBounds(892, 360, 190, 14);
		this.add(lblErro);
		lblErro.setVisible(false);

	}

	protected void registrarCliqueBotaoVoltarDoPainelCadastroUsuario() {
		if (painelCadastroPessoa == null) {
			painelCadastroPessoa = new PainelCadastroPessoa(null);
		}

		painelCadastroPessoa.getBtnVoltar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Lógica do clique no botão Voltar
				// Mostra o painel de listagem de clientes
				painelLogin = new PainelLogin();
				painelLogin.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelCadastroUsuario();
				
			}
		});

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
		if (usuarioAutenticado == null) {
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
