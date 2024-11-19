package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.TempoEstimado;
import repository.DBConnection;
import repository.TempoEstimadoRepository;

@WebServlet("/temposEstimados") // Mapeia o endpoint /temposEstimados
public class TempoEstimadoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TempoEstimadoRepository tempoEstimadoRepository;

    @Override
    public void init() throws ServletException {
        DBConnection dbConnection = new DBConnection();
        tempoEstimadoRepository = new TempoEstimadoRepository(dbConnection);
    }

    // Método GET para buscar os tempos estimados
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TempoEstimado> temposEstimados = tempoEstimadoRepository.findAll(); // Busca todos os tempos estimados
        String json = new Gson().toJson(temposEstimados); // Converte para JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json); // Retorna como resposta
    }
}

