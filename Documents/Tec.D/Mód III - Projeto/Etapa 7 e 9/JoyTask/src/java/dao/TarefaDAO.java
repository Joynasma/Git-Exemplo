package dao;

import java.sql.*;
import java.util.*;
import model.Tarefa;
import connection.ConnectionFactory;

public class TarefaDAO {

    // INSERIR TAREFA
    public void inserir(Tarefa tarefa) throws SQLException {
        String sql = "INSERT INTO tarefas (titulo, descricao, data_inicio, data_fim) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getDataInicio());
            stmt.setString(4, tarefa.getDataFim());
            stmt.executeUpdate();
        }
    }

    // LISTAR TAREFAS 
    public List<Tarefa> listar() throws SQLException {
        List<Tarefa> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tarefa t = new Tarefa();
                t.setId(rs.getInt("id_tarefa"));
                t.setTitulo(rs.getString("titulo"));
                t.setDescricao(rs.getString("descricao"));
                t.setDataInicio(rs.getString("data_inicio"));
                t.setDataFim(rs.getString("data_fim"));
                lista.add(t);
            }
        }
        return lista;
    }

    // LISTAR TAREFAS 
    public List<Tarefa> listarTarefas() throws SQLException {
        List<Tarefa> lista = new ArrayList<>();

        String sql = "SELECT * FROM tarefas";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setId(rs.getInt("id_tarefa"));
            tarefa.setTitulo(rs.getString("titulo")); // CORRIGIDO
            tarefa.setDescricao(rs.getString("descricao"));
            tarefa.setDataInicio(rs.getString("data_inicio"));
            tarefa.setDataFim(rs.getString("data_fim"));
            lista.add(tarefa);
        }

        rs.close();
        stmt.close();
        conn.close();

        return lista;
    }

    // EXLUIR TAREFAS
    public void excluir(int idTarefa) throws SQLException {
        String sql = "DELETE FROM tarefas WHERE id_tarefa = ?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idTarefa);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

}
