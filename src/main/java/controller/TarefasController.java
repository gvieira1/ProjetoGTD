package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TarefaDAO;
import model.entity.Tarefa;
import model.service.AutenticaService;
import utils.DBConnection;
import utils.JsonUtil;
@WebServlet("/tarefas")
public class TarefasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TarefaDAO tarefaDAO;

    @Override
    public void init() throws ServletException {
        DBConnection dbConnection = (DBConnection) getServletContext().getAttribute("dbConnection");

        if (dbConnection == null) {
            throw new ServletException("A conexão com o banco de dados não foi inicializada.");
        }
        tarefaDAO = new TarefaDAO(dbConnection);
    }

    // Método POST (Criação)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	req.setCharacterEncoding("UTF-8");
        Tarefa tarefa = JsonUtil.getGson().fromJson(req.getReader(), Tarefa.class);
        if (!AutenticaService.isAuthenticated(req)) {
            JsonUtil.sendJsonResponseUnauthorized(resp, "error", "Usuário não autenticado.");
            return;
        } else {
        	tarefa.setUsuarioId(AutenticaService.getAuthenticatedUserId(req));
        	tarefaDAO.insertTarefaDescricao(req, tarefa);
            JsonUtil.sendJsonResponseCreated(resp, "success", "Tarefa Criada"); //status 201
        }
        
    }

 // Método GET (Leitura)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 
        if (!AutenticaService.isAuthenticated(req)) {
            JsonUtil.sendJsonResponseUnauthorized(resp, "error", "Usuário não autenticado.");
            return;
        } else {
        	Integer usuarioId = AutenticaService.getAuthenticatedUserId(req);
        	List<Tarefa> tarefas = tarefaDAO.findTarefaByUsuarioId(usuarioId);
        	JsonUtil.sendJsonResponse(resp, tarefas);
        }
        
    }

	
}
