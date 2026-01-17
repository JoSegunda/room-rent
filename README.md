Room-Rent - Sistema de Aluguer de Quartos
=============================================

O **Room-Rent** é uma plataforma _full-stack_ desenvolvida para facilitar a ligação entre proprietários de imóveis e indivíduos à procura de alojamento. O sistema permite a publicação, gestão e procura de quartos, contando com um fluxo rigoroso de moderação administrativa para garantir a segurança dos utilizadores.

+4

🚀 Funcionalidades Principais
-----------------------------

### Utilizadores

*   **Registo e Autenticação:** Sistema de login seguro com armazenamento de palavras-passe via hashing BCrypt.
    
*   **Perfis de Acesso:** Diferenciação entre utilizadores comuns (USER) e administradores (ADMIN).+1
    
*   **Aprovação de Conta:** Novos utilizadores requerem validação manual do administrador antes de poderem publicar anúncios.+1
    

### Anúncios

*   **Publicação de Quartos:** Criação de anúncios detalhados (oferta ou procura) com campos para preço, tipologia, localização e requisitos específicos.+2
    
*   **Fluxo de Moderação:** Todos os anúncios submetidos ficam ocultos até serem validados por um administrador (estado ativo = false).+2
    
*   **Procura Avançada:** Filtros dinâmicos por tipo, localização e texto, com suporte para paginação e ordenação no servidor.+1
    

### Administração

*   **Dashboard de Controlo:** Visualização de estatísticas gerais do sistema.+2
    
*   **Gestão de Pedidos:** Interface dedicada para aprovar utilizadores e validar novos anúncios.+1
    

🛠️ Tecnologias Utilizadas
--------------------------

### Backend

*   **Java 17** com **Spring Boot 4.0.1**.
    
*   **Spring Data JPA** e **Hibernate** para persistência.
    
*   **Spring Security** para proteção e hashing.+1
    
*   **Maven** para gestão de dependências.
    

### Frontend

*   **HTML5** e **CSS3** (Design Responsivo).
    
*   **JavaScript (ES6+)** puro para lógica de cliente e comunicação assíncrona.
    
*   **Font Awesome** para iconografia.
    

### Base de Dados

*   **PostgreSQL** (Base de dados relacional).+1
    

🏗️ Estrutura do Projeto
------------------------

*   /backend: Contém a lógica de API REST, modelos, repositórios e serviços Java.
    
*   /frontend: Contém as páginas estáticas, estilos CSS e scripts de comunicação com a API.
    

⚙️ Instalação e Execução
------------------------

### Pré-requisitos

*   Java JDK 17.
    
*   Maven.
    
*   PostgreSQL em execução (Porta 5432).
    

### Passos

1.  **Configurar a Base de Dados:** Cria uma base de dados chamada room\_rent no PostgreSQL.
    
2.  **Configurar o Backend:** Edita o ficheiro src/main/resources/application.properties com as tuas credenciais do PostgreSQL.+1
    
3.  **Aceder ao Frontend:** Abre o ficheiro frontend/index.html num navegador (recomenda-se o uso do _Live Server_).
    

🔒 Segurança e Boas Práticas
----------------------------

*   **Proteção SQL:** Uso de _Prepared Statements_ via JPA para prevenir injeção de SQL.
    
*   **CORS:** Configurado para aceitar pedidos apenas de origens autorizadas.+1
    
*   **Senhas Seguras:** Implementação de BCrypt para evitar o armazenamento de texto limpo na base de dados.
    

📝 Nota do Desenvolvedor
------------------------

Este é um projeto académico que foca na arquitetura robusta e fluxos de moderação.

*   **Aviso:** O sistema de mensagens internas não foi implementado nesta versão.
    
*   **Futuro:** Planeada a integração de carregamento real de imagens e notificações em tempo real.