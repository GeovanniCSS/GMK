package teste;

import bo.CorridaCampeonatoBo;

public class CorridaCampeonatoTeste {

	public static void main(String[] args) {
		CorridaCampeonatoBo corridaCampeonatoBo=new CorridaCampeonatoBo();
		try {
			System.out.println(""+corridaCampeonatoBo.listar("", null, 0).size());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
