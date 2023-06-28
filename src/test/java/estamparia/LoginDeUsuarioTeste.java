package estamparia;


import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import model.vo.Pessoa;


class LoginDeUsuarioTeste {
	
	private static Pessoa cliente;

	@BeforeAll

	public static void setUpBeforeClass() throws Exception {
		cliente = new Pessoa(10, "Jurema da Silva", "11122233344", "jureminha123@gmail.com", "jureminhalinda", false);

	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}
	
	
	@Test
	public void testeLogin() throws Exception {
		Assertions.assertEquals("jureminha123@gmail.com", cliente.getEmail());
		Assertions.assertEquals("jureminhalinda", cliente.getSenha());	
	}
	@Test
	public void testeLoginFalha1() throws Exception {
		Assertions.assertEquals("jureminha321@gmail.com",cliente.getEmail());
		Assertions.assertEquals("jureminhalinda", cliente.getSenha());	
	}
	@Test
	public void testeLoginFalha2() throws Exception {
		Assertions.assertEquals("jureminha123@gmail.com ", cliente.getEmail());
		Assertions.assertEquals("jureminha", cliente.getSenha(), "SENHA INVÁLIDA");	
	}
	
}

