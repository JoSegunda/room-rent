document.addEventListener('submit', async (e) => {
    if (e.target.id === 'login-form') {
        e.preventDefault();
        
        const dados = {
            email: e.target.email.value,
            password: e.target.password.value
        };

        const response = await fetch('http://localhost:8080/api/users/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dados)
        });

        if (response.ok) {
            const user = await response.json();
            // Guardar sessão no browser
            localStorage.setItem('usuarioLogado', JSON.stringify(user));
            window.location.href = 'perfil.html';
        } else {
            alert("Email ou senha inválidos.");
        }
    }
});