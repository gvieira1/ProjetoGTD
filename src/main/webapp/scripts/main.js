

import { loadSelectOptions } from './api/temposEstimados.js';
import { loadTarefas } from './api/tarefas.js';
import { loadCategorias } from './api/categorias.js';
import { initializeSwitches } from './modal.js';

// Inicialização do aplicativo
$(document).ready(function() {
    loadSelectOptions();
    loadTarefas();
    loadCategorias();
    initializeSwitches();
});
