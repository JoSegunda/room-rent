document.addEventListener('DOMContentLoaded', () => {
    const adsContainer = document.getElementById('ads-list');
    const filterForm = document.getElementById('filter-form');
    const searchInput = document.getElementById('search-name');

    if (!filterForm || !adsContainer) {
        console.error("Erro: Elementos do DOM não encontrados.");
        return;
    }

    // Iniciar a carga
    carregarAnuncios(0);

    // Ouvir mudanças
    filterForm.addEventListener('change', () => carregarAnuncios(0));
    if (searchInput) {
        searchInput.addEventListener('input', () => carregarAnuncios(0));
    }
});


const adsContainer = document.getElementById('ads-list');
let currentPage = 0;

// Função principal de carga
async function carregarAnuncios(page = 0) {
    // 1. Capturar valores atuais dos filtros
    const tipo = document.getElementById('filter-ad').value;
    const search = document.getElementById('search-name').value;
    const sort = document.getElementById('filter-options').value;

    // 2. Construir a URL com filtros
    const url = new URL('http://localhost:8080/api/anuncios/paginado');
    url.searchParams.append('page', page);
    url.searchParams.append('size', 4);
    if (tipo) url.searchParams.append('tipo', tipo);
    if (search) url.searchParams.append('search', search);
    if (sort) url.searchParams.append('sort', sort);

    try {
        const response = await fetch(url);
        const data = await response.json();

        renderizarAnuncios(data.content);
        renderizarPaginacao(data.totalPages, data.number);
    } catch (error) {
        console.error("Erro ao filtrar:", error);
    }
}

// Ouvir mudanças no formulário
filterForm.addEventListener('change', () => carregarAnuncios(0));
document.getElementById('search-name').addEventListener('input', () => {
    // Debounce opcional: esperar o utilizador parar de digitar
    carregarAnuncios(0); 
});

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