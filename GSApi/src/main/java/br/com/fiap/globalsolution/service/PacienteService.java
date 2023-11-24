package br.com.fiap.globalsolution.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.globalsolution.dao.PacienteDao;
import br.com.fiap.globalsolution.exception.BadInfoException;
import br.com.fiap.globalsolution.exception.IdNotFoundException;
import br.com.fiap.globalsolution.factory.ConnectionFactory;
import br.com.fiap.globalsolution.model.Paciente;


public class PacienteService {

    private PacienteDao pacienteDao;

    public PacienteService() throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.getConnection();
		pacienteDao = new PacienteDao(conexao);
	}


    public List<Paciente> listarPacientes() throws SQLException {
        return pacienteDao.listar();
    }

    public void cadastrarPaciente(Paciente paciente) throws SQLException {
        pacienteDao.cadastrar(paciente);
    }

    private void validarPaciente(Paciente paciente) throws BadInfoException {
    	
        if (paciente.getNome() == null || paciente.getNome().length() > 80) {
        	throw new BadInfoException("Nome invalido, nome nao pode ser nulo");
        }
        if (paciente.getEmail() == null || paciente.getEmail().length() > 100) {
        	throw new BadInfoException("Email invalido, email nao pode ser nulo");
        }
        if (paciente.getTelefone() == 0 || String.valueOf(paciente.getTelefone()).length() < 9) {
        	throw new BadInfoException("Telefone invalido, telefone nao pode ser menor que 9 digitos");
        }
        if (paciente.getSexo() == null || paciente.getSexo().length() > 1) {
        	throw new BadInfoException("Sexo invalido, sexo nao pode ser nulo");
        }
        if (paciente.getFaixaEtaria() == null || paciente.getFaixaEtaria().length() > 30) {
        	throw new BadInfoException("idade invalida, idade nao pode ser maior que 3 digitos");
        }
    }

    public Paciente pesquisarPaciente(int id) throws SQLException, IdNotFoundException {
        return pacienteDao.pesquisar(id);
    }

    public void removerPaciente(int id) throws SQLException, IdNotFoundException {
        pacienteDao.remover(id);
    }
    
    public void atualizarPaciente(Paciente paciente) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
    	validarPaciente(paciente);
    	pacienteDao.atualizar(paciente);
    }
}