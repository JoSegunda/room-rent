// Localização: src/main/resources/static/scripts/EnviarAnuncio.js

document.addEventListener('submit', async (e) => {
    // 1. Verificar se o formulário é o correto (ID: form-anuncio)
    if (e.target.id === 'form-anuncio') {
        e.preventDefault();

        // 2. Recuperar o utilizador autenticado
        const userJson = localStorage.getItem('usuarioLogado');
        if (!userJson) {
            alert("Erro: Precisas de estar autenticado para publicar.");
            window.location.href = "login.html";
            return;
        }
        const user = JSON.parse(userJson);

        // 3. Capturar os dados do formulário
        const formData = new FormData(e.target);
        
        // Mapeamento exato entre os nomes no HTML e os atributos da Entidade Java
        const anuncioDados = {
            titulo: formData.get('titulo'),
            tipoAnuncio: formData.get('tipoAnuncio'), // 'oferta' ou 'procura'
            cidade: formData.get('cidade'),
            endereco: formData.get('endereco'),
            codigoPostal: formData.get('codigo-postal'),
            preco: parseFloat(formData.get('preco')),
            quartosDisponiveis: parseInt(formData.get('quartos-disponiveis')),
            idadeMin: parseInt(formData.get('idade-min')) || null,
            idadeMax: parseInt(formData.get('idade-max')) || null,
            generoProcurado: formData.get('genero-procurado'),
            area: parseInt(formData.get('area-imovel')) || null,
            andar: formData.get('andar'),
            tipologia: formData.get('tipologia')
        };

        try {
            // 4. Enviar para o Backend com o userId como parâmetro
            const response = await fetch(`http://localhost:8080/api/anuncios?userId=${user.id}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(anuncioDados)
            });

            if (response.ok) {
                const resultado = await response.json();
                
                // 5. Exibir feedback ao utilizador (Requisito d: Pagamento MB)
                alert("Sucesso! O teu anúncio foi registado.\n\n" + 
                      "DADOS DE PAGAMENTO MB:\n" + resultado.pagamento);

                // 6. Redirecionar para o perfil (SPA)
                window.location.hash = "#nav-perfil";
            } else {
                const erroTexto = await response.text();
                alert("Erro ao publicar: " + erroTexto);
            }
        } catch (error) {
            console.error("Erro na ligação:", error);
            alert("Não foi possível contactar o servidor.");
        }
    }
});