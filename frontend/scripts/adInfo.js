document.addEventListener('DOMContentLoaded', async () => {
    // 1. Obter o ID do anúncio da URL IMEDIATAMENTE (necessário para as mensagens e para o fetch)
    const urlParams = new URLSearchParams(window.location.search);
    const adId = urlParams.get('id');

    if (!adId) {
        alert("Anúncio não encontrado!");
        window.location.href = 'resultados.html';
        return;
    }

    // 2. Gerir a Secção de Contacto (Autenticação)
    // Recuperamos o objeto completo que guardámos no Login/Registo
    const userJson = localStorage.getItem('usuarioLogado');
    const user = userJson ? JSON.parse(userJson) : null;
    const contactSection = document.querySelector('.contact-ad');
    const btnEnviar = document.querySelector('.contact-btn');

    if (!user) {
        if (contactSection) {
            contactSection.innerHTML = `<p style="text-align:center; padding:20px;">
                <a href="login.html" style="color: var(--primary-color); font-weight: bold;">
                Faça login para contactar o anunciante.</a></p>`;
        }
    } else {
        if (btnEnviar) {
            btnEnviar.onclick = async () => {
                const msg = prompt("Escreva a sua mensagem para o anunciante:");
                if (msg) {
                    const payload = {
                        conteudo: msg,
                        anuncio: { id: parseInt(adId) },
                        remetente: { id: user.id } // Usamos o ID do objeto user
                    };

                    try {
                        const res = await fetch('http://localhost:8080/api/mensagens', {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify(payload)
                        });

                        if (res.ok) alert("Mensagem enviada com sucesso!");
                        else alert("Erro ao enviar mensagem.");
                    } catch (err) {
                        console.error("Erro no fetch das mensagens:", err);
                    }
                }
            };
        }
    }

    // 3. Carregar os Dados do Anúncio
    try {
        const response = await fetch(`http://localhost:8080/api/anuncios/${adId}`);
        
        if (response.status === 404) {
            // Se o Java devolver 404, mostramos uma mensagem limpa
            document.body.innerHTML = `
                <div style="text-align:center; padding:50px;">
                    <h1>Anúncio não encontrado</h1>
                    <p>O anúncio com o ID ${adId} pode ter sido removido.</p>
                    <a href="resultados.html">Voltar à pesquisa</a>
                </div>`;
            return;
        }

        if (!response.ok) throw new Error("Erro ao carregar anúncio");
        
        const ad = await response.json();

        // 4. Preencher o HTML (Verifica se os seletores batem certo com o teu HTML)
        const tituloElem = document.querySelector('.ad-info h3');
        const precoElem = document.querySelector('.ad-info h4');
        
        if (tituloElem) tituloElem.textContent = ad.titulo;
        if (precoElem) precoElem.textContent = `${ad.preco} €/mês`;
        
        // Mapeamento dos campos exatos do Java (Anuncio.java)
        const lookingForList = document.querySelector('.looking-for ul');
        if (lookingForList) {
            lookingForList.innerHTML = `
                <li><strong>Género:</strong> ${ad.generoProcurado || 'Indiferente'}</li>
                <li><strong>Idade:</strong> ${ad.idadeMinima || 18} - ${ad.idadeMaxima || 99} anos</li>
            `;
        }

        const houseSetupList = document.querySelector('.house-setup ul');
        if (houseSetupList) {
            houseSetupList.innerHTML = `
                <li><strong>Tipologia:</strong> ${ad.tipologia}</li>
                <li><strong>Andar:</strong> ${ad.andar || 'R/C'}</li>
                <li><strong>Área:</strong> ${ad.areaImovel || '--'} m²</li>
                <li><strong>Cidade:</strong> ${ad.cidade}</li>
            `;
        }

        // Atualizar a imagem
        const imgSlider = document.querySelector('.img-slider');
        if (imgSlider) {
            imgSlider.style.backgroundImage = `url('${ad.fotoUrl || '../media/room.jpg'}')`;
            imgSlider.style.backgroundSize = 'cover';
            imgSlider.style.backgroundPosition = 'center';
        }

    } catch (error) {
        console.error("Erro ao carregar detalhes:", error);
        // alert("Erro ao carregar os detalhes do anúncio.");
    }
});