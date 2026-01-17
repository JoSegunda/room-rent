document.addEventListener('DOMContentLoaded', async () => {
    const loggedUserId = localStorage.getItem('userId'); // Assume que guardas isto no login
    const contactSection = document.querySelector('.contact-ad');

    if (!loggedUserId) {
        contactSection.innerHTML = "<p>Faça login para contactar o anunciante.</p>";
    } else {
        // Adicionar evento ao botão de enviar mensagem
        const btnEnviar = document.querySelector('.contact-btn');
        btnEnviar.onclick = async () => {
            const msg = prompt("Escreva a sua mensagem:");
            if (msg) {
                const payload = {
                    conteudo: msg,
                    anuncio: { id: adId }, // adId vem da URL
                    remetente: { id: loggedUserId }
                };

                const res = await fetch('http://localhost:8080/api/mensagens', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(payload)
                });

                if (res.ok) alert("Mensagem enviada!");
            }
        };
    }

    // 1. Pegar o ID da URL (ex: ad-info.html?id=5)
    const urlParams = new URLSearchParams(window.location.search);
    const adId = urlParams.get('id');

    if (!adId) {
        alert("Anúncio não encontrado!");
        window.location.href = 'resultados.html';
        return;
    }

    try {
        // 2. Pedir os dados ao Backend
        const response = await fetch(`http://localhost:8080/api/anuncios/${adId}`);
        if (!response.ok) throw new Error("Erro ao carregar anúncio");
        
        const ad = await response.json();

        // 3. Preencher o HTML com os dados reais
        document.querySelector('.ad-info h3').textContent = ad.titulo;
        document.querySelector('.ad-info h4').textContent = `${ad.preco} €/mês`;
        
        // Exemplo para preencher as listas
        const lookingForList = document.querySelector('.looking-for ul');
        lookingForList.innerHTML = `
            <li>${ad.generoProcurado || 'Indiferente'}</li>
            <li>Idade: ${ad.idadeMin} - ${ad.idadeMax} anos</li>
        `;

        const houseSetupList = document.querySelector('.house-setup ul');
        houseSetupList.innerHTML = `
            <li>${ad.tipologia}</li>
            <li>Andar: ${ad.andar}</li>
            <li>Área: ${ad.area} m²</li>
        `;

        // Se tiveres foto:
        if (ad.fotoUrl) {
            document.querySelector('.img-slider').style.backgroundImage = `url('${ad.fotoUrl}')`;
        }

    } catch (error) {
        console.error(error);
        alert("Erro ao carregar detalhes.");
    }
});