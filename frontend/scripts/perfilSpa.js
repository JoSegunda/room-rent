  console.log("Working")

const updateContent = () => {
  const content = document.getElementById('dinamic-container');
  console.log("Working")
  const hash = window.location.hash.substring(1); // Remove the '#' from the hash

  switch (hash) {
    case 'nav-perfil':
      content.innerHTML = `
          <h1>Home</h1>
          <p>Welcome to the home page.</p>
        `;
      break;
    case 'nav-post':
        const fileUrl = '../pages/add-post-form.txt';
        fetch(fileUrl)
            .then(r => r.text())
            .then(t => content.innerHTML = t)
      
      break;
    case 'nav-ads':
      content.innerHTML = `
          <h1>Meus anúncios</h1>
          <p>Get in touch with us.</p>
        `;
      break;
    case 'nav-chat':
      content.innerHTML = `
          <h1>conversas</h1>
          <p>Get in touch with us.</p>
        `;
      break;
  }
};

// Event listener for hashchange
window.addEventListener('hashchange', updateContent);
