

import { createTarefa, updateTarefa } from './api/tarefas.js';

$('#tarefaFormInicio').submit(function(event) {
    event.preventDefault();

    var tarefa = {
        descricao: $('#descricaoadd').val(),
    };

    createTarefa(tarefa);
});

$('#tarefaForm').submit(function(e) {
    e.preventDefault();

    const tarefaId = $('#taskId').data('id');
    const tarefaAtualizada = {
        id: tarefaId,
        descricao: $('#descricaomodal').val(),
        prioridade: $('#formSwitch1').prop('checked'),
        prazo: $('#prazomodal').val(),
        tempoEstimadoId: $('#tempo_estimado').val(),
        assunto: $('#assuntomodal').val(),
        delegado: $('#formSwitch2').prop('checked'),
        categoriaId: $('#categoriamodal').val()
    };

    updateTarefa(tarefaId, tarefaAtualizada);
});
