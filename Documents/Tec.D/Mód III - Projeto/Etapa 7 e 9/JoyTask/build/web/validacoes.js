function cadastrarColaborador() {
  var nome = document.getElementById("nome").value;
  var email = document.getElementById("email").value;
  var cargo = document.getElementById("cargo").value;
  var nivel = document.getElementById("nivel").value;

  if (nome && email && cargo && nivel) {
    fetch("cadastrarColaborador", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      body: `nome=${encodeURIComponent(nome)}&email=${encodeURIComponent(email)}&cargo=${encodeURIComponent(cargo)}&nivel=${encodeURIComponent(nivel)}`
    })
    .then(response => {
      if (response.ok || response.redirected) {
        alert("Colaborador cadastrado com sucesso!");
        adicionarNaTabela(nome, email, cargo, nivel);
        limparCampos();
      } else {
        alert("Erro ao cadastrar colaborador.");
      }
    })
    .catch(error => {
      console.error("Erro ao enviar dados:", error);
      alert("Erro ao enviar dados ao servidor.");
    });
  } else {
    alert("Por favor, preencha todos os campos.");
  }
}

function adicionarNaTabela(nome, email, cargo, nivel) {
  var tabela = document.getElementById("tabelaColaboradores").getElementsByTagName('tbody')[0];
  var novaLinha = tabela.insertRow();

  novaLinha.insertCell(0).textContent = nome;
  novaLinha.insertCell(1).textContent = email;
  novaLinha.insertCell(2).textContent = cargo;
  novaLinha.insertCell(3).textContent = nivel;
}

function limparCampos() {
  document.getElementById("nome").value = "";
  document.getElementById("email").value = "";
  document.getElementById("cargo").value = "";
  document.getElementById("nivel").value = "";
}
