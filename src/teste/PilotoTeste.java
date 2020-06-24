package teste;

import java.time.LocalDate;

import bo.pilotoBo;
import dao.GenericDao;
import dao.PilotoDao;
import entity.Piloto;

public class PilotoTeste {

	public static void main(String[] args) {
		
		//LocalDate d=LocalDate.now();
		Piloto p=new Piloto(0,123,"Geovanni","G","Geovanni@GMAIL","134",LocalDate.now(),123);
		Piloto p2=new Piloto();
		pilotoBo pilotoBo=new pilotoBo();
		try {
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
