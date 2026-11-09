package com.senai.julia.comprebem.config;

import com.senai.julia.comprebem.entity.Produto;
import com.senai.julia.comprebem.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Cadastra 65 produtos reais do mercado brasileiro na primeira execução
 * (quando o banco tem menos de 60). Preços aproximados, só para demonstração.
 */
@Configuration
public class CargaInicial {

    private static final int MINIMO = 60;

    private static final String[] ELETRONICOS = {
            "Smartphone Samsung Galaxy A55 5G 256 GB|2199.00|Celular com tela Super AMOLED de 6,6 polegadas, 120 Hz, câmera tripla de 50 MP, 8 GB de RAM e bateria de 5000 mAh.",
            "Apple iPhone 15 128 GB|4999.00|iPhone com tela Super Retina XDR de 6,1 polegadas, chip A16 Bionic, câmera principal de 48 MP e porta USB-C.",
            "Fone de Ouvido JBL Tune 520BT|229.90|Fone Bluetooth on-ear com som JBL Pure Bass, até 57 horas de bateria e microfone para chamadas.",
            "Smart TV Samsung 50\" Crystal UHD 4K|2399.00|Smart TV com resolução 4K, processador Crystal 4K, Wi-Fi integrado, Alexa e aplicativos de streaming.",
            "Notebook Lenovo IdeaPad Slim 3 15\"|3299.00|Notebook com tela Full HD de 15,6 polegadas, processador Intel Core i5, 8 GB de RAM e SSD de 512 GB.",
            "Caixa de Som JBL Flip 6|799.00|Caixa de som Bluetooth portátil, resistente à água e poeira (IP67), com até 12 horas de bateria.",
            "Mouse Logitech MX Master 3S|599.00|Mouse sem fio ergonômico com sensor de 8000 DPI, cliques silenciosos, rolagem eletromagnética e bateria de até 70 dias.",
            "Teclado Logitech K380 Bluetooth|229.00|Teclado compacto e silencioso que conecta em até 3 dispositivos, compatível com Windows, macOS, Android e iOS.",
            "Apple Watch SE 40 mm|2299.00|Relógio inteligente com GPS, monitoramento de frequência cardíaca, detecção de queda e resistência à água.",
            "Tablet Samsung Galaxy Tab A9+ 64 GB|1499.00|Tablet com tela de 11 polegadas a 90 Hz, 4 GB de RAM, som com 4 alto-falantes e bateria de 7040 mAh.",
            "Webcam Logitech C920 Full HD|449.00|Webcam 1080p com dois microfones estéreo, foco automático e correção de luz, ideal para aulas e reuniões online.",
            "Roteador TP-Link Archer C6 AC1200|249.90|Roteador Wi-Fi dual band com 4 antenas, velocidade de até 1200 Mbps e 4 portas Gigabit.",
            "Carregador Portátil Xiaomi 20000 mAh|179.90|Power bank de 20000 mAh com carregamento rápido de 22,5 W e duas saídas USB mais uma USB-C.",
    };

    private static final String[] ROUPAS = {
            "Camiseta Hering Básica Algodão|59.99|Camiseta de malha 100% algodão, gola redonda e modelagem tradicional, ótima para o dia a dia.",
            "Calça Jeans Levi's 511 Slim|399.90|Calça jeans masculina de corte slim com elastano, cinco bolsos e lavagem azul média.",
            "Jaqueta Corta-Vento Nike Windrunner|499.99|Jaqueta leve com capuz, repelente à água e proteção contra o vento, ideal para treinos ao ar livre.",
            "Tênis Nike Revolution 7|349.99|Tênis de corrida leve com espuma macia no solado, cabedal respirável e bom amortecimento para uso diário.",
            "Tênis Adidas Run Falcon 5|299.99|Tênis de corrida com cabedal em mesh respirável, entressola amortecida e solado de borracha com boa aderência.",
            "Moletom Adidas Essentials com Capuz|279.99|Moletom de algodão com capuz ajustável, bolso canguru e logo Adidas bordado no peito.",
            "Bermuda Puma Essentials|129.99|Bermuda esportiva de moletom leve com cordão ajustável, bolsos laterais e logo Puma.",
            "Meias Puma Cano Médio Kit 3 Pares|59.90|Kit com três pares de meias esportivas de algodão com reforço no calcanhar e na ponta.",
            "Boné New Era 9FORTY Aba Curva|169.90|Boné com ajuste traseiro, aba curva e logo bordado na frente, em tecido resistente.",
            "Camisa Polo Lacoste L.12.12|549.00|Polo clássica de piquet de algodão com gola de malha e o tradicional crocodilo bordado.",
            "Chinelo Havaianas Top|44.90|Chinelo de borracha flexível com tiras coloridas e solado antiderrapante, o clássico das Havaianas.",
            "Camiseta Nike Dri-FIT Masculina|129.99|Camiseta esportiva com tecnologia Dri-FIT que afasta o suor da pele e mantém o corpo seco durante o treino.",
            "Cueca Boxer Lupo Kit 3 Unidades|79.90|Kit com três cuecas boxer de algodão com elastano, cós macio e acabamento sem costura lateral.",
    };

