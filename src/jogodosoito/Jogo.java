/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodosoito;

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
				if (configuracaoInicial[i][j] != 0 && configuracaoInicial[i][j] != configuracaoFinal[i][j]) {
					contador++;
				}
			}
		}
		return contador;
	}
}
