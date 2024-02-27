package principal;

import buscaminas.BuscaMinas;
import java.util.Scanner;

public class Interface {
    BuscaMinas buscaminas;
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Interface intf = new Interface();
        intf.iniciarJuego();
    }

    public void iniciarJuego() {
        buscaminas = new BuscaMinas();
        System.out.println("Bienvenido al buscaminas");
        System.out.println("Selecciona la dificultad");
        System.out.println("[P]rincipiante");
        System.out.println("[I]ntermedio");
        System.out.println("[E]xperto");
        char dificultad = sc.next().toUpperCase().charAt(0);
        if(dificultad != 'P' && dificultad != 'I' && dificultad != 'E'){
            System.out.println("Dificultad no valida");
            dificultad = sc.next().toUpperCase().charAt(0);
        }
        buscaminas.setDificultad(dificultad);
        System.out.println("Dificultad seleccionada: "+ buscaminas.getDificultad());
        System.out.println("Numero de minas: "+ buscaminas.getMinas());
        buscaminas.crearMapa();
        imprimirMapa();
        jugar();
    }

    public void jugar() {
        int columna;
        int fila;
        int n = 0;
        while (true) {
            System.out.println("Selecciona una fila");
            fila = sc.nextInt() - 1;
            System.out.println("Selecciona una columna");
            columna = sc.nextInt() - 1;
            System.out.println("¿Quieres [D]escubrir o [B]andera?");
            char accion = sc.next().toUpperCase().charAt(0);
            while (n==1) {
                imprimirMapa();
                n++;
            }
            
            if (accion == 'D') {
                buscaminas.descubrirCasilla(fila, columna);
                if (buscaminas.esMina(fila, columna)) {
                    System.out.println("Has perdido");
                    revelarMapa();
                    break;
                }
                buscaminas.ponerNumero();
            } else if (accion == 'B') { // Changed from 'P' to 'B'
                buscaminas.colocarBandera(fila, columna);
                buscaminas.minasRestantes();
            }
            imprimirMapa();
            if (buscaminas.victoria()){
                revelarMapa();
                System.out.println("Has ganado");
            }
        }
    }
    

    public void imprimirMapa() {
        char[][] mapa = buscaminas.getMapaBlanco();
        for (int i = 0; i < buscaminas.getAlto(); i++) {
            for (int j = 0; j < buscaminas.getAncho(); j++) {
                System.out.print("----");
            }
            System.out.println("-");
            for (int j = 0; j < buscaminas.getAncho(); j++) {
                System.out.print("| " + mapa[i][j] + " ");
            }
            System.out.println("|");
        }
        for (int j = 0; j < buscaminas.getAncho(); j++) {
            System.out.print("----");
        }
        System.out.println("-");
    }

    public void revelarMapa(){
        char[][] mapa = buscaminas.getMapa();
        for (int i = 0; i < buscaminas.getAlto(); i++) {
            for (int j = 0; j < buscaminas.getAncho(); j++) {
                System.out.print("----");
            }
            System.out.println("-");
            for (int j = 0; j < buscaminas.getAncho(); j++) {
                if(mapa[i][j] == '0'){
                    mapa[i][j] = ' ';
                }
                System.out.print("| " + mapa[i][j] + " ");
            }
            System.out.println("|");
        }
        for (int j = 0; j < buscaminas.getAncho(); j++) {
            System.out.print("----");
        }
        System.out.println("-");
    }
}
