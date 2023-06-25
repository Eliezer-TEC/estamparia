package view;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.vo.Pessoa;
import java.awt.Font;

public class PainelHomeCliente extends JPanel {


	/**
	 * Create the application.
	 */
	public PainelHomeCliente() {
		setLayout(null);

		
		JLabel lblEntrada = new JLabel("Bem-vindo à Estamparia.");
		lblEntrada.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEntrada.setBounds(454, 149, 296, 87);
		add(lblEntrada);
		
		JLabel lblIconeHome = new JLabel("");
		lblIconeHome.setIcon(new ImageIcon(PainelHomeCliente.class.getResource("/icones/Camiseta.png")));
		lblIconeHome.setBounds(539, 247, 113, 107);
		add(lblIconeHome);
		
		JLabel lblInformacao1 = new JLabel("Faça seu pedido clicando em \"Novo Pedido\",");
		lblInformacao1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInformacao1.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacao1.setBounds(324, 387, 509, 58);
		add(lblInformacao1);
		
		JLabel lblInformacao2 = new JLabel("ou consulte os seus pedidos em \"Pedidos\".");
		lblInformacao2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInformacao2.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacao2.setBounds(296, 426, 572, 72);
		add(lblInformacao2);
	}

	

}