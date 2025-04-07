package servlet;

import dao.AnexoDAO;
import model.Anexo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/cadastrarAnexo")
public class CadastrarAnexoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeArquivo = request.getParameter("nome_arquivo");
        String caminhoArquivo = request.getParameter("caminho_arquivo");

        Anexo anexo = new Anexo();
        anexo.setNomeArquivo(nomeArquivo);
        anexo.setCaminho(caminhoArquivo);

        try {
            new AnexoDAO().inserir(anexo);
            response.sendRedirect("Anexos.html?sucesso=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Anexos.html?erro=true");
        }
    }
}
