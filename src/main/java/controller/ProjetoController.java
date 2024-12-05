package controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ProjetoDAO;
import model.entity.Projeto;
import model.service.AutenticaService;
import utils.DBConnection;
import utils.JsonUtil;

@WebServlet("/projeto")
public class ProjetoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjetoDAO projetoDAO;

    @Override
    public void init() throws ServletException {
        DBConnection dbConnection = (DBConnection) getServletContext().getAttribute("dbConnection");

        if (dbConnection == null) {
            throw new ServletException("A conexão com o banco de dados não foi inicializada.");
        }
        projetoDAO = new ProjetoDAO(dbConnection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
        Projeto projeto = JsonUtil.getGson().fromJson(req.getReader(), Projeto.class);
        if (!AutenticaService.isAuthenticated(req)) {
            JsonUtil.sendJsonResponseUnauthorized(resp, "error", "Usuário não autenticado.");
            return;
        } else {
        	projeto.setId_usuario(AutenticaService.getAuthenticatedUserId(req));
        	projetoDAO.insertProjeto(req, projeto);
            JsonUtil.sendJsonResponseCreated(resp, "success", "Projeto Criado!"); 
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 
        if (!AutenticaService.isAuthenticated(req)) {
            JsonUtil.sendJsonResponseUnauthorized(resp, "error", "Usuário não autenticado.");
            return;
        } else {
        	Integer usuarioId = AutenticaService.getAuthenticatedUserId(req);
        	List<Projeto> projetos = projetoDAO.findProjetoByUsuarioId(usuarioId);
        	JsonUtil.sendJsonResponse(resp, projetos);
        }
        
    }

	
}
