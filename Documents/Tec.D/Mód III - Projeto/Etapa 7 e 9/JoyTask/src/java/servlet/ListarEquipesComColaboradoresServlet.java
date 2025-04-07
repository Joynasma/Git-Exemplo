/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import com.google.gson.Gson;
import dao.EquipeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Equipe;


@WebServlet("/ListarEquipesComColaboradores")
public class ListarEquipesComColaboradoresServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            EquipeDAO dao = new EquipeDAO();
            List<Equipe> equipes = dao.listarEquipesComColaboradores();

            Gson gson = new Gson();
            out.print(gson.toJson(equipes));
            out.flush();
        } catch (SQLException e) {
            response.sendError(500, "Erro ao buscar equipes com colaboradores");
            e.printStackTrace();
        }
    }
}
