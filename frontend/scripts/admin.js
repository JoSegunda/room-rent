document.addEventListener('DOMContentLoaded', () => {
    // --- REQUISITO 1: REQUERER AUTENTICAÇÃO ---
    const userJson = localStorage.getItem('usuarioLogado');
    const user = userJson ? JSON.parse(userJson) : null;

    // Se não houver user ou se o papel não for ADMIN, expulsa para o login
    if (!user || user.role !== 'ADMIN') {
        window.location.href = '../index.html';
        return;
    }

    // Se for admin, carrega os dados
    carregarEstatisticas();
    carregarUtilizadoresPendentes();
    carregarAnunciosPendentes();
});

// --- REQUISITO 2: PREENCHER TABELAS DE PEDIDOS PENDENTES ---

async function carregarEstatisticas() {
    const res = await fetch('http://localhost:8080/api/admin/stats');
    const stats = await res.json();
    
    document.getElementById('total-ads').textContent = stats.totalAds;
    document.getElementById('total-users').textContent = stats.totalUsers;
    document.getElementById('pending-count').textContent = stats.pendingUsers;
}

async function carregarUtilizadoresPendentes() {
    const res = await fetch('http://localhost:8080/api/admin/users/pendentes');
    const users = await res.json();
    const tbody = document.getElementById('pending-users-table-body');
    
    tbody.innerHTML = users.map(u => `
        <tr>
            <td>${u.nome}</td>
            <td>${u.email}</td>
            <td>${u.role}</td>
            <td>${new Date().toLocaleDateString()}</td>
            <td>
                <button class="btn-approve" onclick="aprovarUtilizador(${u.id})">
                    Aprovar
                </button>
            </td>
        </tr>
    `).join('');
}

async function carregarAnunciosPendentes() {
    const res = await fetch('http://localhost:8080/api/admin/anuncios/pendentes');
    const anuncios = await res.json();
    const tbody = document.getElementById('pending-ads-table-body');
    
    tbody.innerHTML = anuncios.map(a => `
        <tr>
            <td>${a.titulo}</td>
            <td>${a.user ? a.user.nome : 'N/A'}</td>
            <td>${a.cidade}</td>
            <td>${a.preco}€</td>
            <td>${new Date().toLocaleDateString()}</td>
            <td>
                <button class="btn-validate" onclick="validarAnuncio(${a.id})">
                     Validar e Ativar
                </button>
            </td>
        </tr>
    `).join('');
}

// Funções para os botões das tabelas
async function aprovarUtilizador(id) {
    if(confirm("Deseja aprovar o acesso deste utilizador?")) {
        await fetch(`http://localhost:8080/api/admin/users/${id}/aprovar`, { method: 'PUT' });
        location.reload(); // Recarrega para atualizar listas e stats
    }
}

async function validarAnuncio(id) {
    if(confirm("Confirmou que o conteúdo não é ofensivo e deseja ativar o anúncio?")) {
        await fetch(`http://localhost:8080/api/admin/anuncios/${id}/ativar`, { method: 'PUT' });
        location.reload();
    }
}