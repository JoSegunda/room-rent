document.addEventListener('DOMContentLoaded', () => {
    const signupForm = document.getElementById('signup-form');

    if (signupForm) {
        signupForm.addEventListener('submit', async (e) => {
            e.preventDefault();

            const formData = new FormData(e.target);
            const userDados = {
                // Junta nome e apelido com um espaço
                nome: formData.get('fname') + "  " + formData.get('lname'),
                email: formData.get('email'),
                password: formData.get('password')
            };

            try {
                const response = await fetch('http://localhost:8080/api/users/register', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(userDados)
                });

                if (response.ok) {
                    // Recebe o utilizador criado que o Backend enviou de volta
                    const user = await response.json();

                    // Guarda o utilizador no browser (isto cria a "sessão")
                    localStorage.setItem('usuarioLogado', JSON.stringify(user));

                    alert("Registo concluído! A entrar no seu perfil...");

                    // Redireciona diretamente para o perfil (ignora a página de login)
                    window.location.href = 'perfil.html';
                } else {
                    const erro = await response.text();
                    alert("Erro no registo: " + erro);
                }
            } catch (error) {
                console.error("Erro na ligação:", error);
            }
        });
    }
});