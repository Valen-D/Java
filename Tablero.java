package Proyecto_Final2;

public class Tablero {

    private final int [][] tablero;
    public Tablero() {
        tablero = new int[3][3];
        inicializarTablero();
    }

    public void inicializarTablero(){
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.tablero[i][j] = 0;
            }
        }
    }

    public String devolverTableroEnString(){
        StringBuilder dibujoTablero = new StringBuilder("      |C0 |C1 |C2 \n");
        for(int i=0;i < 3; i++){
            dibujoTablero.append("F").append(i).append(" |  ").append(this.tablero[i][0]).append("  |  ").append(this.tablero[i][1]).append("  |  ").append(this.tablero[i][2]).append("  |\n");
        }
        return dibujoTablero.toString();
    }

    public void cambiarValorSegunCoordenada(int fila, int columna, int valorDelJugador){
        this.tablero[fila][columna] = valorDelJugador;
    }

    public boolean coordenadaIngresadaEsValida(int fila, int columna){
        if((fila >= 0 && fila < 3) && (columna >= 0 && columna < 3)){
            return coordenadaEstaVacia(fila,columna);
        }
        return false;
    }

    public boolean coordenadaEstaVacia(int fila, int columna){
        return this.tablero[fila][columna] == 0;
    }

    public boolean terminoElJuego(){
        return this.devolverFichaGanadoraPorColumna()!=0 || this.devolverFichaGanadoraPorDiagonal()!=0
                || this.devolverFichaGanadoraPorFila()!=0;
    }

    public int determinarJugadorGanador(){
        if(this.devolverFichaGanadoraPorColumna()!=0){
            return this.devolverFichaGanadoraPorColumna();
        }
        if(this.devolverFichaGanadoraPorDiagonal()!=0){
            return this.devolverFichaGanadoraPorDiagonal();
        }
        if(this.devolverFichaGanadoraPorFila()!=0){
            return this.devolverFichaGanadoraPorFila();
        }
        return 0;
    }

    public void resetearTablero(){
        this.inicializarTablero();
    }

    public int devolverFichaGanadoraPorColumna(){
        int ficha;
        for(int i = 0; i < 3; i++){
            ficha = this.tablero[0][i];
            if(ficha == this.tablero[1][i] && ficha == this.tablero[2][i] && ficha!=0){
                return ficha;
            }
        }
        return 0;
    }

    public int devolverFichaGanadoraPorFila(){
        int ficha;
        for(int i = 0; i < 3; i++){
            ficha = this.tablero[i][0];
            if(ficha == this.tablero[i][1] && ficha == this.tablero[i][2] && ficha!=0){
                return ficha;
            }
        }
        return 0;
    }

    public int devolverFichaGanadoraPorDiagonal(){
        int ficha = this.tablero[0][0];
        if(ficha == this.tablero[1][1] && ficha == this.tablero[2][2] && ficha!=0){
            return ficha;
        }
        else{
            ficha = this.tablero[0][2];
            if(ficha == this.tablero[1][1] && ficha == this.tablero[2][0] && ficha!=0){
                return ficha;
            }
        }
        return 0;
    }
}
