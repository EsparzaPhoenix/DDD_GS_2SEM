package br.com.fiap.globalsolution.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.globalsolution.dao.MedicoDao;
import br.com.fiap.globalsolution.exception.BadInfoException;
import br.com.fiap.globalsolution.exception.IdNotFoundException;
import br.com.fiap.globalsolution.factory.ConnectionFactory;
import br.com.fiap.globalsolution.model.Medico;

public class MedicoService {

    private MedicoDao medicoDao;

    public MedicoService() throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.getConnection();
		medicoDao = new MedicoDao(conexao);
	}

    public List<Medico> listarMedicos() throws SQLException {
        return medicoDao.listar();
    }

    public void cadastrarMedico(Medico medico) throws SQLException {
        medicoDao.cadastrar(medico);
    }
    
    private void validarMedico(Medico medico) throws BadInfoException {
    	
        if (medico.getNome() == null || medico.getNome().length() > 80) {
        	throw new BadInfoException("Nome invalido, nome nao pode ser nulo");
        }
        if (medico.getEspecialidade() == null || medico.getEspecialidade().length() > 100) {
        	throw new BadInfoException("Email invalido, email nao pode ser nulo");
        }
        if (medico.getCrm() == 0 || String.valueOf(medico.getCrm()).length() > 10) {
        	throw new BadInfoException("Crm invalido, Crm nao pode ser maior que 10 digitos");
        }
    }
    

    public Medico pesquisarMedico(int id) throws SQLException, IdNotFoundException {
        return medicoDao.pesquisar(id);
    }

    public void removerMedico(int id) throws SQLException, IdNotFoundException {
        medicoDao.remover(id);
    }
    
    public void atualizarMedico(Medico medico) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
    	validarMedico(medico);
    	medicoDao.atualizar(medico);
    }
    
}
