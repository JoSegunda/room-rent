const adsContainer = document.getElementById('ads-list');
let currentPage = 0;

async function carregarAnuncios(page = 0) {
    try {
        const response = await fetch(`http://localhost:8080/api/anuncios/paginado?page=${page}&size=4`);
        
        if (!response.ok) throw new Error("Erro na API: " + response.status);

        const data = await response.json();
        
        // Verifica se data e data.content existem antes de usar
        if (data && data.content) {
            renderizarAnuncios(data.content);
            renderizarPaginacao(data.totalPages, data.number);
        }
    } catch (error) {
        console.error("Erro ao carregar anúncios:", error);
        document.getElementById('ads-list').innerHTML = "<p>Erro ao carregar os anúncios. Verifica se o backend está ligado.</p>";
    }
}

function renderizarAnuncios(anuncios) {
    adsContainer.innerHTML = ''; // Limpa os anúncios antigos

    anuncios.forEach(ad => {
        const adHtml = `
            <div class="ad-wrapper">
                <div class="ad-img-all" style="background-image: url('${ad.fotoUrl || '../media/default-room.jpg'}')"></div>
                <div class="ad-content">
                    <h3><a href="ad-info.html?id=${ad.id}">${ad.titulo}</a></h3>
                    <h4>${ad.preco} €/mês</h4>
                    <h3 id="autor-anuncio">Localização: ${ad.cidade}</h3>
                    <div class="extra-ad-info">
                        <p><i class="fas fa-bed"></i> ${ad.quartosDisponiveis} quartos</p>
                        <p><i class="fas fa-venus-mars"></i> ${ad.generoProcurado || 'Indiferente'}</p>
                        <p><i class="fas fa-house"></i> ${ad.tipologia}</p>
                    </div>
                </div>
            </div>
        `;
        adsContainer.insertAdjacentHTML('beforeend', adHtml);
    });
}

function renderizarPaginacao(totalPaginas, paginaAtual) {
    const paginationContainer = document.querySelector('.pagination');
    paginationContainer.innerHTML = '';

    for (let i = 0; i < totalPaginas; i++) {
        const btn = document.createElement('button');
        btn.innerText = i + 1;
        btn.className = `page-btn ${i === paginaAtual ? 'active' : ''}`;
        btn.onclick = () => carregarAnuncios(i);
        paginationContainer.appendChild(btn);
    }
}

// Iniciar a primeira carga
carregarAnuncios(0);