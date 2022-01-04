package Proyecto_Final2;

public class Jugador {

    private String nombreDelJugador;
    private int turnosRestantes;
    private int fichaDelJugador;
    private boolean arrancaPrimero;

    public Jugador() {

    }

    public void determinarTurno(){
        this.setArrancaPrimero(this.turnosRestantes == 5);
    }

    public boolean getArrancaPrimero() {
        return arrancaPrimero;
    }

    public void setArrancaPrimero(boolean arrancaPrimero) {
        this.arrancaPrimero = arrancaPrimero;
    }

    public boolean seAcabaronLosTurnos() {
        return turnosRestantes != 0;
    }

    public String getNombreDelJugador() {
        return nombreDelJugador;
    }

    public void setNombreDelJugador(String nombreDelJugador) {
        this.nombreDelJugador = nombreDelJugador;
    }

    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    public void setTurnosRestantes(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }

    public int getFichaDelJugador() {
        return fichaDelJugador;
    }

    public void setFichaDelJugador(int fichaDelJugador) {
        this.fichaDelJugador = fichaDelJugador;
    }

    public void resetearAtributosDelJugador(){
        this.fichaDelJugador = 0;
        this.nombreDelJugador = "";
        this.turnosRestantes = 0;
    }

    public void juegaLaPC(Tablero tablero){
        boolean laCoordenadaNoFueValida = true;
        if(this.seAcabaronLosTurnos()){
            do{
                int numeroDeCasillero = (int) (Math.random() * 9 + 1);
                int fila = 0, columna = 0;
                switch(numeroDeCasillero)
                {
                    case(1): fila = 0; columna = 0 ; break;
                    case(2): fila = 0; columna = 1 ; break;
                    case(3): fila = 0; columna = 2 ; break;
                    case(4): fila = 1; columna = 0 ; break;
                    case(5): fila = 1; columna = 1 ; break;
                    case(6): fila = 1; columna = 2 ; break;
                    case(7): fila = 2; columna = 0 ; break;
                    case(8): fila = 2; columna = 1 ; break;
                    case(9): fila = 2; columna = 2 ; break;
                }
                if(tablero.coordenadaIngresadaEsValida(fila, columna)){
                    tablero.cambiarValorSegunCoordenada(fila, columna, this.fichaDelJugador);
                    laCoordenadaNoFueValida = false;
                }
            }while(laCoordenadaNoFueValida);
        }
        else{
            EntradaSalida.mostrarMensajeDeError("No le quedan movimientos a la Computadora");
        }
        EntradaSalida.mostrarCadena(this.getNombreDelJugador() + " realizó jugada: \n" + tablero.devolverTableroEnString());
    }

    public void restarTurno(){
        this.setTurnosRestantes(this.getTurnosRestantes() - 1);
    }

    public void jugarJugador(Tablero tablero){
        boolean laCoordenadaNoFueValida = true;
        int filaIngresada;
        int columnaIngresada;

        if(this.seAcabaronLosTurnos()){
            do{
                String leerCoordenadaIngresada = EntradaSalida.leerCadena("Juega: " + this.getNombreDelJugador() + "\n" +
                        "Le quedan: " + this.getTurnosRestantes() + " turnos "+"\n" +
                        tablero.devolverTableroEnString() + "\nIngrese Fila,guion,Columna : ");
                try{
                    filaIngresada = this.extraerFila(leerCoordenadaIngresada);
                    columnaIngresada = this.extraerColumna(leerCoordenadaIngresada);
                    if(tablero.coordenadaIngresadaEsValida(filaIngresada, columnaIngresada)){
                        tablero.cambiarValorSegunCoordenada(filaIngresada, columnaIngresada, this.fichaDelJugador);
                        this.restarTurno();
                        laCoordenadaNoFueValida = false;
                    }
                }
                catch(Exception e){
                    EntradaSalida.mostrarMensajeDeError("Error de ingreso de coordenada, intente nuevamente");
                }
            }while(laCoordenadaNoFueValida);
        }
        else{
            EntradaSalida.mostrarMensajeDeError("Se acabaron los turnos a: " + this.getNombreDelJugador());
        }
    }

    public boolean esValidaLaCoordenada(String coordenada){
        if(coordenada.length() == 3 && coordenada.indexOf("-") == 1){
            return (coordenada.charAt(0) == '0' || coordenada.charAt(0) == '1' || coordenada.charAt(0) == '2') &&
                    (coordenada.charAt(2) == '0' || coordenada.charAt(2) == '1' || coordenada.charAt(2) == '2');
        }
        return false;
    }

    public int extraerFila(String coordenada){
        if(esValidaLaCoordenada(coordenada)){
            return coordenada.charAt(0) - 48;
        }
        return -1;
    }

    public int extraerColumna(String coordenada){
        if(esValidaLaCoordenada(coordenada)){
            return coordenada.charAt(2) - 48;
        }
        return -1;
    }



}
