package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class PainelNovoPedido extends JPanel {

	/**
	 * Create the panel.
	 */
	public PainelNovoPedido() {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Novo Pedido");
		add(lblNewLabel, "4, 4");
		
		JLabel lblNewLabel_1 = new JLabel("Tamanho");
		add(lblNewLabel_1, "4, 8, right, default");
		
		JComboBox comboBox = new JComboBox();
		add(comboBox, "6, 8, fill, default");
		
		JLabel lblNewLabel_2 = new JLabel("Cor");
		add(lblNewLabel_2, "4, 10, right, default");
		
		JComboBox comboBox_1 = new JComboBox();
		add(comboBox_1, "6, 10, fill, default");
		
		JButton btnProcurarImagem = new JButton("Procurar Imagem");
		add(btnProcurarImagem, "6, 12");
		
		JLabel lblNomeImg = new JLabel("");
		add(lblNomeImg, "6, 14");
		
		JLabel lblItensDoPedido = new JLabel("Itens do Pedido");
		add(lblItensDoPedido, "4, 16");

	}

}
