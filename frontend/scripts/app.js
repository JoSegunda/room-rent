fetch("http://localhost:8080/api/hello")
  .then(res => res.text())
  .then(data => {
    console.log(data);
  });
