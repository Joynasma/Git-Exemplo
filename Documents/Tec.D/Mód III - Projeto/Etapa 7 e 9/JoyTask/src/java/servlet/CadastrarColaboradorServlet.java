package servlet;

import dao.ColaboradorDAO;
import model.Colaborador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/cadastrarColaborador")
public class CadastrarColaboradorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cargo = request.getParameter("cargo");
        String nivel = request.getParameter("nivel");
        String senha = request.getParameter("senha");

        Colaborador colaborador = new Colaborador();
        colaborador.setNome(nome);
        colaborador.setEmail(email);
        colaborador.setCargo(cargo);
        colaborador.setNivel(nivel);
        colaborador.setSenha(senha);

        try {
            new ColaboradorDAO().inserir(colaborador);
            response.sendRedirect("CadastrarColaborador.html?sucesso=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("CadastrarColaborador.html?erro=true");
        }
    }
}
