// Função para atualizar a barra de navegação dinamicamente
function atualizarMenuNavegacao() {
    const navList = document.getElementById('nav-list');
    const user = JSON.parse(localStorage.getItem('usuarioLogado'));

    if (!navList) return;

    if (user) {
        // Se estiver autenticado, mostra o Perfil e a opção de Sair
        navList.innerHTML = `
            <li><a href="perfil.html">Meu Perfil</a></li>
            <li><a href="#" id="logout-link">Sair</a></li>
        `;

        document.getElementById('logout-link').addEventListener('click', (e) => {
            e.preventDefault();
            localStorage.removeItem('usuarioLogado');
            window.location.href = '../index.html';
        });
    } else {
        // Se NÃO estiver autenticado, mostra Login e Registo
        navList.innerHTML = `
            <li><a href="login.html">Login</a></li>
            <li><a href="signup.html">Registar</a></li>
        `;
    }
}

// Executar sempre que a página carrega
window.addEventListener('DOMContentLoaded', atualizarMenuNavegacao);