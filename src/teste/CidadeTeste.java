package teste;

import bo.CidadeBo;
import dao.GenericDao;
import entity.Cidade;

public class CidadeTeste {

	public static void main(String[] args) {
		try {
			Cidade c=new Cidade(4,"A","A");
			CidadeBo cidadeBo=new CidadeBo();
			//cidadeBo.saveOrUpadte(new Cidade(4,"A","A"));
			GenericDao<Cidade> gDao = new GenericDao<Cidade>();
				gDao.remove(Cidade.class, c.getId());
			///cidadeBo.deletar(new Cidade(4,"g","g"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
