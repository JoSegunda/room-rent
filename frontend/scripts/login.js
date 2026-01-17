document.addEventListener('submit', async (e) => {
    // Agora o ID coincide com o formulário no HTML
    if (e.target.id === 'login-form') {
        e.preventDefault();
        
        const dados = {
            email: e.target.email.value,
            password: e.target.password.value
        };

        try {
            const response = await fetch('http://localhost:8080/api/users/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(dados)
            });

            if (response.ok) {
                const user = await response.json();
                // Guardar sessão no browser
                localStorage.setItem('usuarioLogado', JSON.stringify(user));
                // Redirecionar para o perfil
                window.location.href = 'perfil.html';
            } else {
                const erroTexto = await response.text();
                alert("Erro: " + erroTexto);
            }
        } catch (error) {
            console.error("Erro na ligação ao servidor:", error);
        }
    }
});