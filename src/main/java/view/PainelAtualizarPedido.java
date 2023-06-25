package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.PedidoController;
import controller.PessoaController;
import model.vo.Camisa;
import model.vo.Pedido;
import model.vo.Pessoa;
import model.vo.SituacaoPedido;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class PainelAtualizarPedido extends JPanel {
	private JLabel lblAtualizarPedido;
	private JLabel lblNPedido;
	private JComboBox comboBoxSituacao;
	private JButton btnSalvar;
	private Pedido pedido;
	private JButton btnVoltar;
	private JLabel lblicon;

	private ArrayList<Camisa> camisas;
	private JTable tableCamisas;
	private String[] nomesColunas = {"Tamanho", "Cor", "Estampa"};
	
	
	
	private void limparTabela() {
		tableCamisas.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabela() {
		this.limparTabela();
		DefaultTableModel model = (DefaultTableModel) tableCamisas.getModel();
		for (Camisa c : this.camisas) {

			Object[] novaLinhaDaTabela = new Object[3];
			novaLinhaDaTabela[0] = c.getTamanho();
			novaLinhaDaTabela[1] = c.getCor();
			novaLinhaDaTabela[2] = c.getNomeArquivo();

			model.addRow(novaLinhaDaTabela);
		}
	}

	/**
	 * Create the panel.
	 * 
	 * @param pessoa
	 */
	@SuppressWarnings("unchecked")
	public PainelAtualizarPedido(Pedido PedidoParaEditar) {
		if (PedidoParaEditar != null) {
			this.pedido = PedidoParaEditar;
		}
		setLayout(null);
		
		camisas = PedidoParaEditar.getCamisas();
		

		lblAtualizarPedido = new JLabel("Atualizar pedido");
		lblAtualizarPedido.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAtualizarPedido.setBounds(641, 48, 303, 62);
		add(lblAtualizarPedido);

		lblNPedido = new JLabel("N° do pedido: " + PedidoParaEditar.getId().toString());
		lblNPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNPedido.setBounds(584, 141, 266, 15);
		add(lblNPedido);

		PedidoController controller = new PedidoController();
		Pessoa cliente = controller.consultarCliente(PedidoParaEditar.getIdPessoa());
		JLabel lblCliente = new JLabel("Cliente: " + cliente.getNome());
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCliente.setBounds(584, 178, 303, 15);
		add(lblCliente);

		

		JLabel lblQtdItens = new JLabel("Qtd Itens: " + PedidoParaEditar.getCamisas().size());
		lblQtdItens.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtdItens.setBounds(584, 221, 105, 15);
		add(lblQtdItens);

		JLabel lblSituacao = new JLabel("Situação:");
		lblSituacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSituacao.setBounds(584, 270, 70, 15);
		add(lblSituacao);

		comboBoxSituacao = new JComboBox(new String[] {  
				SituacaoPedido.PEDIDO_REALIZADO.name(), 
				SituacaoPedido.PREPARANDO_PEDIDO.name(),
				SituacaoPedido.PEDIDO_ENTREGUE.name() 
				 });
		comboBoxSituacao.setBounds(664, 270, 208, 24);
		add(comboBoxSituacao);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				String selectedSituacao = comboBoxSituacao.getSelectedItem().toString();
				SituacaoPedido situacaoPedido = SituacaoPedido.valueOf(selectedSituacao);
				pedido.setSituacaoPedido(situacaoPedido);

				
				PedidoController controller = new PedidoController();
				try {

					controller.atualizar(pedido);
					JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception excecao) {
					JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				atualizarTabela();
			}
		});
		btnSalvar.setBounds(514, 344, 162, 48);
		add(btnSalvar);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(786, 344, 162, 48);
		add(btnVoltar);
		
		lblicon = new JLabel("");
		lblicon.setIcon(new ImageIcon(PainelAtualizarPedido.class.getResource("/icones/icons8-comprar.png")));
		lblicon.setBounds(840, 45, 70, 81);
		add(lblicon);
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}
}
