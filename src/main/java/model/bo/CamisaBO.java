package model.bo;

import model.dao.CamisaDAO;
import model.exception.EstampaJaUtilizadoException;
import model.vo.Camisa;

public class CamisaBO {
	private CamisaDAO dao = new CamisaDAO();

	public Camisa inserir(Camisa novaCamisa) throws EstampaJaUtilizadoException {
		if (dao.estampaJaUtilizada(novaCamisa.getEstampa())) {
			throw new EstampaJaUtilizadoException("Estampa informado já foi utilizado");
		}

//Caso Estampa não utilizado -> salvar e devolver o cliente
		return dao.inserir(novaCamisa);
	}
}
