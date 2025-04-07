package servlet;

import connection.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/excluirEquipe")
public class ExcluirEquipeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");

        try (Connection conn = ConnectionFactory.getConnection()) {
            // Buscar ID da equipe pelo nome
            String sqlBusca = "SELECT id_equipe FROM equipes WHERE nome = ?";
            PreparedStatement stmtBusca = conn.prepareStatement(sqlBusca);
            stmtBusca.setString(1, nome);
            ResultSet rs = stmtBusca.executeQuery();

            if (rs.next()) {
                int idEquipe = rs.getInt("id_equipe");

                // Excluir da tabela de relacionamento
                String sqlAssoc = "DELETE FROM equipe_colaborador WHERE id_equipe = ?";
                PreparedStatement stmtAssoc = conn.prepareStatement(sqlAssoc);
                stmtAssoc.setInt(1, idEquipe);
                stmtAssoc.executeUpdate();

                // Excluir da tabela equipes
                String sqlEquipe = "DELETE FROM equipes WHERE id_equipe = ?";
                PreparedStatement stmtEquipe = conn.prepareStatement(sqlEquipe);
                stmtEquipe.setInt(1, idEquipe);
                stmtEquipe.executeUpdate();

                response.setStatus(200);
            } else {
                response.sendError(404, "Equipe não encontrada");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Erro ao excluir equipe");
        }
    }
}
