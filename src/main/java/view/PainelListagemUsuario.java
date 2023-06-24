package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;

import controller.PessoaController;
import model.exception.CampoInvalidoException;
import model.seletor.PessoaSeletor;
import model.vo.Pessoa;
import javax.swing.SwingConstants;

public class PainelListagemUsuario extends JPanel {
	private JTable tblUsuarios;
	private ArrayList<Pessoa> pessoas;
	private String[] nomesColunas = { "Nome", "CPF", "Email", "Senha", "Funcionario?" };
	private JTextField txtNome;
	private MaskFormatter mascaraCpf;
	private JFormattedTextField txtCPF;

	// componentes externos -> dependência "LGoodDatePicker" foi adicionada no
	// pom.xml
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnGerarPlanilha;
	private JButton btnExcluir;
	private JLabel lblCpf;
	private JLabel lblNome;

	private PessoaController controller = new PessoaController();
	private Pessoa usuarioSelecionado;

	// Atributos para a PAGINAÇÃO

	private PessoaSeletor seletor = new PessoaSeletor();
	private JButton btnVoltar;
	private final int TAMANHO_PAGINA = 5;
	private int paginaAtual = 1;
	private int totalPaginas = 0;
	private JButton btnVoltarPagina;
	private JButton btnAvancarPagina;
	private JLabel lblPaginacao;

	private void limparTabelaClientes() {
		tblUsuarios.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaClientes() {
		this.limparTabelaClientes();

		DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();

		for (Pessoa p : pessoas) {
			Object[] novaLinhaDaTabela = new Object[5];
			novaLinhaDaTabela[0] = p.getNome();
			novaLinhaDaTabela[1] = p.getCpf();
			novaLinhaDaTabela[2] = p.getEmail();
			novaLinhaDaTabela[3] = p.getSenha();
			novaLinhaDaTabela[4] = p.isFuncionario() ? "Sim" : "Não";

			model.addRow(novaLinhaDaTabela);
		}
	}

	public PainelListagemUsuario() {
		this.setLayout(null);

		btnBuscar = new JButton("Buscar COM FILTROS");
		btnBuscar.setBackground(Color.GRAY);
		btnBuscar.setForeground(new Color(0, 0, 0));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarClientesComFiltros();
				atualizarTabelaClientes();
			}
		});
		btnBuscar.setBounds(209, 95, 240, 35);
		this.add(btnBuscar);

		tblUsuarios = new JTable();
		this.limparTabelaClientes(); // Adicionei essa linha

		tblUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tblUsuarios.getSelectedRow();

				if (indiceSelecionado > 0) {
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					usuarioSelecionado = pessoas.get(indiceSelecionado - 1);
				} else {
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);
				}
			}
		});
		tblUsuarios.setBounds(25, 164, 650, 133);
		this.add(tblUsuarios);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 25, 61, 16);
		this.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(160, 20, 240, 28);
		this.add(txtNome);
		txtNome.setColumns(10);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(410, 25, 40, 16);
		this.add(lblCpf);

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
			txtCPF = new JFormattedTextField(mascaraCpf);
			txtCPF.setBounds(450, 19, 120, 28);
			this.add(txtCPF);
			txtCPF.setColumns(10);
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
						resultado = controller.gerarPlanilha(pessoas, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, resultado);
					} catch (CampoInvalidoException e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnGerarPlanilha.setBounds(25, 375, 200, 45);
		this.add(btnGerarPlanilha);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(250, 375, 200, 45);
		btnEditar.setEnabled(false);
		this.add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(475, 375, 200, 45);
		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null,
						"Confirma a exclusão do usuário selecionado?");

				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					try {
						controller.excluir(usuarioSelecionado.getId());
						JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
						pessoas = (ArrayList<Pessoa>) controller.consultarTodos();
						atualizarTabelaClientes();
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		this.add(btnExcluir);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(250, 469, 200, 45);
		add(btnVoltar);

		btnVoltarPagina = new JButton("<< Voltar");
		btnVoltarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual--;
				buscarClientesComFiltros();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		btnVoltarPagina.setEnabled(false);
		btnVoltarPagina.setBounds(175, 319, 111, 23);
		add(btnVoltarPagina);

		btnAvancarPagina = new JButton("Avançar >>");
		btnAvancarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginaAtual++;
				buscarClientesComFiltros();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		btnAvancarPagina.setBounds(386, 319, 111, 23);
		add(btnAvancarPagina);

		lblPaginacao = new JLabel("1 / " + totalPaginas);
		lblPaginacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaginacao.setBounds(283, 323, 105, 14);
		add(lblPaginacao);

		atualizarQuantidadePaginas();

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

	protected void buscarClientesComFiltros() {
		seletor = new PessoaSeletor();

		seletor.setNome(txtNome.getText());

		String cpfSemMascara;
		try {
			cpfSemMascara = (String) mascaraCpf.stringToValue(txtCPF.getText());
			seletor.setCpf(cpfSemMascara);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}

		pessoas = (ArrayList<Pessoa>) controller.consultarComFiltros(seletor);
		atualizarTabelaClientes();
	}

	// Torna o btnEditar acessível externamente à essa classe
	public JButton getBtnEditar() {
		return this.btnEditar;
	}

	public Pessoa getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}
}
