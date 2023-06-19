package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PainelHomeCliente {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelHomeCliente window = new PainelHomeCliente();
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
	public PainelHomeCliente() {
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
		
		JLabel lblEntrada = new JLabel("Bem-vindo à Estamparia.");
		lblEntrada.setBounds(137, 27, 221, 15);
		frame.getContentPane().add(lblEntrada);
		
		JLabel lblIconeHome = new JLabel("");
		lblIconeHome.setIcon(new ImageIcon(PainelHomeCliente.class.getResource("/icones/Camiseta.png")));
		lblIconeHome.setBounds(186, 54, 63, 82);
		frame.getContentPane().add(lblIconeHome);
		
		JLabel lblInformacao1 = new JLabel("Faça seu pedido clicando em \"Novo Pedido\",");
		lblInformacao1.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacao1.setBounds(12, 129, 426, 42);
		frame.getContentPane().add(lblInformacao1);
		
		JLabel lblInformacao2 = new JLabel("ou consulte os seus pedidos em \"Pedidos\".");
		lblInformacao2.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacao2.setBounds(12, 148, 426, 42);
		frame.getContentPane().add(lblInformacao2);
	}

}
