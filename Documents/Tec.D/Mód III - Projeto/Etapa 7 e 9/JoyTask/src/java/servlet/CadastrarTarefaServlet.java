package servlet;

import dao.TarefaDAO;
import model.Tarefa;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/cadastrarTarefa")
public class CadastrarTarefaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String dataInicio = request.getParameter("data_inicio");
        String dataFim = request.getParameter("data_fim");

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setDataInicio(dataInicio);
        tarefa.setDataFim(dataFim);

        try {
            new TarefaDAO().inserir(tarefa);
            response.sendRedirect("AdicionarTarefa.html?sucesso=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AdicionarTarefa.html?erro=true");
        }
    }
}
