package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.ProjetoDAO;
import model.Projeto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/ListarProjetos")
public class ListarProjetosServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            ProjetoDAO dao = new ProjetoDAO();
            List<Projeto> lista = dao.listarProjetos();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, (com.google.gson.JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new com.google.gson.JsonPrimitive(src.toString()))
                    .create();
            String json = gson.toJson(lista);

            out.print(json);
            out.flush();

        } catch (Exception e) {
            response.sendError(500, "Erro ao buscar projetos");
            e.printStackTrace();
        }
    }
}