    private static final String[] ALIMENTOS = {
            "Café Pilão Tradicional 500 g|24.90|Café torrado e moído de sabor equilibrado e aroma marcante, embalagem a vácuo.",
            "Arroz Tio João Tipo 1 5 kg|32.90|Arroz branco agulhinha tipo 1, grãos selecionados e soltinhos depois de cozidos.",
            "Feijão Camil Carioca 1 kg|8.99|Feijão carioca tipo 1 selecionado, cozimento rápido e caldo encorpado.",
            "Azeite Gallo Extra Virgem 500 ml|44.90|Azeite de oliva extra virgem português, acidez máxima de 0,5%, ideal para saladas e finalização.",
            "Chocolate Lacta Ao Leite 165 g|10.99|Barra de chocolate ao leite cremoso, tradicional da Lacta.",
            "Macarrão Barilla Espaguete nº 5 500 g|9.90|Massa italiana de sêmola de trigo durum que mantém o ponto al dente.",
            "Granola Jasmine Tradicional 800 g|19.90|Granola crocante com aveia, mel, castanhas e frutas secas, sem conservantes.",
            "Leite Integral Italac 1 L|5.49|Leite integral UHT, rico em cálcio e proteínas, embalagem longa vida.",
            "Biscoito Recheado Oreo Original 90 g|3.99|Biscoito de chocolate crocante com recheio cremoso de baunilha.",
            "Molho de Tomate Pomarola Tradicional 340 g|3.89|Molho pronto com tomates selecionados e temperos, prático para massas e pizzas.",
            "Creme de Avelã com Cacau Nutella 350 g|24.90|Creme de avelã com cacau para passar no pão, em tapioca ou usar em sobremesas.",
            "Suco Del Valle Uva 1 L|7.99|Néctar de uva pronto para beber, em caixinha de 1 litro.",
            "Achocolatado em Pó Nescau 400 g|8.99|Achocolatado em pó enriquecido com vitaminas e minerais, ótimo com leite gelado ou quente.",
    };

    private static final String[] LIVROS = {
            "Dom Casmurro|34.90|Clássico de Machado de Assis. Bentinho narra sua paixão por Capitu e a dúvida que muda sua vida.",
            "O Pequeno Príncipe|29.90|Obra de Antoine de Saint-Exupéry sobre amizade, amor e o olhar das crianças, lida em todo o mundo.",
            "Código Limpo (Clean Code)|119.90|De Robert C. Martin. Práticas e técnicas para escrever código legível, simples e fácil de manter.",
            "1984|39.90|Distopia de George Orwell sobre vigilância, controle da informação e perda da liberdade.",
            "Sapiens: Uma Breve História da Humanidade|59.90|De Yuval Noah Harari. Percorre a história da nossa espécie, da Pré-História aos dias atuais.",
            "O Hobbit|44.90|De J.R.R. Tolkien. A aventura de Bilbo Bolseiro ao lado de treze anões para recuperar um tesouro guardado por um dragão.",
            "Memórias Póstumas de Brás Cubas|32.90|Romance de Machado de Assis narrado por um defunto autor, com humor e crítica à sociedade do século XIX.",
            "A Revolução dos Bichos|27.90|Fábula política de George Orwell em que os animais de uma fazenda expulsam o dono e criam seu próprio governo.",
            "Hábitos Atômicos|54.90|De James Clear. Método prático para criar bons hábitos e abandonar os ruins com pequenas mudanças diárias.",
            "Java: Como Programar|189.90|De Paul Deitel e Harvey Deitel. Introdução completa à programação em Java, com exemplos e exercícios.",
            "Capitães da Areia|36.90|Romance de Jorge Amado sobre meninos de rua de Salvador, retrato social e poético da infância abandonada.",
            "O Alquimista|37.90|De Paulo Coelho. A jornada do pastor Santiago em busca do seu tesouro e da sua Lenda Pessoal.",
            "Grande Sertão: Veredas|69.90|Obra-prima de Guimarães Rosa em que o ex-jagunço Riobaldo conta sua vida no sertão mineiro.",
    };

