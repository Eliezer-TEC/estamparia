package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PainelListagemPedido extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PainelListagemPedido() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(34, 45, 45, 13);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(234, 42, 96, 19);
		add(textField);
		textField.setColumns(10);

	}
}
