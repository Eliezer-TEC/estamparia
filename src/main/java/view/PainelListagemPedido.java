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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

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

	private String[] nomesColunas = { "N° do Pedido", "Situação", "Cliente", "Qtd Itens", "Data"};

	private MaskFormatter mascaraCpf;
	
	private DatePicker dtPedidoInicial;
	private DatePicker dtPedidoFinal;

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
	private DatePickerSettings dateSettings;

	private Pessoa usuarioAutenticado;
	private JLabel lblDataPedidoDe;
	private JLabel lblAte;


	private void limparTabelaPedidos() {
		tblPedidos.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaPedidos() {
		this.limparTabelaPedidos();

		DefaultTableModel model = (DefaultTableModel) tblPedidos.getModel();
		for (Pedido p : pedidos) {

			Pessoa cliente = new Pessoa();
			cliente = controller.consultarCliente(p.getIdPessoa());
			LocalDate data = p.getData();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Define the desired format
			String dataFormatted = data.format(formatter);

			Object[] novaLinhaDaTabela = new Object[5];
			novaLinhaDaTabela[0] = p.getId();
			novaLinhaDaTabela[1] = p.getSituacaoPedido();
			novaLinhaDaTabela[2] = cliente.getNome();
			novaLinhaDaTabela[3] = p.getCamisas().size();
			novaLinhaDaTabela[4] = p.getData().toString();
			
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
		lblSituacao.setBounds(10, 161, 61, 16);
		this.add(lblSituacao);

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		lblDataPedidoDe = new JLabel("Data de pedido. De:");
		lblDataPedidoDe.setBounds(10, 60, 154, 10);
		this.add(lblDataPedidoDe);

		dtPedidoInicial = new DatePicker(dateSettings);
		dtPedidoInicial.setBounds(160, 55, 515, 30);
		this.add(dtPedidoInicial);

		lblAte = new JLabel("Até:");
		lblAte.setBounds(10, 99, 175, 10);
		this.add(lblAte);

		dtPedidoFinal = new DatePicker(dateSettings);
		dtPedidoFinal.setBounds(160, 90, 515, 30);
		this.add(dtPedidoFinal);

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
		btnGerarPlanilha.setBounds(628, 554, 200, 45);
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
		lblNDoPedido.setBounds(276, 158, 76, 23);
		add(lblNDoPedido);

		txtIdPedido = new JTextField();
		txtIdPedido.setColumns(10);
		txtIdPedido.setBounds(362, 155, 154, 28);
		add(txtIdPedido);

		JLabel lblTitulo = new JLabel("Listagem de pedidos");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(444, 0, 240, 58);
		add(lblTitulo);
		
		cbSituacao = new JComboBox(SituacaoPedido.values());
		cbSituacao.setSelectedIndex(-1);
		cbSituacao.setBounds(64, 158, 175, 23);
		add(cbSituacao);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			cbSituacao.setSelectedIndex(-1);
			txtIdPedido.setText("");
			dtPedidoFinal.setText("");
			dtPedidoInicial.setText("");
			
			}
		});
		btnLimpar.setBounds(338, 554, 165, 45);
		add(btnLimpar);
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
		seletor.setDataInicial(dtPedidoInicial.getDate());
		seletor.setDataFinal(dtPedidoFinal.getDate());
		
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
