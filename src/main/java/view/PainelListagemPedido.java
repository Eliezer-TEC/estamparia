package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.PedidoController;
import controller.PessoaController;
import model.exception.CampoInvalidoException;
import model.seletor.PedidoSeletor;
import model.seletor.PessoaSeletor;
import model.vo.Pedido;
import model.vo.Pessoa;
import model.vo.SituacaoPedido;

import java.awt.Font;
import javax.swing.JComboBox;

public class PainelListagemPedido extends JPanel {

	private JTable tblPedidos;
	private ArrayList<Pedido> pedidos;

	private String[] nomesColunas = { "N° do Pedido", "Situação", "Cliente", "Qtd Itens"};

	private MaskFormatter mascaraCpf;

	// componentes externos -> dependência "LGoodDatePicker" foi adicionada no
	// pom.xml
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnGerarPlanilha;
	private JButton btnExcluir;

	private JLabel lblSituacao;

	private PedidoController controller = new PedidoController();
	private Pedido pedidoSelecionado;
	private JButton btnVoltar;
	// Atributos para a PAGINAÇÃO

	private PedidoSeletor seletor = new PedidoSeletor();

	private final int TAMANHO_PAGINA = 5;
	private int paginaAtual = 1;
	private int totalPaginas = 0;
	private JButton btnVoltarPagina;
	private JButton btnAvancarPagina;
	private JLabel lblPaginacao;
	private JLabel lblNDoPedido;
	private JTextField txtIdPedido;

	private JComboBox<SituacaoPedido> cbSituacao;

	private Pessoa usuarioAutenticado;


