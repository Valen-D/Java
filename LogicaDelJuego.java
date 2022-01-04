package Proyecto_Final2;

public class LogicaDelJuego {
    private final Tablero tablero;
    private final Jugador jugador1;
    private final Jugador jugador2;

    public LogicaDelJuego() {
        tablero = new Tablero();
        jugador1 = new Jugador();
        jugador2 = new Jugador();
    }

    public void pedirIngresoDelJugador(Jugador jugador, int nroDeJugador){
        String nombre = EntradaSalida.leerCadena("Ingrese el nombre del Jugador "+ nroDeJugador + ": ");
        jugador.setNombreDelJugador(nombre);
    }

    public void determinarTurnoDelJugador(){
        double numero = (Math.random() * 1);
        if(numero > 0.5){
            this.jugador1.setTurnosRestantes(5);
            this.jugador2.setTurnosRestantes(4);
        }else{
            this.jugador1.setTurnosRestantes(4);
            this.jugador2.setTurnosRestantes(5);
        }
    }

    public void setearFichasDeLosJugadores(){
        this.jugador1.setFichaDelJugador(1);
        this.jugador2.setFichaDelJugador(2);
    }

    public void iniciarJuego(int modoDeJuego){
        this.determinarTurnoDelJugador();
        this.setearFichasDeLosJugadores();
        if(modoDeJuego == 2){
            this.pedirIngresoDelJugador(this.jugador1,1);
            this.pedirIngresoDelJugador(this.jugador2,2);
        }
        else{
            this.pedirIngresoDelJugador(this.jugador1,1);
            this.jugador2.setNombreDelJugador("COMPUTADORA");
        }
        this.jugador1.determinarTurno();
        this.jugador2.determinarTurno();


        while((this.jugador1.seAcabaronLosTurnos() || this.jugador2.seAcabaronLosTurnos()) && this.hayAlgunJugadorGanador()){
            if(modoDeJuego == 2){
                if(this.jugador1.seAcabaronLosTurnos() && this.hayAlgunJugadorGanador() && !this.jugador2.getArrancaPrimero()){
                    this.jugador1.jugarJugador(tablero);
                    if(this.jugador1.getArrancaPrimero()){
                        this.jugador1.setArrancaPrimero(false);
                    }
                }
                if(this.jugador2.seAcabaronLosTurnos() && this.hayAlgunJugadorGanador() && !this.jugador1.getArrancaPrimero()){
                    this.jugador2.jugarJugador(tablero);
                    if(this.jugador2.getArrancaPrimero()){
                        this.jugador2.setArrancaPrimero(false);
                    }
                }
            }
            else{
                if(this.jugador1.seAcabaronLosTurnos() && this.hayAlgunJugadorGanador() && !this.jugador2.getArrancaPrimero()){
                    this.jugador1.jugarJugador(tablero);
                    if(this.jugador1.getArrancaPrimero()){
                        this.jugador1.setArrancaPrimero(false);
                    }
                }
                if(this.jugador2.seAcabaronLosTurnos() && this.hayAlgunJugadorGanador() && !this.jugador1.getArrancaPrimero()){
                    this.jugador2.juegaLaPC(tablero);
                    if(this.jugador2.getArrancaPrimero()){
                        this.jugador2.setArrancaPrimero(false);
                    }
                }
            }
        }
        EntradaSalida.mostrarCadena(this.tablero.devolverTableroEnString()
                + this.devolverStringDelGanador(this.determinarGanador()));
        this.jugador1.resetearAtributosDelJugador();
        this.jugador2.resetearAtributosDelJugador();
        this.tablero.resetearTablero();
    }

    public int determinarGanador(){
        return this.tablero.determinarJugadorGanador();
    }

    public String devolverStringDelGanador(int nroDeFichaGanadora){
        String nombreDelGanador = "EMPATARON!!!";
        if(nroDeFichaGanadora == this.jugador1.getFichaDelJugador()){
            nombreDelGanador = "El ganador fue: " + this.jugador1.getNombreDelJugador();
        }
        else{
            if(nroDeFichaGanadora == this.jugador2.getFichaDelJugador()){
                nombreDelGanador = "El ganador fue: " + this.jugador2.getNombreDelJugador();
            }
        }
        return nombreDelGanador;
    }

    public boolean hayAlgunJugadorGanador(){
        return !this.tablero.terminoElJuego();
    }

}
