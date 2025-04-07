package dao;

import connection.ConnectionFactory;
import model.Anexo;
import java.sql.*;
import java.util.*;

public class AnexoDAO {

    public void inserir(Anexo anexo) throws SQLException {
        String sql = "INSERT INTO anexos (nome_arquivo, caminho_arquivo) VALUES (?, ?)";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, anexo.getNomeArquivo());
        stmt.setString(2, anexo.getCaminho());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public List<Anexo> listarAnexos() throws SQLException {
        List<Anexo> lista = new ArrayList<>();

        String sql = "SELECT * FROM anexos";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Anexo anexo = new Anexo();
            anexo.setId(rs.getInt("id_anexo"));
            anexo.setNomeArquivo(rs.getString("nome_arquivo"));
            anexo.setCaminho(rs.getString("caminho_arquivo")); // corrigido
            lista.add(anexo);
        }

        rs.close();
        stmt.close();
        conn.close();

        return lista;
    }
}
