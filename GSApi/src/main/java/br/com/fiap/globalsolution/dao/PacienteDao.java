package br.com.fiap.globalsolution.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.globalsolution.model.Paciente;
import br.com.fiap.globalsolution.exception.IdNotFoundException;


public class PacienteDao {

    private Connection conn;

    public PacienteDao(Connection conn) {
        this.conn = conn;
    }

    public List<Paciente> listar() throws SQLException {
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM t_paciente order by id_paciente");
        ResultSet result = stm.executeQuery();
        List<Paciente> lista = new ArrayList<>();
        while (result.next()) {
            Paciente paciente = parsePaciente(result);
            lista.add(paciente);
        }
        return lista;
    }
    
    private Paciente parsePaciente(ResultSet result) throws SQLException {
        int id = result.getInt("id_paciente");
        String nome = result.getString("nm_paciente");
        String email = result.getString("ds_email");
        Long telefone = 0L;
        if (result.getString("nr_telefone") != null)
        	telefone = result.getLong("nr_telefone");
        int convenio = result.getInt("nr_convenio");
        String sexo = result.getString("ds_genero");
        String faixaEtaria = result.getString("ds_faixa_etaria");
        Date dataNascimento = result.getDate("dt_nascimento");

        return new Paciente(id, nome, email, telefone, convenio, sexo, faixaEtaria, dataNascimento);
    }

    public void cadastrar(Paciente paciente) throws SQLException {
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO t_paciente (id_paciente, nm_paciente, ds_email, nr_telefone, nr_convenio, ds_genero, ds_faixa_etaria, dt_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        stm.setInt(1, paciente.getId());
        stm.setString(2, paciente.getNome());
        stm.setString(3, paciente.getEmail());
        stm.setDouble(4, paciente.getTelefone());
        stm.setInt(5, paciente.getConvenio());
        stm.setString(6, paciente.getSexo());
        stm.setString(7, paciente.getFaixaEtaria());
        stm.setDate(8, new java.sql.Date(paciente.getDataNascimento().getTime()));

        stm.executeUpdate();
    }

    public Paciente pesquisar(int id) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM t_paciente WHERE id_paciente = ?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();
        if (!result.next()) {
            
            return null;
        }
        return parsePaciente(result);
    }

    public void remover(int id) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conn.prepareStatement("DELETE FROM t_paciente WHERE id_paciente = ?");
        stm.setInt(1, id);
        stm.executeUpdate();
    }
    
    public void atualizar(Paciente paciente) throws ClassNotFoundException, SQLException, IdNotFoundException {

        PreparedStatement stm = conn.prepareStatement("update t_paciente set "
                + "nm_paciente = ?, ds_email = ?, nr_telefone = ?, nr_convenio = ?,"
                + "ds_genero = ?, ds_faixa_etaria = ?, dt_nascimento = ? where id_paciente = ?");

        stm.setString(1, paciente.getNome());
        stm.setString(2, paciente.getEmail());
        stm.setDouble(3, paciente.getTelefone());
        stm.setInt(4, paciente.getConvenio());
        stm.setString(5, paciente.getSexo());
        stm.setString(6, paciente.getFaixaEtaria());
        stm.setDate(7, new java.sql.Date(paciente.getDataNascimento().getTime()));  // Convertendo para SQL Date
        stm.setInt(8, paciente.getId());
        
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new IdNotFoundException("Paciente não cadastrado, impossível atualizar");
    }
}
