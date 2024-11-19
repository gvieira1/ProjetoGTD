// Função para carregar as opções de prioridade e tempo estimado
function loadSelectOptions() {
    // Carrega as opções de prioridade
    $.get('http://localhost:8080/ProjetoGTD/prioridades', function(prioridades) {
        $('#prioridade').empty(); // Limpa as opções anteriores
        prioridades.forEach(function(prioridade) {
            $('#prioridade').append(
                `<option value="${prioridade.id}">${prioridade.nome}</option>`
            );
        });
    }).fail(function() {
        alert("Erro ao carregar prioridades.");
    });

    // Carrega as opções de tempo estimado
    $.get('http://localhost:8080/ProjetoGTD/temposEstimados', function(temposEstimados) {
        $('#tempo_estimado').empty(); // Limpa as opções anteriores
        temposEstimados.forEach(function(tempoEstimado) {
            $('#tempo_estimado').append(
                `<option value="${tempoEstimado.id}">${tempoEstimado.tempo}</option>`
            );
        });
    }).fail(function() {
        alert("Erro ao carregar tempo estimado.");
    });
}

// Função para carregar todas as tarefas
function loadTarefas() {
    $.get('http://localhost:8080/ProjetoGTD/tarefas', function(tarefas) {
        $('#tarefasList').empty(); // Limpa a lista antes de adicionar novos itens

        tarefas.forEach(function(tarefa) {
            $('#tarefasList').append(
                `<li class="list-group-item">
                   	 ${tarefa.descricao}<br>
                </li><br>`
            );
        });
    }).fail(function() {
        alert("Erro ao carregar tarefas.");
    });
}

// Função para criar uma nova tarefa
$('#tarefaForm').submit(function(event) {
    event.preventDefault(); 

    var tarefa = {
        descricao: $('#descricao').val(),
		prioridadeId: parseInt($('#prioridade').val(), 10),  // Converte para inteiro // ID da prioridade
        prazo: $('#prazo').val(),
        tempoEstimadoId: parseInt($('#tempo_estimado').val(), 10),   // ID do tempo estimado
        assunto: $('#assunto').val(),
        feito: $('#feito').prop('checked')
    };

	console.log(tarefa); 
	
    // Envia os dados para o back-end usando jQuery AJAX (POST)
    $.ajax({
        url: 'http://localhost:8080/ProjetoGTD/tarefas',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(tarefa),
        success: function(response) {
            alert('Tarefa criada com sucesso!');
            loadTarefas(); // Atualiza a lista de tarefas
			
			$('#tarefaForm')[0].reset(); // Reseta o formulário
        },
        error: function(error) {
            alert('Erro ao criar tarefa: ' + error.responseText);
        }
    });
});

// Função para carregar as tarefas e preencher as listas
function loadListas() {
    // requisição GET par a API
    $.get('http://localhost:8080/ProjetoGTD/ordena', function(data) {
        // Agora 'data' contém lista JSON retornado da API
        exibirLista('QuandoPuder', data.QuandoPuder);
        exibirLista('Agendados', data.Agendados);
        exibirLista('Projetos', data.Projetos);
        exibirLista('AguardandoResposta', data.AguardandoResposta);
        exibirLista('Arquivo', data.Arquivo);
    }).fail(function(error) {
        console.error('Erro ao carregar as tarefas:', error);
    });
}

// Função para exibir a lista no HTML
function exibirLista(categoria, lista) {
    var listaElement = $('#' + 'lista' + categoria); 
    listaElement.empty();  // Limpa a lista antes de adicionar novos itens

    if (lista && lista.length > 0) {
        $.each(lista, function(index, tarefa) {
            var liElement = $('<li class="list-group-item">').text(tarefa.descricao);
            listaElement.append(liElement);
        });
    } else {
        // Caso não haja tarefas, exibe uma mensagem
        listaElement.append($('<li class="list-group-item">').text('Nenhuma tarefa para esta categoria.'));
    }
}

// Função para carregar todas as tarefas no carrossel
function loadTarefasCarrossel() {
    $.get('http://localhost:8080/ProjetoGTD/tarefas', function(tarefas) {
        $('#tarefasCarouselItems').empty(); // Limpa o carrossel antes de adicionar novos itens

        tarefas.forEach(function(tarefa, index) {
            const activeClass = index === 0 ? 'active' : '';  // Define a primeira tarefa como ativa
            $('#tarefasCarouselItems').append(
                `<div class="carousel-item ${activeClass}">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Descrição: ${tarefa.descricao}</h5>
                            <p><strong>Prioridade:</strong> ${tarefa.prioridadeNome}</p>
                            <p><strong>Tempo Estimado:</strong> ${tarefa.tempoEstimadoNome}</p>
                            <p><strong>Prazo:</strong> ${tarefa.prazo}</p>
                            <p><strong>Assunto:</strong> ${tarefa.assunto}</p>
                            <p><strong>Feito:</strong> ${tarefa.feito ? 'Sim' : 'Não'}</p>
                        </div>
                    </div>
                </div>`
            );
        });
    }).fail(function() {
        alert("Erro ao carregar tarefas.");
    });
}

// Exibir o carrossel no modal
$('#showCarouselBtn').click(function() {
    loadTarefasCarrossel(); // Carrega as tarefas para o carrossel
    $('#tarefasModal').modal('show'); 
});


// Carrega as tarefas e as opções ao carregar a página
$(document).ready(function() {
    loadTarefas();         // Carrega as tarefas
    loadSelectOptions();    // Carrega as opções de prioridade e tempo estimado
	loadListas();
});
