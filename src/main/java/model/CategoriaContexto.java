package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class CategoriaContexto {
    private Map<String, Predicate<Tarefa>> categorias;

    public CategoriaContexto() {
        categorias = new HashMap<>();
        categorias.put("QuandoPuder", CategoriaFunctions.categoriaQuandoPuder());
        categorias.put("Agendados", CategoriaFunctions.categoriaAgendados());
        categorias.put("Projetos", CategoriaFunctions.categoriaProjetos());
        categorias.put("AguardandoResposta", CategoriaFunctions.categoriaAguardandoResposta());
        categorias.put("Arquivo", CategoriaFunctions.categoriaArquivo());
    }

    public String determinarCategoria(Tarefa tarefa) {
        for (Map.Entry<String, Predicate<Tarefa>> entry : categorias.entrySet()) {
            if (entry.getValue().test(tarefa)) {
                return entry.getKey();  // Retorna a categoria se a condição for satisfeita
            }
        }
        return "Não Categoriza";  // Caso não se encaixe em nenhuma
    }
}
