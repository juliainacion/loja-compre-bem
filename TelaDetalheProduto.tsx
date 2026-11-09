import { Image, StyleSheet, Text, View } from 'react-native';
import { produtosMock, Produto } from './TelaListaProdutos';

function DetalheProduto({ produto }: { produto: Produto }) {
    return (
        <View style={styles.container}>
            <Image source={produto.imagem} style={styles.imagem} />
            <Text style={styles.nome}>{produto.nome}</Text>
            <Text style={styles.preco}>{produto.preco}</Text>
        </View>
    );
}

export default function TelaDetalheProduto({ route }: { route: any }) {
    const { produtoId } = route.params;
    const produto = produtosMock.find((p) => p.id === produtoId);

    if (!produto) {
        return (
            <View style={styles.container}>
                <Text>Produto não encontrado.</Text>
            </View>
        );
    }

    return <DetalheProduto produto={produto} />;
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
        padding: 20,
    },
    imagem: {
        width: 200,
        height: 200,
        marginBottom: 16,
    },
    nome: {
        fontSize: 22,
        fontWeight: 'bold',
        textAlign: 'center',
    },
    preco: {
        fontSize: 18,
        color: '#2E7D32',
        marginTop: 8,
    },
});