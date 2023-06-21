package controller;

import model.bo.CamisaBO;
import model.exception.EstampaJaUtilizadoException;
import model.vo.Camisa;

public class CamisaController {

	
	private	CamisaBO bo = new CamisaBO();
	
	
	public Camisa inserir(Camisa camisa) throws EstampaJaUtilizadoException {
		return bo.inserir(camisa);
	}

}
