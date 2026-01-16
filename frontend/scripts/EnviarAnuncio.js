// Usamos Delegação de Eventos para capturar formulários injetados dinamicamente
document.addEventListener('submit', async (e) => {
    // Verificamos se o alvo do evento é o nosso formulário de anúncio
    if (e.target && e.target.id === 'form-anuncio') {
        e.preventDefault();
        console.log("Formulário SPA detetado e submetido!");

        const formData = new FormData(e.target);
        
        // Mapeamento dos dados para o Java (CamelCase)
        const dados = {
            tipoAnuncio: formData.get('tipo-anuncio'),
            cidade: formData.get('cidade'),
            endereco: formData.get('endereco'),
            codigoPostal: formData.get('codigo-postal'),
            preco: parseFloat(formData.get('preco')),
            quartosDisponiveis: parseInt(formData.get('quartos-disponiveis')),
            idadeMin: parseInt(formData.get('idade-min')),
            idadeMax: parseInt(formData.get('idade-max')),
            generoProcurado: formData.get('genero-procurado'),
            area: parseFloat(formData.get('area-imovel')), // Nome da variável no Anuncio.java
            tipologia: formData.get('tipologia'),
            andar: formData.get('andar'),
            titulo: formData.get('titulo')
        };

        try {
            const response = await fetch('http://localhost:8080/api/anuncios', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(dados)
            });

            if (response.ok) {
                alert('Anúncio publicado com sucesso!');
                // Em SPA, em vez de recarregar, podes mudar o hash
                window.location.hash = '#nav-perfil'; 
            } else {
                alert('Erro ao guardar no servidor.');
            }
        } catch (error) {
            console.error('Erro na ligação:', error);
        }
    }
});