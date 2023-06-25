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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;

public class PainelAtualizarUsuario extends JPanel {
	private JTextField textNome;
	private JTextField textCPF;
	private JTextField textEmail;
	private JLabel lblAtualizarCadastro;
	private JLabel lblNome;
	private JButton btnSalvar;
	private Pessoa pessoa;
	private JButton btnVoltar;
	private MaskFormatter mascaraCpf;
	private JPasswordField textSenha;
	private JRadioButton rdnFuncionario;
	private JRadioButton rdnCliente;

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
		lblAtualizarCadastro.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAtualizarCadastro.setBounds(518, 11, 246, 78);
		add(lblAtualizarCadastro);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(453, 138, 70, 15);
		add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(518, 131, 232, 29);
		add(textNome);
		textNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(463, 190, 70, 15);
		add(lblCpf);

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			// silent
		}
		textCPF = new JFormattedTextField(mascaraCpf);
		textCPF.setColumns(10);
		textCPF.setBounds(518, 185, 232, 29);
		add(textCPF);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(453, 245, 70, 15);
		add(lblEmail);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(518, 238, 232, 29);
		add(textEmail);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(453, 299, 70, 15);
		add(lblSenha);

		textSenha = new JPasswordField();
		textSenha.setColumns(10);
		textSenha.setBounds(518, 292, 232, 29);
		add(textSenha);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipo.setBounds(463, 343, 70, 15);
		add(lblTipo);

		rdnFuncionario = new JRadioButton("Funcionário");
		rdnFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdnFuncionario.setBounds(516, 339, 109, 23);
		add(rdnFuncionario);

		rdnCliente = new JRadioButton("Cliente");
		rdnCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdnCliente.setBounds(641, 339, 109, 23);
		add(rdnCliente);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdnCliente);
		grupo.add(rdnFuncionario);
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				pessoa.setNome(textNome.getText());
				pessoa.setEmail(textEmail.getText());
				pessoa.setSenha(textSenha.getText());
				if (rdnCliente.isSelected()) {
					pessoa.setFuncionario(false);
				}
				if (rdnFuncionario.isSelected()) {
					pessoa.setFuncionario(true);
				}

				try {
					String cpfSemMascara = (String) mascaraCpf.stringToValue(textCPF.getText());
					pessoa.setCpf(cpfSemMascara);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao converter o CPF", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				PessoaController controller = new PessoaController();
				try {

					controller.atualizar(pessoa);
					JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception excecao) {
					JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(441, 441, 172, 43);
		add(btnSalvar);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(670, 441, 177, 43);
		add(btnVoltar);

		if (pessoaParaEditar != null) {
			this.pessoa = pessoaParaEditar;
			pessoaParaEditar.isFuncionario();
			textEmail.setText(pessoaParaEditar.getEmail());
			textCPF.setText(pessoaParaEditar.getCpf());
			textNome.setText(pessoaParaEditar.getNome());
			textSenha.setText(pessoaParaEditar.getSenha());
			rdnFuncionario.setSelected(pessoaParaEditar.isFuncionario() == true);
			rdnCliente.setSelected(pessoaParaEditar.isFuncionario() == false);
		}
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

}
