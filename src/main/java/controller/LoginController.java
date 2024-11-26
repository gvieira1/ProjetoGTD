package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.AutenticaDAO;
import model.entity.Usuario;
import utils.DBConnection;
import utils.JsonUtil;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AutenticaDAO autenticaDAO;

    @Override
    public void init() throws ServletException {
        DBConnection dbConnection = (DBConnection) getServletContext().getAttribute("dbConnection");
        if (dbConnection == null) {
            throw new ServletException("A conexão com o banco de dados não foi inicializada.");
        }
        autenticaDAO = new AutenticaDAO(dbConnection);  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = autenticaDAO.validarLogin(email, senha);

        
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario_id", usuario.getId());
            session.setAttribute("usuario_nome", usuario.getNome());
            
            JsonUtil.sendJsonResponse(response, "success", "login ok");
        } else {
            JsonUtil.sendJsonResponse(response, "error", "Email ou senha inválidos.");
        }

    }
}
