

const updateContent = () => {
  const content = document.getElementById('dinamic-container');
  console.log("Working")
  const hash = window.location.hash.substring(1); // Remove the '#' from the hash

  const user = JSON.parse(localStorage.getItem('usuarioLogado'));
  if (!user) {
      window.location.href = 'login.html';
      return;
  }

  switch (hash) {
    case 'nav-post':
    // Verificar se o utilizador está aprovado no objeto guardado no localStorage
      if (user && user.aprovado === false) {
          content.innerHTML = `
              <div class="alert-container" style="text-align:center; padding: 50px;">
                  <i class="fas fa-user-clock" style="font-size: 4rem; color: #f39c12;"></i>
                  <h2>Conta em Aguarda Aprovação</h2>
                  <p>O seu registo ainda está a ser validado pela nossa equipa de administração.</p>
                  <p>Poderá publicar anúncios assim que a sua conta for aprovada. Obrigado pela paciência!</p>
              </div>
          `;
      } else {
          const fileUrl = '../pages/add-post-form.txt';
          fetch(fileUrl)
              .then(r => r.text())
              .then(t => content.innerHTML = t);
      }
    break;
    case 'nav-ads':
      content.innerHTML = `
          <h1>Meus anúncios</h1>
          <p>Get in touch with us.</p>
        `;
      break;
    case 'nav-chat':
      content.innerHTML = `
          <h1>conversas</h1>
          <p>Get in touch with us.</p>
        `;
      break;
      default:
        const userLogado = JSON.parse(localStorage.getItem('usuarioLogado'));
        const nomeExibir = userLogado ? userLogado.nome : "Utilizador";
        content.innerHTML = `
          <!-- Cabeçalho do Perfil -->
      <div class="profile-header">
        <div class="profile-banner"></div>

        <div class="profile-info">

          <div class="profile-details">
            <div class="profile-main">
              <h1 class="profile-name">${nomeExibir}</h1>
              <p class="profile-title">
                <i class="fas fa-map-marker-alt"></i> Évora, Portugal
                <i class="fas fa-envelope"></i> ${user.email}
              </p>
            </div>

        
          </div>
        </div>
      </div>

      <div class="profile-content">
        <div class="profile-main-content">
          <section class="content-section">
            <div class="section-header">
              <h2><i class="fas fa-home"></i> Meus Anúncios Recentes</h2>
            </div>
            <div class="ads-grid" id="meus-anuncios-lista">
              <p>A carregar os teus anúncios...</p>
            </div>
          </section>
        </div>
      </div>
        `;
        // CHAMADA PARA CARREGAR OS DADOS LOGO APÓS CRIAR O HTML
      carregarMeusAnuncios(user.id);
      break;
      
  }
};

// Função para procurar anúncios do utilizador no Backend
async function carregarMeusAnuncios(userId) {
    const listaContainer = document.getElementById('meus-anuncios-lista');
    if (!listaContainer) return;

    try {
        const response = await fetch(`http://localhost:8080/api/anuncios/meus-anuncios/${userId}`);
        if (!response.ok) throw new Error("Erro ao procurar anúncios");
        
        const anuncios = await response.json();
        renderizarMeusAnuncios(anuncios, listaContainer);
    } catch (error) {
        console.error("Erro:", error);
        listaContainer.innerHTML = "<p>Erro ao carregar os teus anúncios.</p>";
    }
}

// Função para gerar o HTML dinâmico
function renderizarMeusAnuncios(anuncios, container) {
    container.innerHTML = ''; // Limpa o carregando...

    if (anuncios.length === 0) {
        container.innerHTML = '<p>Ainda não publicaste nenhum anúncio.</p>';
        return;
    }

    anuncios.forEach(ad => {
        const card = `
            <div class="ad-card">
                <div class="ad-image" style="background-image: url('${ad.fotoUrl || '../media/room.jpg'}')">
                    <span class="ad-status active">Ativo</span>
                </div>
                <div class="ad-content">
                    <h3><a href="ad-info.html?id=${ad.id}">${ad.titulo}</a></h3>
                    <p class="ad-location">
                        <i class="fas fa-map-marker-alt"></i> ${ad.cidade}
                    </p>
                    <p class="ad-price">${ad.preco}€ <span>/mês</span></p>
                    <div class="ad-meta">
                        <span><i class="fas fa-bed"></i> ${ad.tipologia}</span>
                        <span><i class="fas fa-ruler-combined"></i> ${ad.area || '--'} m²</span>
                    </div>
                </div>
            </div>
        `;
        container.insertAdjacentHTML('beforeend', card);
    });
}

// Event listener for hashchange
window.addEventListener('hashchange', updateContent);
window.addEventListener('load', updateContent);
