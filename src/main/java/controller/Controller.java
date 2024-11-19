package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Tarefa;
import repository.DBConnection;
import repository.LocalDateAdapter;
import repository.TarefaRepository;

@WebServlet("/tarefas")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TarefaRepository tarefaRepository;

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()) // Registra o TypeAdapter para LocalDate
            .create();
    
    @Override
    public void init() throws ServletException {
        DBConnection dbConnection = new DBConnection();
        tarefaRepository = new TarefaRepository(dbConnection);
    }

    // Método POST (Criação)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lê o corpo da requisição JSON
        Tarefa tarefa = gson.fromJson(req.getReader(), Tarefa.class);

        // Chama o DAO para inserir a tarefa no banco de dados
        tarefaRepository.insertParcial(tarefa);
        
        // Responde com um status 201 (Created)
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write("Tarefa criada com sucesso.");
    }

    // Método GET (Leitura)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Busca todas as tarefas
        List<Tarefa> tarefas = tarefaRepository.findAll();
        
        // Converte as tarefas para JSON
        String json = gson.toJson(tarefas);
        //System.out.println("JSON enviado: " + json); 
        
        // Configura o tipo de resposta para JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    // Método PUT (Atualização)
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lê o corpo da requisição JSON
        Tarefa tarefa = gson.fromJson(req.getReader(), Tarefa.class);
        
        // Chama o DAO para atualizar a tarefa
        tarefaRepository.update(tarefa);
        
        // Responde com status 200 (OK)
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("Tarefa atualizada com sucesso.");
    }

    // Método DELETE (Exclusão)
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lê o ID da tarefa que deve ser excluída
        int id = Integer.parseInt(req.getParameter("id"));

        // Chama o DAO para excluir a tarefa
        tarefaRepository.delete(id);

        // Responde com status 200 (OK)
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("Tarefa excluída com sucesso.");
    }
}
