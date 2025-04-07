package dao;

import connection.ConnectionFactory;
import model.Equipe;
import java.sql.*;
import java.util.*;

public class EquipeDAO {

    public List<Equipe> listarEquipesComColaboradores() throws SQLException {
        List<Equipe> lista = new ArrayList<>();

        String sql = "SELECT e.nome AS equipe, c.nome AS colaborador, c.email, c.nivel "
                + "FROM equipes e "
                + "JOIN equipe_colaborador ec ON e.id_equipe = ec.id_equipe "
                + "JOIN colaboradores c ON ec.id_colaborador = c.id_colaborador";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Equipe equipe = new Equipe();
            equipe.setNome(rs.getString("equipe"));
            equipe.setColaboradorNome(rs.getString("colaborador"));
            equipe.setColaboradorEmail(rs.getString("email"));
            equipe.setColaboradorNivel(rs.getString("nivel"));
            lista.add(equipe);
        }

        rs.close();
        stmt.close();
        conn.close();

        return lista;
    }



    public void inserir(Equipe equipe) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();

        // Inserir na tabela equipes
        String sql = "INSERT INTO equipes (nome) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, equipe.getNome());
        stmt.executeUpdate();

        // Pegar o ID da equipe gerado
        ResultSet rs = stmt.getGeneratedKeys();
        int idEquipe = 0;
        if (rs.next()) {
            idEquipe = rs.getInt(1);
        }

        // Inserir os colaboradores selecionados
        if (equipe.getColaboradoresIds() != null) {
            for (int idColab : equipe.getColaboradoresIds()) {
                String sqlAssoc = "INSERT INTO equipe_colaborador (id_equipe, id_colaborador) VALUES (?, ?)";
                PreparedStatement stmtAssoc = conn.prepareStatement(sqlAssoc);
                stmtAssoc.setInt(1, idEquipe);
                stmtAssoc.setInt(2, idColab);
                stmtAssoc.executeUpdate();
                stmtAssoc.close();
            }
        }

        rs.close();
        stmt.close();
        conn.close();
    }
    
    
    public List<Equipe> listarEquipes() throws SQLException {
        List<Equipe> lista = new ArrayList<>();

        String sql = "SELECT * FROM equipes";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Equipe equipe = new Equipe();
            equipe.setId(rs.getInt("id_equipe"));
            equipe.setNome(rs.getString("nome"));
            lista.add(equipe);
        }

        rs.close();
        stmt.close();
        conn.close();

        return lista;
    }
}