    private static final String[] BRINQUEDOS = {
            "LEGO Classic Caixa Grande de Peças Criativas 10698|289.90|Caixa com 790 peças coloridas para construir livremente. Estimula criatividade e coordenação. Para maiores de 4 anos.",
            "Boneca Barbie Fashionista Mattel|89.90|Boneca articulada com roupas e acessórios da moda, perfeita para brincar de estilista.",
            "Carrinhos Hot Wheels Pacote com 5|34.90|Pacote com cinco carrinhos em escala 1:64 com modelos e cores sortidos.",
            "Quebra-Cabeça Grow 1000 Peças|79.90|Quebra-cabeça com 1000 peças de encaixe preciso e imagem em alta qualidade, para montar em família.",
            "Jogo Detetive Estrela|99.90|Clássico jogo de mistério e dedução em que os jogadores descobrem quem foi o culpado, com que arma e onde.",
            "Pelúcia Pikachu Pokémon 20 cm|109.90|Pelúcia macia do Pikachu, com acabamento detalhado e tecido agradável ao toque.",
            "Bola de Futebol de Campo Nike Pitch|129.99|Bola oficial tamanho 5 com costura reforçada e desenho de alta visibilidade, para jogar no campo.",
            "Massinha Play-Doh Kit com 10 Potes|49.90|Dez potes de massa de modelar atóxica em cores variadas, para criar e moldar à vontade.",
            "Jogo UNO Cartas Mattel|29.90|Jogo de cartas rápido e divertido para 2 a 10 jogadores, fácil de aprender e ótimo para a família.",
            "Jogo da Vida Hasbro|169.90|Jogo de tabuleiro em que cada jogador faz escolhas de carreira, família e dinheiro até a aposentadoria.",
            "Banco Imobiliário Estrela|139.90|Clássico jogo de tabuleiro de compra e venda de propriedades, com dinheiro de brinquedo e cartas.",
            "Lançador Nerf Elite 2.0 Commander|99.90|Lançador com tambor para 6 dardos e design tático, acompanha dardos Nerf Elite.",
            "Cubo Mágico Rubik's 3x3|49.90|Quebra-cabeça 3D clássico com seis faces coloridas, desafia raciocínio lógico e agilidade.",
    };

    private static final String[] CATEGORIAS = {"Eletrônicos", "Roupas", "Alimentos", "Livros", "Brinquedos"};

    @Bean
    CommandLineRunner popularProdutos(ProdutoRepository produtoRepository) {
        return args -> {
            // Versão antiga da carga (fotos "picsum" e nomes genéricos): troca pela nova.
            boolean cargaAntiga = produtoRepository.existsByImagemContaining("picsum.photos");
            if (cargaAntiga) {
                produtoRepository.deleteAll();
            } else if (produtoRepository.count() >= MINIMO) {
                return;
            }

            String[][] grupos = {ELETRONICOS, ROUPAS, ALIMENTOS, LIVROS, BRINQUEDOS};
            List<Produto> produtos = new ArrayList<>();
            int contador = 1;

            for (int g = 0; g < grupos.length; g++) {
                for (String linha : grupos[g]) {
                    String[] partes = linha.split("\\|");
                    produtos.add(new Produto(
                            partes[0],
                            new BigDecimal(partes[1]),
                            CATEGORIAS[g],
                            partes[2],
                            null,
                            10 + (contador * 7) % 90
                    ));
                    contador++;
                }
            }

            produtoRepository.saveAll(produtos);
        };
    }
}
