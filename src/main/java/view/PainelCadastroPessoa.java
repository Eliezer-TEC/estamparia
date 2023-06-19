package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import model.exception.CampoInvalidoException;
import model.exception.CpfJaUtilizadoException;
import model.exception.EmailJaUtilizadoException;
import model.vo.Pessoa;
import controller.PessoaController;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JPasswordField;

public class PainelCadastroPessoa extends JPanel {
	private Pessoa pessoa;
	private JTextField txtNome;
	private JLabel lblTitulo;
	private JLabel lblNome;
	private JLabel lblCpf;
	private MaskFormatter mascaraCpf;
	private JFormattedTextField txtCPF;
	private JButton btnSalvar;
	private JButton btnVoltar;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblSenha;
	private JPasswordField passwordField;

	public PainelCadastroPessoa() {
		

		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(47dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(82dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(52dlu;default)"),},
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
				FormSpecs.DEFAULT_ROWSPEC,}));

		lblTitulo = new JLabel(pessoa.getId() == null ? "NOVO CLIENTE" : "EDIÇÃO DE CLIENTE");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblTitulo, "4, 2, 7, 1, center, default");

		lblNome = new JLabel("Nome");
		add(lblNome, "4, 4, right, default");

		txtNome = new JTextField();
		add(txtNome, "8, 4, 3, 1, fill, default");
		txtNome.setColumns(10);

		lblCpf = new JLabel("CPF");
		add(lblCpf, "4, 6, right, default");

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			// silent
		}

		txtCPF = new JFormattedTextField(mascaraCpf);
		add(txtCPF, "8, 6, 3, 1, fill, default");

		lblEmail = new JLabel("Email");
		add(lblEmail, "4, 8, right, default");

		txtEmail = new JTextField();
		add(txtEmail, "8, 8, 3, 1, fill, default");
		txtEmail.setColumns(10);

		lblSenha = new JLabel("Senha");
		add(lblSenha, "4, 10, right, default");
		
		passwordField = new JPasswordField();
		add(passwordField, "8, 10, 3, 1, fill, default");
		

		btnSalvar = new JButton("Salvar");
		add(btnSalvar, "8, 14");
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				pessoa.setNome(txtNome.getText());
				pessoa.setFuncionario(false);
				pessoa.setEmail(txtEmail.getText());
				pessoa.setSenha(passwordField.getText());

				try {
					String cpfSemMascara = (String) mascaraCpf.stringToValue(txtCPF.getText());
					pessoa.setCpf(cpfSemMascara);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao converter o CPF", "Erro", JOptionPane.ERROR_MESSAGE);
				}

				PessoaController controller = new PessoaController();
				try {
					controller.inserir(pessoa);
					JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (CpfJaUtilizadoException | EmailJaUtilizadoException | CampoInvalidoException  excecao) {
					JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
				
						btnVoltar = new JButton("Voltar");
						add(btnVoltar, "10, 14");

		if (this.pessoa.getId() != null) {
			preencherCamposDaTela();
		}
	}

	private void preencherCamposDaTela() {
		this.txtCPF.setText(this.pessoa.getCpf());
		this.txtNome.setText(this.pessoa.getNome());
		this.txtEmail.setText(this.pessoa.getEmail());
		this.passwordField.setText(this.pessoa.getSenha());
	}

	// Usado para tornar o btnVoltar acessível externamente (por exemplo, pelo
	// MenuTelefonia)
	public JButton getBtnVoltar() {
		return btnVoltar;
	}
}
