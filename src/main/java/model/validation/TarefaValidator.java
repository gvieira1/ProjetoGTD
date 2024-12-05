package model.validation;

import java.util.List;

import model.entity.Tarefa;

public class TarefaValidator {

	public static boolean isTarefaValid(Tarefa tarefa) {
		return tarefa != null;
	}
	
	public static boolean isTarefaValid(List<Tarefa> tarefa) {
		return tarefa != null && !tarefa.isEmpty();
	}
	
	public static boolean isPathValid(String string) {
		return string != null && string.length() > 1;
	}
	
}
