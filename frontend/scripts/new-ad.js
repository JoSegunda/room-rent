// Script para funcionalidades da página de adicionar anúncio

document.addEventListener('DOMContentLoaded', function() {
    // Contador de caracteres para descrição
    const descricaoTextarea = document.getElementById('descricao');
    const charCounter = document.getElementById('char-counter');
    
    if (descricaoTextarea && charCounter) {
        descricaoTextarea.addEventListener('input', function() {
            charCounter.textContent = this.value.length;
        });
    }

    // Upload de fotos
    const uploadArea = document.getElementById('upload-area');
    const fileInput = document.getElementById('fotos');
    const previewContainer = document.getElementById('preview-container');
    const btnSelecionarFotos = document.getElementById('btn-selecionar-fotos');
    
    if (uploadArea && fileInput && btnSelecionarFotos) {
        // Abrir seletor de arquivos ao clicar no botão
        btnSelecionarFotos.addEventListener('click', function() {
            fileInput.click();
        });
        
        // Abrir seletor de arquivos ao clicar na área de upload
        uploadArea.addEventListener('click', function() {
            fileInput.click();
        });
        
        // Efeito drag and drop
        uploadArea.addEventListener('dragover', function(e) {
            e.preventDefault();
            uploadArea.style.borderColor = '#2D6A4F';
            uploadArea.style.background = '#E9ECEF';
        });
        
        uploadArea.addEventListener('dragleave', function() {
            uploadArea.style.borderColor = '';
            uploadArea.style.background = '';
        });
        
        uploadArea.addEventListener('drop', function(e) {
            e.preventDefault();
            uploadArea.style.borderColor = '';
            uploadArea.style.background = '';
            
            if (e.dataTransfer.files.length) {
                handleFiles(e.dataTransfer.files);
            }
        });
        
        // Seleção de arquivos
        fileInput.addEventListener('change', function() {
            if (this.files.length) {
                handleFiles(this.files);
            }
        });
    }

    function handleFiles(files) {
        const maxFiles = 10;
        const allowedTypes = ['image/jpeg', 'image/png', 'image/jpg'];
        const maxSize = 5 * 1024 * 1024; // 5MB
        
        // Limitar número de arquivos
        const filesToProcess = Array.from(files).slice(0, maxFiles);
        
        filesToProcess.forEach(file => {
            // Verificar tipo do arquivo
            if (!allowedTypes.includes(file.type)) {
                alert('Tipo de arquivo não permitido. Use apenas JPG ou PNG.');
                return;
            }
            
            // Verificar tamanho do arquivo
            if (file.size > maxSize) {
                alert('Arquivo muito grande. O tamanho máximo é 5MB.');
                return;
            }
            
            // Criar preview
            const reader = new FileReader();
            
            reader.onload = function(e) {
                const previewItem = document.createElement('div');
                previewItem.className = 'preview-item';
                
                previewItem.innerHTML = `
                    <img src="${e.target.result}" alt="Preview">
                    <button type="button" class="remove-btn" title="Remover foto">
                        <i class="fas fa-times"></i>
                    </button>
                `;
                
                previewContainer.appendChild(previewItem);
                
                // Adicionar evento para remover foto
                const removeBtn = previewItem.querySelector('.remove-btn');
                removeBtn.addEventListener('click', function() {
                    previewItem.remove();
                    updateFileInput();
                });
            };
            
            reader.readAsDataURL(file);
        });
        
        updateFileInput();
    }

    function updateFileInput() {
        // Aqui você pode implementar a lógica para atualizar o input de arquivos
        // se necessário (por exemplo, usando FormData)
    }

    // Validação do formulário
    const form = document.getElementById('form-anuncio');
    if (form) {
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Validação básica
            const requiredFields = form.querySelectorAll('[required]');
            let isValid = true;
            
            requiredFields.forEach(field => {
                if (!field.value.trim()) {
                    isValid = false;
                    field.style.borderColor = '#DC3545';
                    
                    // Remover o estilo após 3 segundos
                    setTimeout(() => {
                        field.style.borderColor = '';
                    }, 3000);
                }
            });
            
            // Verificar se tem pelo menos 3 fotos
            const photos = previewContainer.querySelectorAll('.preview-item');
            if (photos.length < 3) {
                isValid = false;
                alert('Por favor, adicione pelo menos 3 fotos.');
                uploadArea.scrollIntoView({ behavior: 'smooth' });
            }
            
            if (isValid) {
                // Aqui você pode implementar o envio do formulário
                alert('Anúncio publicado com sucesso!');
                // form.submit(); // Descomente esta linha para enviar o formulário
            } else {
                alert('Por favor, preencha todos os campos obrigatórios.');
            }
        });
    }

    // Pré-visualização do anúncio
    const btnPreview = document.getElementById('btn-preview');
    if (btnPreview) {
        btnPreview.addEventListener('click', function() {
            // Aqui você pode implementar a lógica de pré-visualização
            alert('Funcionalidade de pré-visualização em desenvolvimento.');
        });
    }

    // Validação de idade
    const idadeMin = document.getElementById('idade-min');
    const idadeMax = document.getElementById('idade-max');
    
    if (idadeMin && idadeMax) {
        idadeMin.addEventListener('change', validateAgeRange);
        idadeMax.addEventListener('change', validateAgeRange);
    }
    
    function validateAgeRange() {
        const min = parseInt(idadeMin.value) || 0;
        const max = parseInt(idadeMax.value) || 0;
        
        if (min > 0 && max > 0 && min > max) {
            alert('A idade mínima não pode ser maior que a idade máxima.');
            idadeMin.value = '';
            idadeMax.value = '';
        }
    }

    // Auto-complete para cidade
    const cidadeSelect = document.getElementById('cidade');
    if (cidadeSelect) {
        // Aqui você pode adicionar auto-complete ou outras funcionalidades
    }
});