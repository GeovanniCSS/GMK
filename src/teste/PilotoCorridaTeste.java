package teste;

import bo.PilotoCorridaBo;
import dao.GenericDao;
import entity.PilotoCorrida;

public class PilotoCorridaTeste {

	public static void main(String[] args) {
		PilotoCorridaBo pilotoCorridaBo=new PilotoCorridaBo();
		GenericDao<PilotoCorrida> gDao=new GenericDao<PilotoCorrida>();
		try {
			//System.out.println(gDao.findById(PilotoCorrida.class, 40));
			gDao.remove(PilotoCorrida.class, 38);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
