package estamparia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import model.vo.Pessoa;

class CadastroDeUsuarioTest {

	@Test
	public void testCadastroDeUsuario(){
		 
		 Pessoa novaPessoa = new Pessoa(1, "Jurema da Silva","11122233344", "jureminha123@gmail.com", "jureminhalinda", false);
	 
			assertNotNull(novaPessoa.getId());
			assertEquals("Jurema da Silva", novaPessoa.getNome());
			assertEquals("11122233344", novaPessoa.getCpf());
			assertEquals("jureminha123@gmail.com", novaPessoa.getEmail());
			assertEquals("jureminhalinda", novaPessoa.getSenha());
			assertFalse(novaPessoa.isFuncionario());
	    }
	
	@Test
	public void testCadatroDeUsuarioFalha() {
		
		Pessoa novaPessoa = new Pessoa(1, "Aldo Santos","11122233344", "aldo@gmail.com", "132Aldo", false);
   
		assertNotNull(novaPessoa.getId());
		assertEquals("Aldo Santos", novaPessoa.getNome());
		assertEquals("11122233344", novaPessoa.getCpf());
		assertEquals("aldo@gmail.com ", novaPessoa.getEmail());
		assertEquals("132Aldo", novaPessoa.getSenha());
		assertFalse(novaPessoa.isFuncionario());
		
	}
}

