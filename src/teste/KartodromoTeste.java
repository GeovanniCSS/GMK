package teste;

import bo.KartodromoBo;
import entity.Cidade;
import entity.Kartodromo;

public class KartodromoTeste {

	public static void main(String[] args) {
		try {
			//KartodromoBo.saveOrUpadte(new Kartodromo(5,"Pinhais","134","rua",2,"",123,565,Novo kartódromo
			//"url",true,new Cidade(1,"",""),1,10));
			
			KartodromoBo.horarios().forEach(System.out::println);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
