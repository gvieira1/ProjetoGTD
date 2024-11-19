package model;

import java.util.function.Predicate;

public class CategoriaFunctions {

    public  static Predicate<Tarefa> categoriaQuandoPuder() {
        return tarefa -> tarefa.getPrioridadeId() != 3 && tarefa.getPrazo() == null;
    }

    public  static Predicate<Tarefa> categoriaAgendados() {
        return tarefa -> tarefa.getPrazo() != null;
    }

    public static Predicate<Tarefa> categoriaProjetos() {
        return tarefa -> tarefa.getTempoEstimadoId() == 4;
    }

    public static Predicate<Tarefa> categoriaAguardandoResposta() {
        return tarefa -> tarefa.getDelegado();
    }

    public static Predicate<Tarefa> categoriaArquivo() {
        return tarefa -> tarefa.getPrioridadeId() == 3 && tarefa.getPrazo() == null;
    }
    
}
