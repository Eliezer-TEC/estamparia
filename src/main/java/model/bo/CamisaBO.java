package model.bo;

import model.dao.CamisaDAO;
import model.exception.EstampaJaUtilizadoException;
import model.vo.Camisa;
import model.vo.SituacaoPedido;

public class CamisaBO {
	private CamisaDAO dao = new CamisaDAO();

	public Camisa inserir(Camisa camisa) throws EstampaJaUtilizadoException {
		
	
//Caso Estampa não utilizado -> salvar e devolver o cliente
		return dao.inserir(camisa);
	}
}
