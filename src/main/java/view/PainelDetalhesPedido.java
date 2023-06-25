package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;

import model.vo.Pedido;

public class PainelDetalhesPedido extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PainelDetalhesPedido(Pedido pedido) {
		setLayout(null);
		
		JLabel lblDetalhesPedido = new JLabel("Detalhes do Pedido");
		lblDetalhesPedido.setBounds(182, 10, 93, 13);
		add(lblDetalhesPedido);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(23, 269, 85, 21);
		add(btnVoltar);
		
		table = new JTable();
		table.setBounds(10, 33, 430, 226);
		add(table);

	}
}
