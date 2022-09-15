package jogodosoito;

/**
 *
 * @author Luis Guilherme Cipriani
 */
public class No {

    //Peça alocada no espaço vizinho
    public No vizinho;

    //Matriz 3x3 representando os nove espaços do tabuleiro
    int matriz[][] = new int[3][3];

    //Coordenadas para o espaço em branco
    public int coordX, coordY;

    //Quantidade de peças colocadas em posições erradas
    public int custo;

    //Número de movimentos realizados
    public int nivel;

    //Método construtor da classe No, com inicialização dos atributos.
    public No(int matriz[][], int coordX, int coordY, int novaCoordX, int novaCoordY, No vizinho, int nivel) {

        this.custo = Integer.MAX_VALUE;
        this.nivel = nivel;
        this.coordX = novaCoordX;
        this.coordY = novaCoordY;
        this.vizinho = vizinho;

        this.matriz = new int[matriz.length][];
        for (int i = 0; i < matriz.length; i++) {
            this.matriz[i] = matriz[i].clone();
        }

        this.matriz[coordX][coordY] = this.matriz[coordX][coordY] + this.matriz[novaCoordX][novaCoordY];
        this.matriz[novaCoordX][novaCoordY] = this.matriz[coordX][coordY] - this.matriz[novaCoordX][novaCoordY];
        this.matriz[coordX][coordY] = this.matriz[coordX][coordY] - this.matriz[novaCoordX][novaCoordY];
    }
}
