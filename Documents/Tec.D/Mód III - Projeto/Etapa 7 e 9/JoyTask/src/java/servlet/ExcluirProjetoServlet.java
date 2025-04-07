package servlet;

import dao.ProjetoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/excluirProjeto")
public class ExcluirProjetoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idProjeto = Integer.parseInt(request.getParameter("id"));
            ProjetoDAO dao = new ProjetoDAO();
            dao.excluirProjeto(idProjeto);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir projeto.");
        }
    }
}
