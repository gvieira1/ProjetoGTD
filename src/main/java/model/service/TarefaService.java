package model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.TarefaDAO;
import model.entity.Tarefa;
import model.validation.TarefaValidator;

public class TarefaService {

	private TarefaDAO tarefaDAO;

	public TarefaService(TarefaDAO tarefaDAO) {
		this.tarefaDAO = tarefaDAO;
	}

	public Map<String, List<Tarefa>> obterTarefaPorCategoria(List<Tarefa> tarefas) {

		Map<String, List<Tarefa>> categoriasMap = new HashMap<>();

		for (Tarefa tarefa : tarefas) {
			categoriasMap.computeIfAbsent(tarefa.getCategoriaNome(), k -> new ArrayList<>()).add(tarefa);
		}
		return categoriasMap;
	}

	public void definirCategoria(Tarefa tarefa) {
		if (tarefa.getDelegado()) {
			tarefa.setCategoriaId(4);
		} else if (tarefa.getPrioridade()) {
			tarefa.setCategoriaId(1);
		} else if (tarefa.getPrazo() != null) {
			tarefa.setCategoriaId(2);
		} else {
			tarefa.setCategoriaId(5);
		}
	}

	public boolean atualizarTarefa(Tarefa tarefa) {
		if (!TarefaValidator.isTarefaValid(tarefa)) {
			return false;
		}
		definirCategoria(tarefa);
		return tarefaDAO.updateTarefa(tarefa);
	}

}
