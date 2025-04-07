package servlet;

import com.google.gson.Gson;
import dao.EquipeDAO;
import model.Equipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ListarEquipes")
public class ListarEquipesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            List<Equipe> equipes = new EquipeDAO().listarEquipes();
            String json = new Gson().toJson(equipes);
            out.print(json);
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().print("{\"erro\":\"Erro ao buscar equipes\"}");
            e.printStackTrace();
        }
    }
}
