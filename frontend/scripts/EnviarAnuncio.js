document.getElementById('form-anuncio').addEventListener('submit', async (e) => {
    e.preventDefault(); // Impede o recarregamento da página

    // Captura os dados do formulário
    const formData = new FormData(e.target);
    
    // Converte o FormData para um objeto JSON simples
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
        areaImovel: parseFloat(formData.get('area-imovel')),
        andar: formData.get('andar'),
        tipologia: formData.get('tipologia'),
        titulo: formData.get('titulo')
    };

    try {
        const response = await fetch('http://localhost:8080/api/anuncios', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados)
        });

        if (response.ok) {
            alert('Anúncio publicado com sucesso!');
            window.location.href = 'index.html'; // Redireciona para a home
        } else {
            alert('Erro ao publicar anúncio.');
        }
    } catch (error) {
        console.error('Erro na ligação:', error);
        alert('Não foi possível ligar ao servidor.');
    }
});