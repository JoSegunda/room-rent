const updateContent = () => {
  const content = document.getElementById('content');
  const hash = window.location.hash.substring(1); // Remove the '#' from the hash

  switch (hash) {
    case 'home':
      content.innerHTML = `
          <h1>Home</h1>
          <p>Welcome to the home page.</p>
        `;
      break;
    case 'about':
      content.innerHTML = `
          <h1>About</h1>
          <p>Learn more about us on this page.</p>
        `;
      break;
    case 'contact':
      content.innerHTML = `
          <h1>Contact</h1>
          <p>Get in touch with us.</p>
        `;
      break;
  }
};

// Event listener for hashchange
window.addEventListener('hashchange', updateContent);
