package servlet;

import dao.ProjetoDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/entregarProjeto")
public class EntregarProjetoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idProjeto = Integer.parseInt(request.getParameter("id"));

        try {
            ProjetoDAO dao = new ProjetoDAO();
            dao.deletarProjeto(idProjeto);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
