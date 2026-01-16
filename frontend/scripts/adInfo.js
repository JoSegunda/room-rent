document.addEventListener('DOMContentLoaded', async () => {
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