document.addEventListener('DOMContentLoaded', () => {
    // Capturar os elementos do HTML
    const adsContainer = document.getElementById('ads-list');
    const filterForm = document.getElementById('filter-form');
    const searchInput = document.getElementById('search-name');
    const paginationContainer = document.querySelector('.pagination');

    if (!adsContainer) {
        console.error("Contentor 'ads-list' não encontrado!");
        return;
    }

    // Função para carregar anúncios
    async function carregarAnuncios(page = 0) {
        const tipo = document.getElementById('filter-ad')?.value || "";
        const search = document.getElementById('search-name')?.value || "";
        const sort = document.getElementById('filter-options')?.value || "";

        const url = new URL('http://localhost:8080/api/anuncios/paginado');
        url.searchParams.append('page', page);
        url.searchParams.append('size', 4);
        if (tipo) url.searchParams.append('tipo', tipo);
        if (search) url.searchParams.append('search', search);
        if (sort) url.searchParams.append('sort', sort);

        try {
            const response = await fetch(url);
            if (!response.ok) throw new Error("Erro no servidor (500)");

            const data = await response.json();
            
            if (data && data.content) {
                renderizarAnuncios(data.content);
                renderizarPaginacao(data.totalPages, data.number);
            }
        } catch (error) {
            console.error("Erro ao carregar anúncios:", error);
            adsContainer.innerHTML = `<p style="color:red">Erro ao ligar ao servidor. Verifique se o Backend está a correr.</p>`;
        }
    }

    // Função para renderizar os cards
    function renderizarAnuncios(anuncios) {
        adsContainer.innerHTML = ''; 
        if (anuncios.length === 0) {
            adsContainer.innerHTML = '<p>Nenhum anúncio encontrado.</p>';
            return;
        }

        anuncios.forEach(ad => {
            const adHtml = `
                <div class="ad-wrapper">
                    <div class="ad-content">
                        <h3><a href="ad-info.html?id=${ad.id}">${ad.titulo}</a></h3>
                        <h4>${ad.preco} €/mês</h4>
                        <p><strong>Cidade:</strong> ${ad.cidade}</p>
                        <div class="extra-ad-info">
                            <p><i class="fas fa-bed"></i> ${ad.quartosDisponiveis || 1} quartos</p>
                            <p><i class="fas fa-venus-mars"></i> ${ad.generoProcurado || 'Indiferente'}</p>
                            <p><i class="fas fa-house"></i> ${ad.tipologia}</p>
                        </div>
                    </div>
                </div>
            `;
            adsContainer.insertAdjacentHTML('beforeend', adHtml);
        });
    }

    // Função para renderizar a paginação
    function renderizarPaginacao(totalPaginas, paginaAtual) {
        if (!paginationContainer) return;
        paginationContainer.innerHTML = '';

        for (let i = 0; i < totalPaginas; i++) {
            const btn = document.createElement('button');
            btn.innerText = i + 1;
            btn.className = `page-btn ${i === paginaAtual ? 'active' : ''}`;
            btn.onclick = () => carregarAnuncios(i);
            paginationContainer.appendChild(btn);
        }
    }

    // Ativar os Filtros
    if (filterForm) {
        filterForm.addEventListener('change', () => carregarAnuncios(0));
    }

    if (searchInput) {
        let timer;
        searchInput.addEventListener('input', () => {
            clearTimeout(timer);
            timer = setTimeout(() => carregarAnuncios(0), 500);
        });
    }

    // Carga inicial
    carregarAnuncios(0);
});