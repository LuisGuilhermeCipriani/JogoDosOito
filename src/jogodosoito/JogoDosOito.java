package jogodosoito;

/**
 *
 * @author Luis Guilherme Cipriani
 */
public class JogoDosOito {

    public static void main(String[] args) {
        
        //Coordenadas do puzzle com espaço em branco na configuração inicial
        int coordX = 1, coordY = 0;
        
        //Configuração inicial, onde o valor 0 representa um espaço vazio
        int[][] configuracaoInicial = {{1, 8, 2}, {0, 4, 3}, {7, 6, 5}};
        //Configuração final, ou seja, a matriz que se deseja alcançar, onde o valor 0 representa um espaço vazio
        int[][] configuracaoFinal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        Jogo jogo = new Jogo();
        if (jogo.ehSolucionavel(configuracaoInicial)) {
            jogo.solucionaJogo(configuracaoInicial, configuracaoFinal, coordX, coordY);
            System.out.println("\nPuzzle organizado! Foram apresentadas " + 
                    Jogo.configuracoes + " configurações no total para que o objetivo fosse alcançado");
        } else {
            System.out.println("Não há como solucionar a configuração inicial");
        }
    }

}
