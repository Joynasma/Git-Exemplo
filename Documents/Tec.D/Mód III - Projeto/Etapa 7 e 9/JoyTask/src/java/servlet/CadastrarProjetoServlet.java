package servlet;

import dao.ProjetoDAO;
import model.Projeto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/cadastrarProjeto")
public class CadastrarProjetoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        LocalDate dataEntrega = LocalDate.parse(request.getParameter("data_entrega"));
        int idEquipe = Integer.parseInt(request.getParameter("id_equipe"));

        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        projeto.setDescricao(descricao);
        projeto.setDataEntrega(dataEntrega);
        projeto.setIdEquipe(idEquipe);

        try {
            ProjetoDAO dao = new ProjetoDAO();
            dao.inserirProjeto(projeto);
            response.sendRedirect("CadastroProjeto.html?sucesso=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("CadastroProjeto.html?erro=true");
        }
    }

}