	private void limparTabelaPedidos() {
		tblPedidos.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaPedidos() {
		this.limparTabelaPedidos();

		DefaultTableModel model = (DefaultTableModel) tblPedidos.getModel();
		for (Pedido p : pedidos) {

			Pessoa cliente = new Pessoa();
			cliente = controller.consultarCliente(p.getIdPessoa());

			Object[] novaLinhaDaTabela = new Object[5];
			novaLinhaDaTabela[0] = p.getId();
			novaLinhaDaTabela[1] = p.getSituacaoPedido();
			novaLinhaDaTabela[2] = cliente.getNome();
			novaLinhaDaTabela[3] = p.getCamisas().size();

			model.addRow(novaLinhaDaTabela);
		}
	}

	public PainelListagemPedido(final Pessoa usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
		this.setLayout(null);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setForeground(new Color(0, 0, 0));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usuarioAutenticado.isFuncionario() == true) {
					buscarPedidosComFiltros();
				}
				if (usuarioAutenticado.isFuncionario() == false) {
					buscarPedidosComFiltros();
				}
				atualizarTabelaPedidos();

			}
		});
		btnBuscar.setBounds(721, 151, 165, 37);
		this.add(btnBuscar);

		tblPedidos = new JTable();
		this.limparTabelaPedidos(); // Adicionei essa linha

		tblPedidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tblPedidos.getSelectedRow();

				if (indiceSelecionado > 0 && usuarioAutenticado.isFuncionario() == true) {
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					pedidoSelecionado = pedidos.get(indiceSelecionado - 1);
				} else {
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);
				}
			}
		});
		tblPedidos.setBounds(199, 210, 762, 188);
		this.add(tblPedidos);

		lblSituacao = new JLabel("Situação:");
		lblSituacao.setBounds(211, 97, 61, 16);
		this.add(lblSituacao);

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		btnGerarPlanilha = new JButton("Gerar Planilha ");
		btnGerarPlanilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaSelecaoDestinoArquivo = new JFileChooser();
				janelaSelecaoDestinoArquivo.setDialogTitle("Selecione um destino para a planilha...");

				int opcaoSelecionada = janelaSelecaoDestinoArquivo.showSaveDialog(null);
				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = janelaSelecaoDestinoArquivo.getSelectedFile().getAbsolutePath();
					String resultado;
					try {
						resultado = controller.gerarPlanilha(pedidos, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, resultado);
					} catch (CampoInvalidoException e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnGerarPlanilha.setBounds(468, 554, 200, 45);
		this.add(btnGerarPlanilha);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(993, 244, 165, 45);
		btnEditar.setEnabled(false);
		this.add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(993, 322, 165, 45);
		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null,
						"Confirma a exclusão do pedido selecionado?");

				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					try {
						controller.excluir(pedidoSelecionado.getId());
						JOptionPane.showMessageDialog(null, "Pedido excluído com sucesso");
						pedidos = (ArrayList<Pedido>) controller.consultarTodos();
						atualizarTabelaPedidos();
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		this.add(btnExcluir);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(468, 628, 200, 45);
		add(btnVoltar);

		lblPaginacao = new JLabel("1 / " + totalPaginas);
		lblPaginacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaginacao.setBounds(513, 458, 105, 14);
		add(lblPaginacao);
		atualizarQuantidadePaginas();

		btnVoltarPagina = new JButton("<< Voltar");
		btnVoltarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual--;
				buscarPedidosComFiltros();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		btnVoltarPagina.setEnabled(false);
		btnVoltarPagina.setBounds(392, 454, 111, 23);
		add(btnVoltarPagina);

		btnAvancarPagina = new JButton("Avançar >>");
		btnAvancarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginaAtual++;
				buscarPedidosComFiltros();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		btnAvancarPagina.setBounds(628, 454, 111, 23);
		add(btnAvancarPagina);

		lblNDoPedido = new JLabel("N° do Pedido:");
		lblNDoPedido.setBounds(552, 97, 76, 16);
		add(lblNDoPedido);

		txtIdPedido = new JTextField();
		txtIdPedido.setColumns(10);
		txtIdPedido.setBounds(629, 92, 240, 28);
		add(txtIdPedido);

		JLabel lblTitulo = new JLabel("Listagem de pedidos");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(441, 11, 240, 58);
		add(lblTitulo);
		
		SituacaoPedido[] situacaoPedidoValues = new SituacaoPedido[SituacaoPedido.values().length + 1];
		situacaoPedidoValues[0] = null; // Add null as the first element
		System.arraycopy(SituacaoPedido.values(), 0, situacaoPedidoValues, 1, SituacaoPedido.values().length);

		cbSituacao = new JComboBox<>(situacaoPedidoValues);
		cbSituacao.setBounds(264, 95, 252, 21);
		add(cbSituacao);
		








	}

	private void atualizarQuantidadePaginas() {
		int totalRegistros = controller.contarTotalRegistrosComFiltros(seletor);

		// QUOCIENTE da divisão inteira
		totalPaginas = totalRegistros / TAMANHO_PAGINA;

		// RESTO da divisão inteira
		if (totalRegistros % TAMANHO_PAGINA > 0) {
			totalPaginas++;
		}

		lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
	}

	protected void buscarPedidosComFiltros() {

		seletor = new PedidoSeletor();
		seletor.setLimite(TAMANHO_PAGINA);
		seletor.setPagina(paginaAtual);
		// seletor.setNome(txtNome.getText());
		
		if (cbSituacao != null && cbSituacao.getSelectedItem() != null) {
		    SituacaoPedido selectedSituacao = (SituacaoPedido) cbSituacao.getSelectedItem();
		    seletor.setSituacaoPedido(selectedSituacao);
		}
		
		if(txtIdPedido.getText().trim() != null && !txtIdPedido.getText().trim().isEmpty()) {
			seletor.setId(Integer.parseInt(txtIdPedido.getText()));
			}
		if (txtIdPedido.getText().trim() != null && !txtIdPedido.getText().trim().isEmpty()) {
				seletor.setId(Integer.parseInt(txtIdPedido.getText()));	
		}
		if (usuarioAutenticado.isFuncionario() == false) {
			seletor.setIdPessoa(usuarioAutenticado.getId());
		}

		// String cpfSemMascara;
		// try {
		// cpfSemMascara = (String) mascaraCpf.stringToValue(txtCPF.getText());
		// //seletor.setCpf(cpfSemMascara);
		// } catch (ParseException e1) {
		// TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		pedidos = (ArrayList<Pedido>) controller.consultarComFiltros(seletor);
		atualizarTabelaPedidos();
		atualizarQuantidadePaginas();
	}

	// Torna o btnEditar acessível externamente à essa classe
	public JButton getBtnEditar() {
		return this.btnEditar;
	}

	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}
}
