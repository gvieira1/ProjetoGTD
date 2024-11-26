package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TempoEstimadoDAO;
import model.entity.TempoEstimado;
import utils.DBConnection;
import utils.JsonUtil;

@WebServlet("/temposEstimados") 
public class TempoEstimadoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TempoEstimadoDAO tempoEstimadoDAO;

    @Override
    public void init() throws ServletException {
        DBConnection dbConnection = (DBConnection) getServletContext().getAttribute("dbConnection");
        
        if (dbConnection == null) {
            throw new ServletException("A conexão com o banco de dados não foi inicializada.");
        }
        tempoEstimadoDAO = new TempoEstimadoDAO(dbConnection);
    }

    // Método GET para buscar os tempos estimados
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TempoEstimado> temposEstimados = tempoEstimadoDAO.findTempoEstimado(); 
        JsonUtil.sendJsonResponse(resp, temposEstimados);
    }
}

