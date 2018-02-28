package persistence.dao;

import java.util.List;

import model.Composta;

public interface CompostaDao {
	public void save(Composta l);
	public void deleteComposta(Composta l);
	public List<Composta> getAllComposta();
	public Composta findByPrimaryKey(Composta l);
}
