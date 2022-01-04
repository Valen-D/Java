package Proyecto_Final2;

public class ControladorDelJuego {

    private final LogicaDelJuego juego;

    {
        juego = new LogicaDelJuego();
    }

    public ControladorDelJuego(){
    }

    public void iniciarAplicacion(){
        //Bucle infinito
        while (true) {
            int opcion = EntradaSalida.leerOpcionDelMenu(" 1 - Jugar vs PC\n 2 - Jugar de a 2\n 3 - Salir");
            switch (opcion) {
                case 1:
                    iniciarJuegoDe2Jugadores();
                    break;
                case 2:
                    iniciarJuegoContraLaComputadora();
                    break;
                case 3:
                    salir();
                    break;
            }
        }
    }

    public void iniciarJuegoDe2Jugadores(){
        this.juego.iniciarJuego(1);
    }

    public void iniciarJuegoContraLaComputadora(){
        this.juego.iniciarJuego(2);
    }

    public void salir(){
        System.exit(0);
    }
}
