package jogodosoito;

/**
 *
 * @author Luis Guilherme Cipriani
 */
public class No {

    public No vizinho;

    int matriz[][] = new int[3][3];

    public int coordX, coordY;

    public int custo;

    public int nivel;

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
