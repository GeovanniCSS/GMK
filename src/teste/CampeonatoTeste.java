package teste;

import java.time.LocalDate;

import bo.CampeonatoBo;
import entity.Campeonato;
import entity.Piloto;

public class CampeonatoTeste {

	public static void main(String[] args) {
		LocalDate d=LocalDate.now();
		CampeonatoBo bo=new CampeonatoBo();
		String g="";
		Campeonato campeonato=new Campeonato(0, "A", LocalDate.now(), 5, true,
				Integer.parseInt(g), 5, 5, new Piloto());
		try {
		bo.validarDadosCampeonato(campeonato);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
