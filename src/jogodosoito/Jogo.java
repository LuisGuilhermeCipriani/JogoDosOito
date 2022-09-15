
package jogodosoito;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Luis Guilherme Cipriani
 */
public class Jogo {

    //Representam os lados do tabuleiro: abaixo, esquerda, acima, direita
    int[] linha = {1, 0, -1, 0};
    int[] coluna = {0, -1, 0, 1};

    //Representa a dimensão da posição
    public int tamanho = 3;
    //Representa a quantidade de matrizes geradas até a configuração esperada
    static int configuracoes = 0;

    //Método para imprimir a matriz de dimensões 3x3
    public void imprimeMatriz(int[][] matriz) {
        for (int[] matriz1 : matriz) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(" | " + matriz1[j] + " | ");
            }
            System.out.println();
        }
    }

    //Método para imprimir o caminho percorrido do nó raiz ao nó de destino
    public void imprimeCaminho(No raiz) {
        if (raiz == null) {
            return;
        }
        imprimeCaminho(raiz.vizinho);
        System.out.println("\n Configuração " + configuracoes + ":");
        imprimeMatriz(raiz.matriz);
        configuracoes++;
    }

    //Função para calcular a quantidade de peças que estão em posições erradas, ou seja, diferente das esperadas na configuração final
    public int calculaCusto(int[][] configuracaoInicial, int[][] configuracaoFinal) {
        int contador = 0;
        int tam = configuracaoInicial.length;
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (configuracaoInicial[i][j] != 0 && configuracaoInicial[i][j] 
                        != configuracaoFinal[i][j]) {
                    contador++;
                }
            }
        }
        return contador;
    }
    
    //Função para verificar se as posições x e y formam uma coordenada válida da matriz
    public boolean ehSeguro(int x, int y) {
        return (x >= 0 && x < tamanho && y >= 0 && y < tamanho);
    }

    //Método para resolver o puzzle, onde x e y representam coordenadas de espaços vazios em estado inicial
    public void solucionaJogo(int[][] configuracaoInicial, int[][] configuracaoFinal, 
            int x, int y) {
        
        //Cria uma fila de prioridade para armazenar nós ativos da árvore de pesquisa
        PriorityQueue<No> fila = new PriorityQueue<>(1000, (a, b) -> 
                (a.custo + a.nivel) - (b.custo + b.nivel));
        No raiz = new No(configuracaoInicial, x, y, x, y, null, 0);
        raiz.custo = calculaCusto(configuracaoInicial, configuracaoFinal);
        //Adiciona a raiz na lista de nós ativos;
        fila.add(raiz);

        //Encontra um nó com menor custo, adiciona seus filhos à lista de nós e por fim o exclui.
        while (!fila.isEmpty()) {
            //O nó ativo encontrado é excluído da lista
            No minimo = fila.poll();
            if (minimo.custo == 0) {
                //imprime o caminho da raiz ao destino;
                imprimeCaminho(minimo);
                return;
            }

            for (int i = 0; i < 4; i++) {
                if (ehSeguro(minimo.coordX + linha[i], minimo.coordY + coluna[i])) {
                    //cria um nó filho e calcula seu custo
                    No filho = new No(minimo.matriz, minimo.coordX, 
                            minimo.coordY, minimo.coordX + linha[i], 
                            minimo.coordY + coluna[i], minimo, minimo.nivel + 1);
                    filho.custo = calculaCusto(filho.matriz, configuracaoFinal);
                    //Adiciona filho à lista de nós ativos
                    fila.add(filho);
                }
            }
        }
    }
    
    //Função para verificar se a matriz corresponde a uma configuração possível de ser solucionada
    public boolean ehSolucionavel(int[][] matriz) {
		int contador = 0;
		List<Integer> lista = new ArrayList<>();
		
        for (int[] matriz1 : matriz) {
            for (int j = 0; j < matriz.length; j++) {
                lista.add(matriz1[j]);
            }
        }
		
		Integer[] lista2 = new Integer[lista.size()];
		lista.toArray(lista2);
		
		for (int i = 0; i < lista2.length - 1; i++) {
			for (int j = i + 1; j < lista2.length; j++) {
				if (lista2[i] > lista2[j] && lista2[i] != 0 && 
                                        lista2[j] != 0) {
					contador++;
				}
			}
		}
		
		return contador % 2 == 0;
	}
}
