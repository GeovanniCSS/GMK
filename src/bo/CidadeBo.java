package bo;

import java.util.List;

import dao.GenericDao;
import entity.Cidade;

public class CidadeBo {

	public void saveOrUpadte(Cidade cidadeo) throws Exception {
		validarDadosCidade(cidadeo);

		GenericDao<Cidade> gDao = new GenericDao<Cidade>();
		try {
			gDao.saveOrUpdate(cidadeo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void deletar(Cidade cidade) throws Exception {
		validarDadosCidade(cidade);

		GenericDao<Cidade> gDao = new GenericDao<Cidade>();
		try {
			gDao.remove(Cidade.class, cidade.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public  List<Cidade> listar() throws Exception {
		try {
			GenericDao<Cidade> kDao = new GenericDao<Cidade>();
			return kDao.list(Cidade.class);
		} catch (Exception e) {
			throw new Exception("Nenhuma cidade selecionado");
		}
	}	
	

	private void validarDadosCidade(Cidade cidadeo) throws Exception {
		if (cidadeo.getId() < 0) {
			throw new Exception("Id do cidadeo não pode ser negativo!");
		}
		if (cidadeo.getNome().equals("")) {
			throw new Exception("Nome do cidadeo não pode ficar em branco!");
		}
	}
}
