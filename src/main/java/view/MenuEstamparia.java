package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

public class MenuEstamparia {

	private JFrame frmSistemaDeEstamparia;
	private PainelCadastroPessoa painelCadastroCliente;
	private PainelLogin painelLogin;
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
	public static void setUIFont (java.awt.Font f){
		//FONTE: https://stackoverflow.com/questions/30479695/how-to-change-fonts-of-all-components
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get (key);
			if (value != null && value instanceof java.awt.Font)
				UIManager.put (key, f);
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
		//Altera a fonte de toda a aplicação
		setUIFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
		frmSistemaDeEstamparia = new JFrame();
		frmSistemaDeEstamparia.setTitle("Sistema de Estamparia");
		frmSistemaDeEstamparia.setBounds(100, 100, 579, 300);
		frmSistemaDeEstamparia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeEstamparia.setExtendedState(JFrame.MAXIMIZED_BOTH);

		JMenuBar menuBar = new JMenuBar();
		frmSistemaDeEstamparia.setJMenuBar(menuBar);

		JMenu mnHome = new JMenu("Home");
		mnHome.setEnabled(false);
		mnHome.setIcon(new ImageIcon(PainelHomeCliente.class.getResource("/icones/iconeCasa.png")));
		menuBar.add(mnHome);

		JMenu mnPedidos = new JMenu("Pedidos");
		mnPedidos.setEnabled(false);
		mnPedidos.setIcon(new ImageIcon(PainelHomeCliente.class.getResource("/icones/Pedidos.png")));
		menuBar.add(mnPedidos);

		JMenu mnNovoPedido = new JMenu("Novo pedido");
		mnNovoPedido.setEnabled(false);
		mnNovoPedido.setIcon(new ImageIcon(PainelHomeCliente.class.getResource("/icones/Comprar.png")));
		menuBar.add(mnNovoPedido);

		painelLogin = new PainelLogin();
		frmSistemaDeEstamparia.setContentPane(painelLogin);
		frmSistemaDeEstamparia.revalidate();
	}

}
