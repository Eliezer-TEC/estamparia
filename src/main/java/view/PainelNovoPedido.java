package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.cj.jdbc.Blob;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.event.ActionEvent;

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

	/**
	 * Create the panel.
	 */
	public PainelNovoPedido() {
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(42dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(200dlu;default):grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(285dlu;default)"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		lblNovoPedido = new JLabel("Novo Pedido");
		add(lblNovoPedido, "4, 4");

		lblTamanho = new JLabel("Tamanho");
		add(lblTamanho, "4, 8, right, default");

		cbTamanho = new JComboBox();
		add(cbTamanho, "6, 8, fill, default");

		lblCor = new JLabel("Cor");
		add(lblCor, "4, 10, right, default");

		cbCor = new JComboBox();
		add(cbCor, "6, 10");

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
		add(btnProcurarImagem, "6, 12");

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
		//lblImagem = new JLabel();
		//lblImagem.setBounds(10, 161, 493, 252);
		//contentPane.add(lblImagem);
		
		lblItensDoPedido = new JLabel("Itens do Pedido");
		add(lblItensDoPedido, "4, 18");

		btnSalvar = new JButton("Salvar");
		add(btnSalvar, "4, 22");

		btnVoltar = new JButton("Voltar");
		add(btnVoltar, "4, 24");

	}

}
