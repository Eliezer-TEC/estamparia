package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import view.PainelCadastroCliente;



public class MenuEstamparia {

	private JFrame frmSistemaDeEstamparia;
	private PainelCadastroCliente painelCadastroCliente;
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
		
		JMenuBar menuBar = new JMenuBar();
		//Como alterar a orientação da barra de menus
		//menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		frmSistemaDeEstamparia.setJMenuBar(menuBar);
		
		JMenu mnCliente = new JMenu("Cliente");
		mnCliente.setIcon(new ImageIcon(MenuEstamparia.class.getResource("/icones/icons8-hanging-up-man-48.png")));
		menuBar.add(mnCliente);
		
		JMenuItem mntmCadastroCliente = new JMenuItem("Cadastro");
		mntmCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroCliente = new PainelCadastroCliente(null);
				painelCadastroCliente.setVisible(true);
				
				//Atualiza a tela principal
				frmSistemaDeEstamparia.setContentPane(painelCadastroCliente);
				frmSistemaDeEstamparia.revalidate();
			}
		});
		mntmCadastroCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmCadastroCliente.setIcon(new ImageIcon(MenuEstamparia.class.getResource("/icones/icons8-cadastro-20.png")));
		mnCliente.add(mntmCadastroCliente);
}
	
}
