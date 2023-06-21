package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.cj.jdbc.Blob;

import controller.CamisaController;
import controller.PessoaController;
import model.exception.CampoInvalidoException;
import model.exception.CpfJaUtilizadoException;
import model.vo.Camisa;
import model.vo.Pedido;
import model.vo.Pessoa;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

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
	private JLabel lblImagem;
	private Container contentPane;
	private JButton btnMostrarImg;
	private Pedido pedido;
	private Camisa camisa;
	private JLabel lblEmail;
	private JTextField txtEmail;

	/**
	 * Create the panel.
	 */
	public PainelNovoPedido(Camisa camisaParaEditar) {
		if (camisaParaEditar != null) {
			this.camisa = camisaParaEditar;
		} else {
			this.camisa = new Camisa();
		}
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(42dlu;default)"),
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
				FormSpecs.DEFAULT_ROWSPEC,}));

		lblNovoPedido = new JLabel("Novo Pedido");
		add(lblNovoPedido, "4, 4");
		
				lblTamanho = new JLabel("Tamanho");
				add(lblTamanho, "4, 6, right, default");
		
				cbTamanho = new JComboBox();
				cbTamanho.setToolTipText("\r\n");
				cbTamanho.setModel(new DefaultComboBoxModel(new String[] { "PP", "P", "M", "G" }));
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
			}
		});
		
				cbCor = new JComboBox();
				cbCor.setModel(new DefaultComboBoxModel(
						new String[] { "VERMELO", "AZUL", "AMARELO", "VERDE", "BRANCO", "PRETO", "CINZA", "ROSA" }));
				add(cbCor, "6, 8");
		
	
		
		
		lblNomeImg = new JLabel("");
		add(lblNomeImg, "6, 14");

		btnMostrarImg = new JButton("Mostrar Imagem");
		btnMostrarImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (imagemSelecionada != null) {
					String path = imagemSelecionada.getAbsolutePath();
					ImageIcon icon = new ImageIcon(path);
					lblImagem.setIcon(icon);
				}
			}
		});
		add(btnMostrarImg, "6, 16");

		// PERGUNTA
		// lblImagem = new JLabel();
		// lblImagem.setBounds(10, 161, 493, 252);
		// contentPane.add(lblImagem);

		lblItensDoPedido = new JLabel("Itens do Pedido");
		add(lblItensDoPedido, "4, 18");

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbTamanho.getSelectedIndex() == 0) {
					camisa.setTamanho("PP");
				}
				if (cbTamanho.getSelectedIndex() == 1) {
					camisa.setTamanho("P");
				}
				if (cbTamanho.getSelectedIndex() == 2) {
					camisa.setTamanho("M");
				}
				if (cbTamanho.getSelectedIndex() == 3) {
					camisa.setTamanho("G");
				}

				if (cbCor.getSelectedIndex() == 0) {
					camisa.setCor("VERMELO");
				}
				if (cbCor.getSelectedIndex() == 1) {
					camisa.setCor("AZUL");
				}
				if (cbCor.getSelectedIndex() == 2) {
					camisa.setCor("AMARELO");
				}
				if (cbCor.getSelectedIndex() == 3) {
					camisa.setCor("VERDE");
				}
				if (cbCor.getSelectedIndex() == 4) {
					camisa.setCor("BRANCO");
				}
				if (cbCor.getSelectedIndex() == 5) {
					camisa.setCor("PRETO");
				}
				if (cbCor.getSelectedIndex() == 6) {
					camisa.setCor("CINZA");
				}
				if (cbCor.getSelectedIndex() == 7) {
					camisa.setCor("ROSA");
				}
			
			
				CamisaController controller = new CamisaController();

				try {
					controller.inserir(camisa);
					JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception excecao) {
					JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		add(btnSalvar, "4, 22");

		btnVoltar = new JButton("Voltar");
		add(btnVoltar, "4, 24");

	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

}
