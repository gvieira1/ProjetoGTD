

import { formatarCategoriaParaId } from '../helpers.js';

export function loadCategorias() {
    $.get('/ProjetoGTD/ordena', function(data, textStatus, xhr) {
        if (xhr.status === 200) {
            $.each(data, function(categoria, tarefas) {
                exibirLista(categoria, tarefas);
            });
        } else {
            console.error('Erro ao carregar dados ou dados em formato inesperado:', data);
        }
    }).fail(function(error) {
        console.error('Erro ao carregar os dados:', error);
    });
}

function exibirLista(categoria, lista) {
    var categoriaFormatada = formatarCategoriaParaId(categoria);
    var listaElement = $('#' + categoriaFormatada);
    listaElement.empty();

    if (lista && lista.length > 0) {
        $.each(lista, function(index, tarefa) {
            var liElement = $('<li class="list-group-item">').text(tarefa.descricao);
            listaElement.append(liElement);
        });
    } else {
        listaElement.append($('<li class="list-group-item">').text('Nenhuma tarefa para esta categoria.'));
    }
}

// Função para definir a categoria dinamicamnte no front end
export function definirCategoria() {
	let categoria = "Arquivo";
	const prioridade = $('#formSwitch1').prop('checked');
	const delegado = $('#formSwitch2').prop('checked');
	const prazo = $('#prazomodal').val();

	if (delegado) {
		categoria = "Aguardando Resposta";
	} else if (prioridade) {
		categoria = "Quando Puder";
	} else if (prazo) {
		categoria = "Agendado";
	} 

	atualizarCategoriaSelect(categoria);
}

// Função para atualizar o select de categoria
function atualizarCategoriaSelect(categoria) {
	const $categoriaSelect = $('#categoriamodal');
	const $option = $categoriaSelect.find('option').filter(function() {
		return $(this).text() === categoria;
	});

	if ($option.length > 0) {
		$categoriaSelect.prop('selectedIndex', $option.index());
	}
}

