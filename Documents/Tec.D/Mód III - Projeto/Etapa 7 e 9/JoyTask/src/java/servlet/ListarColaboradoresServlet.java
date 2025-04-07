package servlet;

import com.google.gson.Gson;
import dao.ColaboradorDAO;
import model.Colaborador;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ListarColaboradores")
public class ListarColaboradoresServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            ColaboradorDAO dao = new ColaboradorDAO();
            List<Colaborador> colaboradores = dao.listarColaboradores();

            Gson gson = new Gson();
            out.print(gson.toJson(colaboradores));
            out.flush();
        } catch (SQLException e) {
            response.sendError(500, "Erro ao buscar colaboradores");
            e.printStackTrace();
        }
    }
}
