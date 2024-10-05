// Aqui vou criar uma função para somar um array de números 

function somarArray(array) {
  return array.reduce((acc, curr) => acc + curr, 0);
}

// função para testar a função somarArray

function testSomarArray() {
  const array = [1, 2, 3, 4, 5];
  const resultado = somarArray(array);
  console.log(resultado === 15
    ? 'Teste passou'
    : 'Teste falhou');
}

