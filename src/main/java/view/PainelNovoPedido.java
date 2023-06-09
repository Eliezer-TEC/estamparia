package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.cj.jdbc.Blob;

import controller.CamisaController;
import controller.PedidoController;
import controller.PessoaController;
import model.exception.CampoInvalidoException;
import model.exception.CpfJaUtilizadoException;
import model.vo.Camisa;
import model.vo.Pedido;
import model.vo.Pessoa;
import model.vo.SituacaoPedido;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;

public class PainelNovoPedido extends JPanel {

	private JLabel lblItensDoPedido;
	private JLabel lblNomeImg;
	private JButton btnProcurarImagem;
	private JComboBox cbCor;
	private JLabel lblCor;
	private JComboBox cbTamanho;
	private JLabel lblTamanho;
	private JLabel lblNovoPedido;
	private JButton btnVoltar;
	private JButton btnSalvar;
	private java.io.File imagemSelecionada;
	private Container contentPane;
	private Pedido pedido;
	private Camisa camisa;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblImg;
	private JButton btnAdd;
	private ArrayList<Camisa> camisas = new ArrayList<Camisa>();
	private JTable tableCamisas;
	private String[] nomesColunas = {"Tamanho", "Cor", "Estampa"};
	private JLabel lblTxt;

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
	 */
	public PainelNovoPedido(final Pessoa usuarioAutenticado) {
		this.camisa = new Camisa();
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(65dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(200dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(285dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(93dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		lblNovoPedido = new JLabel("Novo Pedido");
		add(lblNovoPedido, "4, 4");

		lblImg = new JLabel("");
		add(lblImg, "8, 2, 1, 25");

		lblTamanho = new JLabel("Tamanho");
		add(lblTamanho, "4, 6, right, default");

		cbTamanho = new JComboBox();
		cbTamanho.setToolTipText("\r\n");
		cbTamanho.setModel(new DefaultComboBoxModel(new String[] { "PP", "P", "M", "G", "GG" }));
		add(cbTamanho, "6, 6, fill, default");

		lblCor = new JLabel("Cor");
		add(lblCor, "4, 8, right, default");

		btnProcurarImagem = new JButton("Procurar Imagem");
		btnProcurarImagem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					imagemSelecionada = fileChooser.getSelectedFile();
				}
				if (imagemSelecionada != null) {
					String path = imagemSelecionada.getAbsolutePath();
					ImageIcon icon = new ImageIcon(path);
					lblImg.setIcon(icon);
					
				}
				btnAdd.setEnabled(true);
			}
		});
		add(btnProcurarImagem, "6, 10");

		cbCor = new JComboBox();
		cbCor.setModel(new DefaultComboBoxModel(
				new String[] { "VERMELHO", "AZUL", "AMARELO", "VERDE", "BRANCO", "PRETO", "CINZA", "ROSA" }));
		add(cbCor, "6, 8");

		btnAdd = new JButton("Adicionar Camiseta ao Pedido");
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Camisa c = new Camisa();
				c.setCor(cbCor.getSelectedItem().toString());
				c.setTamanho(cbTamanho.getSelectedItem().toString());
					try {
						c.setEstampa(Files.readAllBytes(imagemSelecionada.toPath()));
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao converter imagem", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				c.setNomeArquivo(imagemSelecionada.getName());
				camisas.add(c);
				btnSalvar.setEnabled(true);
				atualizarTabela();
			}
		});
		add(btnAdd, "6, 12");

		lblItensDoPedido = new JLabel("Itens do Pedido");
		add(lblItensDoPedido, "4, 14");

		lblNomeImg = new JLabel("");
		add(lblNomeImg, "6, 14");

		btnVoltar = new JButton("Voltar");
		add(btnVoltar, "4, 24");
		
		lblTxt = new JLabel("imagem de no máximo 300px X 300px");
		add(lblTxt, "8, 28");

		tableCamisas = new JTable();
		add(tableCamisas, "4, 30, 3, 1, fill, fill");
		
				btnSalvar = new JButton("Finalizar Pedido");
				btnSalvar.setEnabled(false);
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Pedido pedido = new Pedido();

						pedido.setCamisas(camisas);
						pedido.setIdPessoa(usuarioAutenticado.getId());
						pedido.setSituacaoPedido(SituacaoPedido.PEDIDO_REALIZADO);

						PedidoController controller = new PedidoController();

						try {
							controller.inserir(pedido);
							JOptionPane.showMessageDialog(null, "Camisa salva com sucesso!", "Sucesso",
									JOptionPane.INFORMATION_MESSAGE);
						camisas.clear();
						atualizarTabela();
						lblImg.setIcon(null);
						} catch (Exception excecao) {
							JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				add(btnSalvar, "8, 30");

	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

}
