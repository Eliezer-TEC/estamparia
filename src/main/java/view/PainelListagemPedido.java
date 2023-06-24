package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class PainelListagemPedido extends JPanel {
	private JTable tablePedidos;

	/**
	 * Create the panel.
	 */
	public PainelListagemPedido() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pedidos");
		lblNewLabel.setBounds(23, 26, 108, 13);
		add(lblNewLabel);
		
		tablePedidos = new JTable();
		tablePedidos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"N\u00BA pedido", "Data do pedidos", "Status"
			}
		));
		tablePedidos.getColumnModel().getColumn(1).setPreferredWidth(109);
		tablePedidos.setBounds(383, 201, -320, -93);
		add(tablePedidos);
		
		JButton btnGerarPlanilha = new JButton("Gerar planilha");
		btnGerarPlanilha.setBounds(23, 263, 155, 25);
		add(btnGerarPlanilha);
		
		JButton btnVerDetalhes = new JButton("Ver detalhes");
		btnVerDetalhes.setBounds(277, 42, 142, 25);
		add(btnVerDetalhes);
		
		JButton btnEditarStatus = new JButton("Editar status");
		btnEditarStatus.setBounds(202, 263, 142, 25);
		add(btnEditarStatus);
		
		JButton btnExcluirPedido = new JButton("Excluir");
		btnExcluirPedido.setBounds(382, 263, 142, 25);
		add(btnExcluirPedido);

	}
}
