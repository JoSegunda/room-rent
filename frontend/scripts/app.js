fetch("http://localhost:8080/api/hello")
  .then((res) => res.text())
  .then((data) => {
    console.log(data);
  });

//Register form
const form = document.getElementById("form-anuncio");
if (form) {
  document
    .getElementById("registerForm")
    .addEventListener("submit", function (e) {
      e.preventDefault();

      const user = {
        name:
          document.getElementById("fname").value +
          document.getElementById("lname").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
      };

      fetch("http://localhost:8080/api/users/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
      })
        .then((res) => {
          if (!res.ok) throw new Error("Erro ao registar");
          return res.json();
        })
        .then((data) => {
          alert("Utilizador registado com sucesso!");
          console.log(data);
        })
        .catch((err) => alert(err.message));
    });
}

function verificarSessao() {
    const user = JSON.parse(localStorage.getItem('usuarioLogado'));
    const navList = document.getElementById('nav-list');

    if (user && navList) {
        // Substitui Login/Registo pelo nome e Logout
        navList.innerHTML = `
            <li class="nav-item" id="greetings">Olá,<a href="../frontend/pages/perfil.html"> ${user.nome}</a></li>
            <li class="nav-item"><button class="btn" id="logout-btn">Sair</button></li>
        `;

        document.getElementById('logout-btn').onclick = () => {
            localStorage.removeItem('usuarioLogado');
            window.location.href = '../index.html';
        };
    }
}

// Chamar a função ao carregar a página
window.addEventListener('load', verificarSessao);