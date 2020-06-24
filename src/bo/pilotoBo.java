package bo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.GenericDao;
import dao.PilotoDao;
import entity.Piloto;

public class pilotoBo {

	public void saveOrUpadte(Piloto piloto) throws Exception {
		validarDadosPiloto(piloto);

		GenericDao<Piloto> gDao = new GenericDao<Piloto>();
		try {
			gDao.saveOrUpdate(piloto);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void deletar(Piloto piloto) throws Exception {
		validarDadosPiloto(piloto);

		GenericDao<Piloto> gDao = new GenericDao<Piloto>();
		try {
			gDao.remove(Piloto.class, piloto.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public List<Piloto> listar(String tipo,Piloto piloto, int id) throws Exception {
		try {
			if (tipo.equals("")) {
				GenericDao<Piloto> pDao = new GenericDao<Piloto>();
				return pDao.list(Piloto.class);
			} else {
				PilotoDao pilotoDao=new PilotoDao();
				return pilotoDao.listar(tipo, piloto, id);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	public Piloto login(String tipo, Piloto piloto) throws Exception {
		if (listar(tipo, piloto, 0).size() == 1) {
			return listar(tipo, piloto, 0).get(0);
		} else {
			throw new Exception("Login incorreto");
		}
	}

	public Piloto cadastro(String tipo, Piloto piloto) throws Exception {
		validarDadosPiloto(piloto);
		if (listar(tipo, piloto, 1).size() == 0) {
			piloto.setId(0);
			saveOrUpadte(piloto);
			return piloto;
		} else {
			throw new Exception("Login já utilizado");
		}
	}

	private void validarDadosPiloto(Piloto piloto) throws Exception {
		if (piloto.getId() < 0) {
			throw new Exception("Id do piloto não pode ser negativo!");
		} else if (piloto.getNome().equals("")) {
			throw new Exception("Nome do piloto não pode ficar em branco!");
		} else if (piloto.getSenha().equals("")) {
			throw new Exception("Senha não pode ficar em branco!");
		} else if (piloto.getEmail().equals("")) {
			throw new Exception("Email não pode ficar em branco!");
		}
	}
	public Piloto proximaTela(String nome, String cpf, String email, String celular, String dataNascimento)
			throws Exception {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Piloto piloto = new Piloto();
		if (nome.equals("")) {
			throw new Exception("Nome do piloto não pode ficar em branco!");
		} else if (email.equals("")) {
			throw new Exception("Email não pode ficar em branco!");
		} else if (cpf.equals("")) {
			throw new Exception("CPF não pode ficar em branco!");
		} else if (dataNascimento.equals("")) {
			throw new Exception("Data de nascimento não pode ficar em branco!");
		} else if (celular.equals("")) {
			throw new Exception("celular não pode ficar em branco!");
		} else {
			try {
				piloto.setDataNascimento(LocalDate.parse(dataNascimento.replaceAll("/", "-"), formatador));
			} catch (Exception e) {
				throw new Exception("Data de Nascimento está no formato errado");
			}
			piloto.setNome(nome);
			piloto.setEmail(email);
			piloto.setCpf(Integer.parseInt(cpf));
			piloto.setCelular(Integer.parseInt(celular));
		}
		return piloto;
	}

}
