package teste;

import java.util.Map;

import bo.CampeonatoPontuacaoBo;
import entity.Campeonato;
import entity.CampeonatoPontuacao;

public class CampeonatoPontuacaoTeste {
	public static void main(String[] args) {
		try {
			CampeonatoPontuacaoBo campeonatoPontuacaoBo=new CampeonatoPontuacaoBo();
			campeonatoPontuacaoBo.salvarPontuacao(new CampeonatoPontuacao(0,10,10,new Campeonato(1)));
			
			//CampeonatoPontuacaoBo.listar("repetido", new CampeonatoPontuacao(0,3,7,new Campeonato(1)), 0).forEach(System.out::println);;
			//teste da logica para pegar a pontuacao por posicao
			//campeonatoPontuacaoBo.listar("todosCampeonato", null, 1).forEach(System.out::println);
			//Map <Integer,Integer>lista=campeonatoPontuacaoBo.pontosDoCampeonato(new Campeonato(1));
			//for(int i=1;i<=lista.size();i++) {
			//System.out.println("posicao:"+i+",pontuacao:"+lista.get(i)); 
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
