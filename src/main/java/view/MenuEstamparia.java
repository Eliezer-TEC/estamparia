package view;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private PainelListagemPedido painelListagemPedido;
	private PainelListagemUsuario painelListagemUsuario;
	
	private PainelLogin painelLogin;
	private PainelHomeCliente painelHome;
	private JMenuBar menuBar;
	private JMenu mnHome;
	private JMenu mnPedidos;
	private JMenu mnUsuario;
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
	private PainelAtualizarUsuario painelAtualizarUsuario;
	private PainelAtualizarPedido painelAtualizarPedido;

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
		frmSistemaDeEstamparia.setBounds(100, 100, 1363, 1056);
		frmSistemaDeEstamparia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeEstamparia.setExtendedState(JFrame.MAXIMIZED_BOTH);

		menuBar = new JMenuBar();
		frmSistemaDeEstamparia.setJMenuBar(menuBar);

		mnHome = new JMenu("Home");
		mnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				painelHome = new PainelHomeCliente();
				frmSistemaDeEstamparia.setContentPane(painelHome);
				frmSistemaDeEstamparia.revalidate();
			}
		});
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
		mntmListagemPedidos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				painelListagemPedido = new PainelListagemPedido();
				painelListagemPedido.setVisible(true);
				registrarCliqueBotaoEditarDoPainelListagemPedido();
				registrarCliqueBotaoVoltarDoPainelListagemPedido();
				frmSistemaDeEstamparia.setContentPane(painelListagemPedido);
				frmSistemaDeEstamparia.revalidate();
			}

		});
		mnPedidos.add(mntmListagemPedidos);

		mnUsuario = new JMenu("Usuário");
		mnUsuario.setEnabled(false);
		mnUsuario.setIcon(new ImageIcon(MenuEstamparia.class.getResource("/icones/Roupas.png")));
		menuBar.add(mnUsuario);

		mntmListagemUsuario = new JMenuItem("Listagem Usuários");
		mntmListagemUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				painelListagemUsuario = new PainelListagemUsuario();
				painelListagemUsuario.setVisible(true);
				registrarCliqueBotaoEditarDoPainelListagemCliente();
				registrarCliqueBotaoVoltarDoPainelListagemUsuario();
				frmSistemaDeEstamparia.setContentPane(painelListagemUsuario);
				frmSistemaDeEstamparia.revalidate();
			}

		});
		mnUsuario.add(mntmListagemUsuario);

		painelLogin = new PainelLogin();
		painelLogin.getBtnNovoUsuario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		painelLogin.getBtnNovoUsuario().setBounds(987, 301, 139, 33);
		painelLogin.getBtnLogar().setBounds(762, 301, 139, 33);
		frmSistemaDeEstamparia.setContentPane(painelLogin);
		painelLogin.setLayout(null);
		frmSistemaDeEstamparia.revalidate();

		bloquearTodoMenu();

		painelLogin = new PainelLogin();

		painelLogin.getBtnNovoUsuario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroPessoa = new PainelCadastroPessoa(null);
				painelCadastroPessoa.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelCadastroUsuario();
				frmSistemaDeEstamparia.setContentPane(painelCadastroPessoa);
				frmSistemaDeEstamparia.revalidate();
			}
		});

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
						frmSistemaDeEstamparia.setTitle("Bem-vindo, " + usuarioAutenticado.getNome() + " - " + " Funcionário");
						frmSistemaDeEstamparia.setContentPane(painelHome);
						
						frmSistemaDeEstamparia.revalidate();
					}
					if (usuarioAutenticado != null && usuarioAutenticado.isFuncionario() == false) {
						mnUsuario.setEnabled(false);
						mnHome.setEnabled(true);
						mnPedidos.setEnabled(true);
						painelHome = new PainelHomeCliente();
						frmSistemaDeEstamparia.setTitle("Bem-vindo, " + usuarioAutenticado.getNome() + " - " + " Cliente");
						frmSistemaDeEstamparia.setContentPane(painelHome);
						frmSistemaDeEstamparia.revalidate();
					} 
					
					

				} catch (CampoInvalidoException exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				}

			}

		});
		frmSistemaDeEstamparia.setContentPane(painelLogin);
	}

	protected void registrarCliqueBotaoVoltarDoPainelListagemPedido() {
		if (painelListagemPedido == null) {
			painelListagemPedido = new PainelListagemPedido();
		}
		painelListagemPedido.getBtnVoltar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Lógica do clique no botão Voltar
				// Mostra o painel de listagem de clientes
				painelHome = new PainelHomeCliente();
				painelHome.setVisible(true);

				frmSistemaDeEstamparia.setContentPane(painelHome);
				frmSistemaDeEstamparia.revalidate();
			}
		});
	}
	
	protected void registrarCliqueBotaoVoltarDoPainelAtualizarPedido() {
		if (painelAtualizarPedido == null) {
			painelAtualizarPedido = new PainelAtualizarPedido(null);
		}

		// Registrar o evento de clique no voltar do PainelCadastroCliente
		painelAtualizarPedido.getBtnVoltar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Lógica do clique no botão Voltar
				// Mostra o painel de listagem de clientes
				painelListagemPedido = new PainelListagemPedido();
				painelListagemPedido.setVisible(true);
				registrarCliqueBotaoEditarDoPainelListagemPedido();
				registrarCliqueBotaoVoltarDoPainelListagemPedido();
				frmSistemaDeEstamparia.setContentPane(painelListagemPedido);
				frmSistemaDeEstamparia.revalidate();
			}
		});
	}
	
	protected void registrarCliqueBotaoEditarDoPainelListagemPedido() {
		// Registro de ouvinte para o clique em um botão de um painel
		painelListagemPedido.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				painelAtualizarPedido = new PainelAtualizarPedido(painelListagemPedido.getPedidoSelecionado());
				painelAtualizarPedido.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelAtualizarPedido();

				// Atualiza a tela principal
				frmSistemaDeEstamparia.setContentPane(painelAtualizarPedido);
				frmSistemaDeEstamparia.revalidate();
			}
		});
	}

	protected void registrarCliqueBotaoEditarDoPainelListagemCliente() {
		// Registro de ouvinte para o clique em um botão de um painel
		painelListagemUsuario.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				painelAtualizarUsuario = new PainelAtualizarUsuario(painelListagemUsuario.getUsuarioSelecionado());
				painelAtualizarUsuario.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelAtualizarUsuario();

				// Atualiza a tela principal
				frmSistemaDeEstamparia.setContentPane(painelAtualizarUsuario);
				frmSistemaDeEstamparia.revalidate();
			}
		});
	}

	protected void registrarCliqueBotaoVoltarDoPainelAtualizarUsuario() {
		if (painelAtualizarUsuario == null) {
			painelAtualizarUsuario = new PainelAtualizarUsuario(null);
		}

		// Registrar o evento de clique no voltar do PainelCadastroCliente
		painelAtualizarUsuario.getBtnVoltar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Lógica do clique no botão Voltar
				// Mostra o painel de listagem de clientes
				painelListagemUsuario = new PainelListagemUsuario();
				painelListagemUsuario.setVisible(true);
				registrarCliqueBotaoEditarDoPainelListagemCliente();
				registrarCliqueBotaoVoltarDoPainelListagemUsuario();
				frmSistemaDeEstamparia.setContentPane(painelListagemUsuario);
				frmSistemaDeEstamparia.revalidate();
			}
		});
	}

	protected void registrarCliqueBotaoVoltarDoPainelListagemUsuario() {
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
				painelLogin = new PainelLogin();

				registrarCliqueBotaoVoltarDoPainelCadastroUsuario();
				registrarCliqueBotaoCadastroDoPainelLogin();
				registrarCliqueBotaoLogin();
				frmSistemaDeEstamparia.setContentPane(painelLogin);
				frmSistemaDeEstamparia.revalidate();
			}
		});
	}

	protected void registrarCliqueBotaoLogin() {
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
	}

	protected void registrarCliqueBotaoCadastroDoPainelLogin() {
		painelLogin.getBtnNovoUsuario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroPessoa = new PainelCadastroPessoa(null);
				painelCadastroPessoa.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelCadastroUsuario();
				frmSistemaDeEstamparia.setContentPane(painelCadastroPessoa);
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
