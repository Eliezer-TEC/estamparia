package estamparia;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.vo.Pessoa;


class AtualizarUsuarioTeste {

private static Pessoa cliente;
	
	@BeforeAll

	static void setUpBeforeClass() throws Exception {
		cliente = new Pessoa(14, "Jurema da Silva", "11122233344", "jureminha123@gmail.com", "jureminhalinda", false);
	}
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testeAtualizarTipo() {
		cliente.setFuncionario(true);
		Assertions.assertEquals(true, cliente.isFuncionario(), "Usuário atualizado com sucesso");
	}
	
	@Test
	void testeAtualizarTipoErro() {
		cliente.setFuncionario(false);
		Assertions.assertEquals(true, cliente.isFuncionario(), "Usuário não foi atualizado");
	}

}
