package servlet;

import dao.MetaDAO;
import model.Meta;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/cadastrarMeta")
public class CadastrarMetaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        LocalDate dataEntrega = LocalDate.parse(request.getParameter("data_entrega"));

        Meta meta = new Meta();
        meta.setTitulo(titulo);
        meta.setDescricao(descricao);
        meta.setDataEntrega(dataEntrega);

        try {
            new MetaDAO().inserir(meta);
            response.sendRedirect("AdicionarMeta.html?sucesso=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AdicionarMeta.html?erro=true");
        }
    }
}
