package servlet;

import dao.TarefaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/excluirTarefa")
public class ExcluirTarefaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idTarefa = Integer.parseInt(request.getParameter("id"));

            TarefaDAO dao = new TarefaDAO();
            dao.excluir(idTarefa);

            response.setStatus(HttpServletResponse.SC_OK); 
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erro ao excluir tarefa.");
        }
    }
}
