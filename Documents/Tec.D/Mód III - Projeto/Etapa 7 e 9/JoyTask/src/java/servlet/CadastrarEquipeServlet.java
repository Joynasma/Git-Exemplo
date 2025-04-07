package servlet;

import com.google.gson.Gson;
import dao.EquipeDAO;
import model.Equipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/cadastrarEquipe")
public class CadastrarEquipeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String colaboradoresJson = request.getParameter("colaboradores");

        Gson gson = new Gson();
        int[] colaboradoresIds = gson.fromJson(colaboradoresJson, int[].class);

        Equipe equipe = new Equipe();
        equipe.setNome(nome);
        equipe.setColaboradoresIds(Arrays.stream(colaboradoresIds).boxed().toList());

        try {
            new EquipeDAO().inserir(equipe);
            response.sendRedirect("CadastrarEquipe.html?sucesso=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("CadastrarEquipe.html?erro=true");
        }
    }
}
