const updateContent = () => {
  const content = document.getElementById("dinamic-container");
  const user = JSON.parse(localStorage.getItem("usuarioLogado"));

  // Se não houver user, manda para o login
  if (!user) {
    window.location.href = "../pages/login.html";
    return;
  }

  const hash = window.location.hash.substring(1);

  switch (hash) {
    case "nav-post":
      fetch("../pages/add-post-form.txt")
        .then((r) => r.text())
        .then((t) => (content.innerHTML = t));
      break;

    case "nav-chat":
      content.innerHTML = `<h1>As Minhas Conversas</h1><div id="chat-list">Carregando mensagens...</div>`;
      // Aqui chamarias a função para carregar mensagens do backend
      break;

    default: // Caso Perfil (Home da SPA)
      const userLogado = JSON.parse(localStorage.getItem("usuarioLogado"));
      const nomeExibir = userLogado ? userLogado.nome : "Utilizador";
      content.innerHTML = `
          <div class="profile-header">
              <h1 class="profile-name">${nomeExibir}</h1>
              <p><i class="fas fa-envelope"></i> ${user.email}</p>
          </div>
          <div id="meus-anuncios">Carregando os teus anúncios...</div>
      `;
      carregarMeusAnuncios(user.id);
      break;
  }
};

// Event listener for hashchange
window.addEventListener("hashchange", updateContent);
window.addEventListener("load", updateContent);
