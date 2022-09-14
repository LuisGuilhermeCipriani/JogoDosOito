/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodosoito;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import javafx.scene.Node;

/**
 *
 * @author cipri
 */
public class Jogo {

    int[] linha = {1, 0, -1, 0};
    int[] coluna = {0, -1, 0, 1};

    public int tamanho = 3;

    public void imprimeMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void imprimeCaminho(No raiz) {
        if (raiz == null) {
            return;
        }
        imprimeCaminho(raiz.vizinho);
        imprimeMatriz(raiz.matriz);
        System.out.println();
    }

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
    
    public boolean ehSeguro(int x, int y) {
        return (x >= 0 && x < tamanho && y >= 0 && y < tamanho);
    }

    public void solucionaJogo(int[][] configuracaoInicial, int[][] configuracaoFinal, 
            int x, int y) {
        PriorityQueue<No> fila = new PriorityQueue<No>(1000, (a, b) -> 
                (a.custo + a.nivel) - (b.custo + b.nivel));
        No raiz = new No(configuracaoInicial, x, y, x, y, null, 0);
        raiz.custo = calculaCusto(configuracaoInicial, configuracaoFinal);
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No minimo = fila.poll();
            if (minimo.custo == 0) {
                imprimeCaminho(minimo);
                return;
            }

            for (int i = 0; i < 4; i++) {
                if (ehSeguro(minimo.coordX + linha[i], minimo.coordY + coluna[i])) {
                    No filho = new No(minimo.matriz, minimo.coordX, 
                            minimo.coordY, minimo.coordX + linha[i], 
                            minimo.coordY + coluna[i], minimo, minimo.nivel + 1);
                    filho.custo = calculaCusto(filho.matriz, configuracaoFinal);
                    fila.add(filho);
                }
            }
        }
    }
    
    public boolean ehSolucionavel(int[][] matriz) {
		int contador = 0;
		List<Integer> lista = new ArrayList<Integer>();
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				lista.add(matriz[i][j]);
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
