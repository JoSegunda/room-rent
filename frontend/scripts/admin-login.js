document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');

    if (loginForm) {
        loginForm.addEventListener('submit', async (e) => {
            e.preventDefault();

            // Capturar dados do formulário
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            try {
                // Enviar pedido POST para o endpoint de login existente
                const response = await fetch('http://localhost:8080/api/users/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ email, password })
                });

                if (response.ok) {
                    const user = await response.json();

                    // verificar se o utilizador é Administrador
                    if (user.role === 'ADMIN') {
                        // Guardar a sessão no browser
                        localStorage.setItem('usuarioLogado', JSON.stringify(user));
                        
                        alert("Login de administrador bem-sucedido!");
                        // Redirecionar para o painel principal de admin
                        window.location.href = 'admin.html';
                    } else {
                        alert("Acesso negado: Esta conta não tem privilégios de administrador.");
                    }
                } else {
                    // Tratar erros do servidor (401 Unauthorized, etc.)
                    const errorMsg = await response.text();
                    alert("Erro no login: " + (errorMsg || "Credenciais inválidas."));
                }
            } catch (error) {
                console.error("Erro na ligação ao servidor:", error);
                alert("Não foi possível ligar ao servidor. Verifique se o backend está a correr.");
            }
        });
    }
});