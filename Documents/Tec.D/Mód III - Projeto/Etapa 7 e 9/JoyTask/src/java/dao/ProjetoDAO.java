package dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.*;
import model.Projeto;
import java.time.LocalDate;



public class ProjetoDAO {

    // LISTAR PROJETOS
    public List<Projeto> listarProjetos() throws SQLException {
        List<Projeto> lista = new ArrayList<>();

        String sql = "SELECT p.id_projeto, p.nome, p.descricao, p.data_entrega, " +
                     "e.nome AS nome_equipe " +
                     "FROM projetos p " +
                     "LEFT JOIN equipes e ON p.id_equipe = e.id_equipe";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Projeto projeto = new Projeto();
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setNome(rs.getString("nome"));
            projeto.setDescricao(rs.getString("descricao"));
            projeto.setDataEntrega(rs.getDate("data_entrega").toLocalDate());
            projeto.setNomeEquipe(rs.getString("nome_equipe")); // pode vir null

            lista.add(projeto);
        }

        rs.close();
        stmt.close();
        conn.close();

        return lista;
    }

    // INSERIR PROJETO
    public void inserirProjeto(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO projetos (nome, descricao, data_entrega, id_equipe) " +
                     "VALUES (?, ?, ?, ?)";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, projeto.getNome());
        stmt.setString(2, projeto.getDescricao());
        stmt.setDate(3, java.sql.Date.valueOf(projeto.getDataEntrega()));

      
        // Verifica se tem equipe definida
        if (projeto.getIdEquipe() != 0) {
            stmt.setInt(4, projeto.getIdEquipe());
        } else {
            stmt.setNull(4, Types.INTEGER);
        }

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    // EXCLUIR PROJETO
    public void excluirProjeto(int idProjeto) throws SQLException {
        String sql = "DELETE FROM projetos WHERE id_projeto = ?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idProjeto);

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
    
    // ENTREGAR PROJETO
    
    public void deletarProjeto(int id) throws SQLException {
    String sql = "DELETE FROM projetos WHERE id_projeto = ?";

    Connection conn = ConnectionFactory.getConnection();
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, id);
    stmt.executeUpdate();

    stmt.close();
    conn.close();
}

    
}
