# Exercício Prático — Aula 03

**Nome:** Julia Inacio    **Data:** 10/08/2026

Partindo da tela de lista de produtos da Loja Compre Bem construída em sala, acrescente por conta própria uma prop nova e um estado novo ao componente `ProdutoItem`.

**1. Qual prop nova você acrescentou ao `ProdutoItem`, e o que ela representa:**

Acrescentei a prop `estoque` (número), que representa a quantidade de unidades
disponíveis daquele produto. Ela vem do dado mockado (`produtosMock`) e o
componente `ProdutoItem` só lê esse valor, nunca o altera.

**2. Qual estado novo você acrescentou, e o que muda quando ele é atualizado:**

Acrescentei o estado `quantidade` (número, começa em 1), que representa quantas
unidades do produto o usuário quer selecionar. Quando os botões "+" ou "-" são
pressionados, o estado muda e a tela atualiza sozinha, mostrando o novo número —
sempre respeitando o limite entre 1 e o valor de `estoque`.

**3. Por que você decidiu que cada dado era prop (vem de fora, só leitura) ou estado (o componente controla e muda com o tempo):**

`estoque` é prop porque é um dado fixo que descreve o produto em si — vem de fora
(do mock) e não muda por interação do usuário, só quem "é dono" do produto poderia
alterá-lo. Já `quantidade` é estado porque é uma escolha do usuário, que muda a
cada clique nos botões, e é específica de cada `ProdutoItem` — o produto ao lado
tem sua própria quantidade, independente.

**4. Cole aqui o código da sua extensão do `ProdutoItem`:**

\`\`\`tsx
function ProdutoItem({ produto }: { produto: Produto }) {
  const [favorito, setFavorito] = useState(false);
  const [quantidade, setQuantidade] = useState(1);

  function aumentar() {
    if (quantidade < produto.estoque) {
      setQuantidade(quantidade + 1);
    }
  }

  function diminuir() {
    if (quantidade > 1) {
      setQuantidade(quantidade - 1);
    }
  }

  return (
    <View style={styles.item}>
      <Image source={produto.imagem} style={styles.imagem} />
      <Text style={styles.nome}>{produto.nome}</Text>
      <Text style={styles.preco}>{produto.preco}</Text>
      <Text style={styles.estoque}>Estoque: {produto.estoque} unidades</Text>

      <View style={styles.contador}>
        <Button title="-" onPress={diminuir} />
        <Text style={styles.quantidade}>{quantidade}</Text>
        <Button title="+" onPress={aumentar} />
      </View>

      <Button
        title={favorito ? '♥ Favorito' : '♡ Favoritar'}
        onPress={() => setFavorito(!favorito)}
      />
    </View>
  );
}
\`\`\`
