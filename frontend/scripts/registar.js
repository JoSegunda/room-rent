document.addEventListener('DOMContentLoaded', () => {
    const signupForm = document.getElementById('signup-form');

    if (signupForm) {
        signupForm.addEventListener('submit', async (e) => {
            e.preventDefault();

            const formData = new FormData(e.target);
            const userDados = {
                // Adicionei um espaço entre o nome e apelido
                nome: formData.get('fname') + " " + formData.get('lname'),
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
                    // 1. Receber o utilizador criado pelo Backend
                    const user = await response.json();

                    // 2. Guardar no localStorage para que o perfilSpa.js o reconheça
                    localStorage.setItem('usuarioLogado', JSON.stringify(user));

                    alert("Registo concluído com sucesso!");

                    // 3. Redirecionar diretamente para o perfil
                    window.location.href = 'perfil.html';
                } else {
                    const erro = await response.text();
                    alert("Erro: " + erro);
                }
            } catch (error) {
                console.error("Erro na ligação:", error);
            }
        });
    }
});