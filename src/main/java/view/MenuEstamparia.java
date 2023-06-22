package view;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import model.exception.CampoInvalidoException;
import model.vo.Pessoa;

import javax.swing.JMenuItem;

public class MenuEstamparia {

	private JFrame frmSistemaDeEstamparia;
	private PainelCadastroPessoa painelCadastroPessoa;
	private PainelListagemUsuario painelListagemUsuario;
	
	private PainelLogin painelLogin;
	private PainelHomeCliente painelHome;
	private JMenuBar menuBar;
	private JMenu mnHome;
	private JMenu mnPedidos;
	private JMenu mnUsuario;
	private JMenuItem mnAtualizar;
	private JMenuItem mntmNovoPedido;
	private JMenuItem mntmListagemPedidos;
	private JMenuItem mntmListagemUsuario;
	private Pessoa usuarioAutenticado;

	public Pessoa getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(Pessoa usarioAutenticado) {
		this.usuarioAutenticado = usarioAutenticado;
	}

	private PainelNovoPedido painelNovoPedido;

	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuEstamparia window = new MenuEstamparia();
					window.frmSistemaDeEstamparia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void setUIFont(java.awt.Font f) {
		// FONTE:
		// https://stackoverflow.com/questions/30479695/how-to-change-fonts-of-all-components
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value != null && value instanceof java.awt.Font)
				UIManager.put(key, f);
		}
	}

	/**
	 * Create the application.
	 */
	public MenuEstamparia() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Altera a fonte de toda a aplicação
		setUIFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
		frmSistemaDeEstamparia = new JFrame();
		frmSistemaDeEstamparia.setTitle("Sistema de Estamparia");
		frmSistemaDeEstamparia.setBounds(100, 100, 579, 300);
		frmSistemaDeEstamparia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeEstamparia.setExtendedState(JFrame.MAXIMIZED_BOTH);

		menuBar = new JMenuBar();
		frmSistemaDeEstamparia.setJMenuBar(menuBar);

		mnHome = new JMenu("Home");
		mnHome.setEnabled(false);
		mnHome.setIcon(new ImageIcon(PainelHomeCliente.class.getResource("/icones/iconeCasa.png")));
		menuBar.add(mnHome);

		mnPedidos = new JMenu("Pedidos");
		mnPedidos.setEnabled(false);
		mnPedidos.setIcon(new ImageIcon(PainelHomeCliente.class.getResource("/icones/Pedidos.png")));
		menuBar.add(mnPedidos);

		mntmNovoPedido = new JMenuItem("Novo Pedido");
		mntmNovoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelNovoPedido = new PainelNovoPedido(usuarioAutenticado);
				painelNovoPedido.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelNovoPedido();
				frmSistemaDeEstamparia.setContentPane(painelNovoPedido);
				frmSistemaDeEstamparia.revalidate();
			}
		});
		mnPedidos.add(mntmNovoPedido);

		mntmListagemPedidos = new JMenuItem("Listagem Pedidos");
		mnPedidos.add(mntmListagemPedidos);

		mnUsuario = new JMenu("Usuário");
		mnUsuario.setEnabled(false);
		mnUsuario.setIcon(new ImageIcon(MenuEstamparia.class.getResource("/icones/icons8-hanging-up-man-48.png")));
		menuBar.add(mnUsuario);

		mntmListagemUsuario = new JMenuItem("Listagem Usuário");
		mntmListagemUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				painelListagemUsuario = new PainelListagemUsuario();
				painelListagemUsuario.setVisible(true); 
				registrarCliqueBotaoVoltarDoPainelCadastroListagemUsuario();
				frmSistemaDeEstamparia.setContentPane(painelListagemUsuario);
				frmSistemaDeEstamparia.revalidate();
			}

		});
		mnUsuario.add(mntmListagemUsuario);

		mnAtualizar = new JMenuItem("Atualizar");
		mnAtualizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelCadastroPessoa = new PainelCadastroPessoa(null);
				painelCadastroPessoa.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelCadastroUsuario();

				frmSistemaDeEstamparia.setContentPane(painelCadastroPessoa);
				frmSistemaDeEstamparia.revalidate();
			}
		});
		mnUsuario.add(mnAtualizar);

		painelLogin = new PainelLogin();
		frmSistemaDeEstamparia.setContentPane(painelLogin);
		frmSistemaDeEstamparia.revalidate();

		bloquearTodoMenu();

		painelLogin = new PainelLogin();
		painelLogin.getBtnLogar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					usuarioAutenticado = painelLogin.autenticar();
					bloquearTodoMenu();
					if (usuarioAutenticado != null && usuarioAutenticado.isFuncionario() == true) {
						mnUsuario.setEnabled(true);
						mnHome.setEnabled(true);
						mnPedidos.setEnabled(true);
						painelHome = new PainelHomeCliente();
						frmSistemaDeEstamparia.setContentPane(painelHome);
						frmSistemaDeEstamparia.revalidate();
					}
					if (usuarioAutenticado != null && usuarioAutenticado.isFuncionario() == false) {
						mnUsuario.setEnabled(false);
						mnHome.setEnabled(true);
						mnPedidos.setEnabled(true);
						painelHome = new PainelHomeCliente();
						frmSistemaDeEstamparia.setContentPane(painelHome);
						frmSistemaDeEstamparia.revalidate();
					} else {
					}

				} catch (CampoInvalidoException exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				}

			}

		});
		frmSistemaDeEstamparia.setContentPane(painelLogin);
	}


	protected void registrarCliqueBotaoVoltarDoPainelCadastroListagemUsuario() {
		if (painelListagemUsuario == null) {
			painelListagemUsuario = new PainelListagemUsuario();
		}

		painelListagemUsuario.getBtnVoltar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Lógica do clique no botão Voltar
				// Mostra o painel de listagem de clientes
				painelHome = new PainelHomeCliente();
				painelHome.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelCadastroUsuario();

				frmSistemaDeEstamparia.setContentPane(painelHome);
				frmSistemaDeEstamparia.revalidate();
			}
		});
	}

	protected void registrarCliqueBotaoVoltarDoPainelNovoPedido() {
		if (painelNovoPedido == null) {
			painelNovoPedido = new PainelNovoPedido(usuarioAutenticado);
		}

		painelNovoPedido.getBtnVoltar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Lógica do clique no botão Voltar
				// Mostra o painel de listagem de clientes
				painelHome = new PainelHomeCliente();
				painelHome.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelCadastroUsuario();

				frmSistemaDeEstamparia.setContentPane(painelHome);
				frmSistemaDeEstamparia.revalidate();
			}
		});
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
				painelHome = new PainelHomeCliente();
				painelHome.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelCadastroUsuario();

				frmSistemaDeEstamparia.setContentPane(painelHome);
				frmSistemaDeEstamparia.revalidate();
			}
		});
	}

	private void bloquearTodoMenu() {
		mnHome.setEnabled(false);
		mnPedidos.setEnabled(false);
		mnUsuario.setEnabled(false);
	}
}
