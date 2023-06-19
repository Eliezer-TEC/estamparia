package view;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PainelHomeCliente extends JPanel {


	/**
	 * Create the application.
	 */
	public PainelHomeCliente() {
		setLayout(null);

		
		JLabel lblEntrada = new JLabel("Bem-vindo à Estamparia.");
		lblEntrada.setBounds(132, 34, 238, 14);
		add(lblEntrada);
		
		JLabel lblIconeHome = new JLabel("");
		lblIconeHome.setIcon(new ImageIcon(PainelHomeCliente.class.getResource("/icones/Camiseta.png")));
		lblIconeHome.setBounds(169, 74, 58, 58);
		add(lblIconeHome);
		
		JLabel lblInformacao1 = new JLabel("Faça seu pedido clicando em \"Novo Pedido\",");
		lblInformacao1.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacao1.setBounds(111, 183, 211, 14);
		add(lblInformacao1);
		
		JLabel lblInformacao2 = new JLabel("ou consulte os seus pedidos em \"Pedidos\".");
		lblInformacao2.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacao2.setBounds(111, 208, 203, 14);
		add(lblInformacao2);
	}

	

}