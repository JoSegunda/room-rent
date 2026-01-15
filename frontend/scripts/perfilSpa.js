  console.log("Working")

const updateContent = () => {
  const content = document.getElementById('dinamic-container');
  console.log("Working")
  const hash = window.location.hash.substring(1); // Remove the '#' from the hash

  switch (hash) {
    case 'nav-post':
        const fileUrl = '../pages/add-post-form.txt';
        fetch(fileUrl)
            .then(r => r.text())
            .then(t => content.innerHTML = t)
      
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
        content.innerHTML = `
          <!-- Cabeçalho do Perfil -->
      <div class="profile-header">
        <div class="profile-banner"></div>

        <div class="profile-info">
          <div class="avatar-section">
            <div class="avatar-wrapper">
              <img
                src="../media/jd.png"
                alt="Foto de perfil"
                class="profile-avatar"
              />
            </div>
          </div>

          <div class="profile-details">
            <div class="profile-main">
              <h1 class="profile-name">Helena Calado</h1>
              <p class="profile-title">
                <i class="fas fa-map-marker-alt"></i> Évora, Portugal
              </p>
            </div>

        
          </div>
        </div>
      </div>

      <div class="profile-content">
        <!-- Conteúdo Principal -->
        <div class="profile-main-content">
          <!-- Sobre Mim -->
          

          <!-- Meus Anúncios Recentes -->
          <section class="content-section">
            <div class="section-header">
              <h2><i class="fas fa-home"></i> Meus Anúncios Recentes</h2>
            </div>
            <div class="ads-grid">
              <div class="ad-card">
                <div
                  class="ad-image"
                  style="background-image: url('../media/room.jpg')"
                >
                  <span class="ad-status active">Ativo</span>
                </div>
                <div class="ad-content">
                  <h3>Quarto luminoso no centro de Évora</h3>
                  <p class="ad-location">
                    <i class="fas fa-map-marker-alt"></i> Centro Histórico,
                    Évora
                  </p>
                  <p class="ad-price">250€ <span>/mês</span></p>
                  <div class="ad-meta">
                    <span><i class="fas fa-bed"></i> T3</span>
                    <span><i class="fas fa-ruler-combined"></i> 15 m²</span>
                    <span
                      ><i class="fas fa-calendar"></i> Disponível agora</span
                    >
                  </div>
                </div>
              </div>

              <div class="ad-card">
                <div
                  class="ad-image"
                  style="background-image: url('../media/room.jpg')"
                >
                  <span class="ad-status rented">Alugado</span>
                </div>
                <div class="ad-content">
                  <h3>Apartamento T2 perto da Universidade</h3>
                  <p class="ad-location">
                    <i class="fas fa-map-marker-alt"></i> Bacelo, Évora
                  </p>
                  <p class="ad-price">350€ <span>/mês</span></p>
                  <div class="ad-meta">
                    <span><i class="fas fa-bed"></i> T2</span>
                    <span><i class="fas fa-ruler-combined"></i> 65 m²</span>
                    <span><i class="fas fa-calendar"></i> Dez 2023</span>
                  </div>
                </div>
              </div>
            </div>
          </section>
        </div>
      </div>
        `;
        break;
  }
};

// Event listener for hashchange
window.addEventListener('hashchange', updateContent);
window.addEventListener('load', updateContent);
