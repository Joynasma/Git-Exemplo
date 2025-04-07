package dao;

import connection.ConnectionFactory;
import model.Colaborador;
import java.sql.*;
import java.util.*;

public class ColaboradorDAO {

    // INSERIR COLABORADOR
    public void inserir(Colaborador colaborador) throws SQLException {
        String sql = "INSERT INTO colaboradores (nome, email, cargo, nivel, senha) VALUES (?, ?, ?, ?, ?)";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, colaborador.getNome());
        stmt.setString(2, colaborador.getEmail());
        stmt.setString(3, colaborador.getCargo());
        stmt.setString(4, colaborador.getNivel());
        stmt.setString(5, colaborador.getSenha());

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    // LISTAR COLABORADORES
    public List<Colaborador> listarColaboradores() throws SQLException {
        List<Colaborador> lista = new ArrayList<>();

        String sql = "SELECT * FROM colaboradores";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Colaborador c = new Colaborador();
            c.setId(rs.getInt("id_colaborador"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setCargo(rs.getString("cargo"));
            c.setNivel(rs.getString("nivel"));
            c.setSenha(rs.getString("senha")); 
            lista.add(c);
        }

        rs.close();
        stmt.close();
        conn.close();

        return lista;
    }
}
