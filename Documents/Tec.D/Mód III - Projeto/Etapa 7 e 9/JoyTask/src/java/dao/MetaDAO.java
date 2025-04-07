package dao;

import connection.ConnectionFactory;
import model.Meta;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class MetaDAO {

    // INSERIR META
    public void inserir(Meta meta) throws SQLException {
        String sql = "INSERT INTO metas (titulo, descricao, data_entrega) VALUES (?, ?, ?)";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, meta.getTitulo());
        stmt.setString(2, meta.getDescricao());
        stmt.setDate(3, java.sql.Date.valueOf(meta.getDataEntrega()));

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
// LISTAR METAS

    public List<Meta> listarMetas() throws SQLException {
        List<Meta> lista = new ArrayList<>();

        String sql = "SELECT * FROM metas";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Meta meta = new Meta();
            meta.setId(rs.getInt("id_meta"));
            meta.setTitulo(rs.getString("titulo"));
            meta.setDescricao(rs.getString("descricao"));
            meta.setDataEntrega(rs.getDate("data_entrega").toLocalDate());

            lista.add(meta);
        }

        rs.close();
        stmt.close();
        conn.close();

        return lista;
    }
    // EXCLUIR META
    
    public void excluir(int id) throws SQLException {
    String sql = "DELETE FROM metas WHERE id_meta = ?";
    Connection conn = ConnectionFactory.getConnection();
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, id);
    stmt.executeUpdate();

    stmt.close();
    conn.close();
}


}
