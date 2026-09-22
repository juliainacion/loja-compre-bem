import { useState } from 'react';
import { Image, StyleSheet, Text, View, Button, TouchableOpacity, FlatList } from 'react-native';

export type Produto = {
    id: string;
    nome: string;
    preco: string;
    imagem: any;
    estoque: number;
};

export const produtosMock: Produto[] = [
    {
        id: '1',
        nome: 'Cadeira Ergonômica Elements Sophy – Preto & Azul',
        preco: 'R$ 2.799,00',
        imagem: require('./assets/Cadeira-ergonomica-Sophy-Azul.png'),
        estoque: 10,
    },
    {
        id: '2',
        nome: 'Mesa Industrial Grande Escritório Reunião Home Office 190x80',
        preco: 'R$ 655,80',
        imagem: require('./assets/mesa-industrial.png'),
        estoque: 5,
    },
    {
        id: '3',
        nome: 'Luminária Led Com Braço Oscilante Longo',
        preco: 'R$ 79,90',
        imagem: require('./assets/luminaria-led.png'),
        estoque: 15,
    },
];

function ProdutoItem({ produto, navigation }: { produto: Produto; navigation: any }) {
    const [favorito, setFavorito] = useState(false);
    const [quantidade, setQuantidade] = useState(1);

    function aumentar() {
        if (quantidade < produto.estoque) setQuantidade(quantidade + 1);
    }

    function diminuir() {
        if (quantidade > 1) setQuantidade(quantidade - 1);
    }

    return (
        <TouchableOpacity
            style={styles.item}
            onPress={() => navigation.navigate('DetalheProduto', { produtoId: produto.id })}
        >
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
        </TouchableOpacity>
    );
}

export default function TelaListaProdutos({ navigation }: { navigation: any }) {
    return (
        <FlatList
            data={produtosMock}
            keyExtractor={(item) => item.id}
            renderItem={({ item }) => <ProdutoItem produto={item} navigation={navigation} />}
            contentContainerStyle={styles.container}
        />
    );
}

const styles = StyleSheet.create({
    container: { alignItems: 'center', paddingVertical: 40, paddingHorizontal: 16 },
    item: { alignItems: 'center', marginBottom: 24, paddingBottom: 16, borderBottomWidth: 1, borderBottomColor: '#E0E0E0', width: '100%' },
    imagem: { width: 120, height: 120, marginBottom: 8, borderRadius: 8 },
    nome: { fontSize: 16, fontWeight: 'bold', textAlign: 'center', marginBottom: 4, paddingHorizontal: 8 },
    preco: { fontSize: 15, fontWeight: '600', color: '#2E7D32', marginBottom: 8 },
    estoque: { fontSize: 12, color: '#888', marginBottom: 8 },
    contador: { flexDirection: 'row', alignItems: 'center', gap: 12, marginBottom: 8 },
    quantidade: { fontSize: 16, fontWeight: 'bold', minWidth: 24, textAlign: 'center' },
});