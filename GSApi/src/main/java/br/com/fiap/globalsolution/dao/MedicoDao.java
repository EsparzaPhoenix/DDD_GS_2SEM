package br.com.fiap.globalsolution.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.globalsolution.model.Medico;
import br.com.fiap.globalsolution.exception.IdNotFoundException;

public class MedicoDao {

    private Connection conn;

    public MedicoDao(Connection conn) {
        this.conn = conn;
    }

    public List<Medico> listar() throws SQLException {
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM t_medico order by id_medico");
        ResultSet result = stm.executeQuery();
        List<Medico> lista = new ArrayList<>();
        while (result.next()) {
            Medico medico = parseMedico(result);
            lista.add(medico);
        }
        return lista;
    }

    private Medico parseMedico(ResultSet result) throws SQLException {
        int id = result.getInt("id_medico");
        int idHospital = result.getInt("id_hospital");
        String nome = result.getString("nm_medico");
        Long crm = result.getLong("nr_crm");
        String especialidade = result.getString("nm_especialidade");

        return new Medico(id, idHospital, nome, crm, especialidade);
    }

    public void cadastrar(Medico medico) throws SQLException {
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO t_medico (id_medico, id_hospital, nm_medico, nr_crm, nm_especialidade) VALUES (?, ?, ?, ?, ?)");
        stm.setInt(1, medico.getId());
        stm.setInt(2, medico.getIdHospital());
        stm.setString(3, medico.getNome());
        stm.setLong(4, medico.getCrm());
        stm.setString(5, medico.getEspecialidade());
        stm.executeUpdate();
    }

    public Medico pesquisar(int id) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM t_medico WHERE id_medico = ?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();
        if (!result.next()) {
            
            return null;
        }
        return parseMedico(result);
    }

    public void remover(int id) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conn.prepareStatement("DELETE FROM t_medico WHERE id_medico = ?");
        stm.setInt(1, id);
        stm.executeUpdate();
    }
    
    public void atualizar(Medico medico) throws ClassNotFoundException, SQLException, IdNotFoundException {

		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("update t_medico set id_hospital = ?, nm_medico = ?, nr_crm = ?, nm_especialidade = ? where id_medico = ?");
		stm.setInt(1, medico.getIdHospital());
        stm.setString(2, medico.getNome());
        stm.setLong(3, medico.getCrm());
        stm.setString(4, medico.getEspecialidade());
        stm.setInt(5, medico.getId());

        int linha = stm.executeUpdate();
        if (linha == 0) {
            throw new IdNotFoundException("Médico não cadastrado, impossível atualizar");
        }
    }
}
