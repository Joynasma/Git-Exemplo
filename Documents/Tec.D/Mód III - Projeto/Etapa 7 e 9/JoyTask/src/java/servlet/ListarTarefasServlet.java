package servlet;

import com.google.gson.Gson;
import dao.TarefaDAO;
import model.Tarefa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ListarTarefas")
public class ListarTarefasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            TarefaDAO dao = new TarefaDAO();
            List<Tarefa> lista = dao.listarTarefas();

            Gson gson = new Gson();
            String json = gson.toJson(lista);

            out.print(json);
            out.flush();
        } catch (Exception e) {
            response.sendError(500, "Erro ao buscar tarefas");
            e.printStackTrace();
        }
    }
}
