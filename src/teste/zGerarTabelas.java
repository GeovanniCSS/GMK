package teste;

import java.time.LocalDate;

import bo.CidadeBo;
import bo.KartodromoBo;
import bo.pilotoBo;
import entity.Cidade;
import entity.Kartodromo;
import entity.Piloto;

public class zGerarTabelas {

	public static void main(String[] args) {
		KartodromoBo kartodromoBo = new KartodromoBo();
		pilotoBo pilotoBo = new pilotoBo();
		CidadeBo cidadeBo = new CidadeBo();
		try {
			// (int id, String nome,String senha, String eRua, int eNumero, String
			// eComplemento, int telefone, int whatsapp,
			// String urlSite, boolean situacao, Cidade cidade,int numMinimo,int numMax
			cidadeBo.saveOrUpadte(new Cidade(0, "Pinhais", "pr"));
			cidadeBo.saveOrUpadte(new Cidade(0, "Curitiba", "pr"));
			cidadeBo.saveOrUpadte(new Cidade(0, "Araucária", "pr"));
			kartodromoBo.saveOrUpadte(new Kartodromo(0, "Pinhais", "1234", "Av. Mal. Floriano Peixoto", 3031, "Parolin",
					36408006, 985566461, "pinhais_Kart@gmail.com", true, new Cidade(1, "", ""), 2, 20));
			kartodromoBo.saveOrUpadte(new Kartodromo(0, "Kart Indoor", "1234", "Av. Pres. Kennedy", 757, "Rebouças",
					37408090, 98556137, "Kart_indor@gmail.com", true, new Cidade(2, "", ""), 5, 15));
			kartodromoBo.saveOrUpadte(new Kartodromo(0, "Raceland", "1234", "Av. Água Verde", 860, "Água Verde",
					36877545, 98556699, "Racerace@gmail.com", true, new Cidade(1, "", ""), 1, 15));
			pilotoBo.saveOrUpadte(new Piloto(0, 12, "Geovanni Corsino", "GeovanniACSS", "email", "1234", LocalDate.now(), 123));
			pilotoBo.saveOrUpadte(new Piloto(0, 12, "João Pedro", "Joãozinho", "email", "1234", LocalDate.now(), 123));
			pilotoBo.saveOrUpadte(new Piloto(0, 12, "Leonardo da Silva", "LeoSilva", "email", "1234", LocalDate.now(), 123));
			pilotoBo.saveOrUpadte(new Piloto(0, 12, "Anderson da Silva", "Junior", "email", "1234", LocalDate.now(), 123));
			pilotoBo.saveOrUpadte(new Piloto(0, 12, "Matheus de Souza", "MatheusSouza", "email", "1234", LocalDate.now(), 123));
			pilotoBo.saveOrUpadte(new Piloto(0, 12, "Bruno Indiana", "BrunoIndi", "email", "1234", LocalDate.now(), 123));
			pilotoBo.saveOrUpadte(new Piloto(0, 12, "Allan José", "Mr.piloto", "email", "1234", LocalDate.now(), 123));
			pilotoBo.saveOrUpadte(new Piloto(0, 12, "José de Oliveira", "JoséACSS", "email", "1234", LocalDate.now(), 123));
			pilotoBo.saveOrUpadte(new Piloto(0, 12, "Lucas da Silva", "Luquinha", "email", "1234", LocalDate.now(), 123));
			pilotoBo.saveOrUpadte(new Piloto(0, 12, "Marcos cordeiro", "Marcão", "email", "1234", LocalDate.now(), 123));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
