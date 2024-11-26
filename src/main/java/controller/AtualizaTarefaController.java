package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TarefaDAO;
import model.entity.Tarefa;
import model.service.AutenticaService;
import model.service.TarefaService;
import model.validation.TarefaValidator;
import utils.DBConnection;
import utils.JsonUtil;

@WebServlet("/atualiza/*")
public class AtualizaTarefaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TarefaDAO tarefaDAO;
    private	 TarefaService tarefaService;

	@Override
	public void init() throws ServletException {
		DBConnection dbConnection = (DBConnection) getServletContext().getAttribute("dbConnection");

		if (dbConnection == null) {
			throw new ServletException("A conexão com o banco de dados não foi inicializada.");
		}
		tarefaDAO = new TarefaDAO(dbConnection);
		tarefaService = new TarefaService(tarefaDAO);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		if (TarefaValidator.isPathValid(req.getPathInfo())) {
			
			int tarefaId = Integer.parseInt(req.getPathInfo().substring(1));
			Tarefa tarefa = tarefaDAO.findTarefaById(tarefaId);
			if (TarefaValidator.isTarefaValid(tarefa)) {
				JsonUtil.sendJsonResponse(resp, tarefa);
			} else {
				JsonUtil.sendJsonResponseNotFound(resp, "error", "Tarefa não encontrada");
			}
		} else {
			JsonUtil.sendJsonResponseBadRequest(resp, "error", "ID da tarefa não especificado");
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		if (TarefaValidator.isPathValid(req.getPathInfo())) {
			
			Tarefa tarefa = JsonUtil.getGson().fromJson(req.getReader(), Tarefa.class);
			tarefa.setUsuarioId(AutenticaService.getAuthenticatedUserId(req));
			System.out.println(tarefa.getId());
			
			boolean updated = tarefaService.atualizarTarefa(tarefa);

			if (updated) {
				JsonUtil.sendJsonResponse(resp, "success", "Tarefa atualizada com sucesso.");
			} else {
				JsonUtil.sendJsonResponse(resp, "error", "Erro ao atualizar tarefa.");
			}
		} else {
			JsonUtil.sendJsonResponseBadRequest(resp, "error", "ID da tarefa não especificado");
		}

	}
}
