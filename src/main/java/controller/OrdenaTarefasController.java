package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TarefaDAO;
import model.entity.Tarefa;
import model.service.AutenticaService;
import model.service.TarefaService;
import utils.DBConnection;
import utils.JsonUtil;

@WebServlet("/ordena") 
public class OrdenaTarefasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private TarefaDAO tarefaDAO;
    private TarefaService tarefaService;
    
    @Override
    public void init() throws ServletException {
        DBConnection dbConnection = (DBConnection) getServletContext().getAttribute("dbConnection");
        
        if (dbConnection == null) {
            throw new ServletException("A conexão com o banco de dados não foi inicializada.");
        }
        tarefaDAO = new TarefaDAO(dbConnection);
        tarefaService = new TarefaService(tarefaDAO);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	 if (!AutenticaService.isAuthenticated(req)) {
             JsonUtil.sendJsonResponseUnauthorized(resp, "error", "Usuário não autenticado.");
             return;
         } else {
         	Integer usuarioId = AutenticaService.getAuthenticatedUserId(req);
         	List<Tarefa> todasAsTarefas = tarefaDAO.findTarefaECategoria(usuarioId);
         	Map<String, List<Tarefa>> categoria = tarefaService.obterTarefaPorCategoria(todasAsTarefas);
         	JsonUtil.sendJsonResponse(resp, categoria); 
         }	
    }
    
}