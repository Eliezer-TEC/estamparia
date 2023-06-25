package view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.text.MaskFormatter;

import controller.PessoaController;
import model.exception.CampoInvalidoException;
import model.exception.CpfJaUtilizadoException;
import model.exception.EmailJaUtilizadoException;
import model.vo.Pessoa;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;

public class PainelAtualizarUsuario extends JPanel {
	private JTextField textNome;
	private JTextField textCPF;
	private JTextField textEmail;
	private JLabel lblAtualizarCadastro;
	private JLabel lblNome;
	private JComboBox comboBoxTipo;
	private JButton btnSalvar;
	private Pessoa pessoa;
	private JButton btnVoltar;
	private MaskFormatter mascaraCpf;
	private JPasswordField textSenha;

	/**
	 * Create the panel.
	 * 
	 * @param pessoa
	 */
	public PainelAtualizarUsuario(Pessoa pessoaParaEditar) {
		if (pessoaParaEditar != null) {
			this.pessoa = pessoaParaEditar;
		} else {
			this.pessoa = new Pessoa();
		}
		setLayout(null);

		lblAtualizarCadastro = new JLabel("Atualizar cadastro");
		lblAtualizarCadastro.setBounds(139, 12, 172, 15);
		add(lblAtualizarCadastro);

		lblNome = new JLabel("Nome: " + pessoaParaEditar.getNome());
		lblNome.setBounds(59, 41, 70, 15);
		add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(119, 39, 208, 19);
		add(textNome);
		textNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(72, 80, 70, 15);
		add(lblCpf);

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			// silent
		}
		textCPF = new JFormattedTextField(mascaraCpf);
		textCPF.setColumns(10);
		textCPF.setBounds(119, 78, 208, 19);
		add(textCPF);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(59, 116, 70, 15);
		add(lblEmail);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(119, 114, 208, 19);
		add(textEmail);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(59, 157, 70, 15);
		add(lblSenha);

		textSenha = new JPasswordField();
		textSenha.setColumns(10);
		textSenha.setBounds(119, 155, 208, 19);
		add(textSenha);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(72, 196, 70, 15);
		add(lblTipo);

		comboBoxTipo = new JComboBox(new String[] { "Funcionário", "Cliente" });
		comboBoxTipo.setBounds(119, 191, 208, 24);
		add(comboBoxTipo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				boolean tipo = false;
				if (comboBoxTipo.getSelectedIndex() == 0) {
					tipo = true;
				} else {
					tipo = false;
				}

				pessoa.setNome(textNome.getText());
				pessoa.setFuncionario(tipo);
				pessoa.setEmail(textEmail.getText());
				pessoa.setSenha(textSenha.getText());

				try {
					String cpfSemMascara = (String) mascaraCpf.stringToValue(textCPF.getText());
					pessoa.setCpf(cpfSemMascara);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao converter o CPF", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				PessoaController controller = new PessoaController();
				try {

					controller.atualizar(pessoa);
					JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception excecao) {
					JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(83, 248, 117, 25);
		add(btnSalvar);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(222, 248, 117, 25);
		add(btnVoltar);

	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

}
