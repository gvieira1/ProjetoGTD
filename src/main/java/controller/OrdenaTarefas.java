package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import model.CategoriaContexto;
import model.Tarefa;
import repository.DBConnection;
import repository.LocalDateAdapter;
import repository.TarefaRepository;

@WebServlet("/ordena") 
public class OrdenaTarefas extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private TarefaRepository tarefaRepository;
    private String json;
    
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()) 
            .create();
    
    @Override
    public void init() throws ServletException {
    	DBConnection dbConnection = new DBConnection();
        tarefaRepository = new TarefaRepository(dbConnection);
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Tarefa> tarefas = tarefaRepository.findAll();  
        CategoriaContexto contexto = new CategoriaContexto();
        
        List<Tarefa> listaQuandoPuder = new ArrayList<>();
        List<Tarefa> listaAgendados = new ArrayList<>();
        List<Tarefa> listaProjetos = new ArrayList<>();
        List<Tarefa> listaAguardandoResposta = new ArrayList<>();
        List<Tarefa> listaArquivo = new ArrayList<>();
        
        for (Tarefa tarefa : tarefas) {
            String categoria = contexto.determinarCategoria(tarefa);
            
            switch (categoria) {
                case "QuandoPuder":
                	listaQuandoPuder.add(tarefa);
                    break;
                case "Agendados":
                	listaAgendados.add(tarefa);
                    break;
                case "Projetos":
                	listaProjetos.add(tarefa);
                    break;
                case "AguardandoResposta":
                	listaAguardandoResposta.add(tarefa);
                    break;
                case "Arquivo":
                    listaArquivo.add(tarefa);
                    break;
                default:
                    break;
            }
        }
        
        // Criando um objeto JsonObject para armazenar as listas
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.add("QuandoPuder", gson.toJsonTree(listaQuandoPuder));
        jsonResponse.add("Agendados", gson.toJsonTree(listaAgendados));
        jsonResponse.add("Projetos", gson.toJsonTree(listaProjetos));
        jsonResponse.add("AguardandoResposta", gson.toJsonTree(listaAguardandoResposta));
        jsonResponse.add("Arquivo", gson.toJsonTree(listaArquivo));

        // Convertendo o JsonObject para uma string JSON
        json = gson.toJson(jsonResponse);

        // Definindo o tipo de conteúdo da resposta como JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        // Escrevendo o JSON na resposta
        resp.getWriter().write(json);
    }
}