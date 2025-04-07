package servlet;

import com.google.gson.Gson;
import dao.AnexoDAO;
import model.Anexo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ListarAnexos")
public class ListarAnexosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            AnexoDAO dao = new AnexoDAO();
            List<Anexo> lista = dao.listarAnexos();

            Gson gson = new Gson();
            out.print(gson.toJson(lista));
            out.flush();
        } catch (SQLException e) {
            response.sendError(500, "Erro ao buscar anexos");
            e.printStackTrace();
        }
    }
}
