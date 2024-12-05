

import { formatarCategoriaParaId } from '../helpers.js';
import { openEditModalClassificado } from './tarefas.js';

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
            var liElement = $(`
                <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                    ${tarefa.descricao}
					<input type="checkbox" class="form-check-input me-2 " data-task-id="${tarefa.id}"  data-bs-toggle="modal" data-bs-target="#feitoModal">
                </li>
            `)
            .on('click', function(event) {
                if (!$(event.target).is('input[type="checkbox"]')) {
                    openEditModalClassificado(tarefa.id);
                }
            });
            
            listaElement.append(liElement);
        });
    } else {
        listaElement.append($('<li class="list-group-item">').text('Nenhuma tarefa para esta categoria.'));
    }

	    $('input[type="checkbox"]').change(function() {
	        const tarefaId = $(this).data('task-id');
	        const isChecked = $(this).prop('checked');
			const descricao = $(this).closest('li').contents().first().text().trim(); 

			$('#feitoModal').data('tarefa-desc', descricao); 
	        $('#feitoModal').data('tarefa-id', tarefaId); 
	        $('#feitoModal').data('is-checked', isChecked);  
	    });
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

