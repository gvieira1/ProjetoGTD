package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Prioridade;
import repository.DBConnection;
import repository.PrioridadeRepository;

@WebServlet("/prioridades") 
public class PrioridadeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PrioridadeRepository prioridadeRepository;

    @Override
    public void init() throws ServletException {
        DBConnection dbConnection = new DBConnection();
        prioridadeRepository = new PrioridadeRepository(dbConnection);
    }

    // Método GET para buscar as prioridades
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Prioridade> prioridades = prioridadeRepository.findAll(); 
        String json = new Gson().toJson(prioridades); 
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json); 
    }
}
