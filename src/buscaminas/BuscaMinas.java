package buscaminas;

/**
 * Representa la lógica para un juego de Buscaminas.
 * 
 * @author Xacobo
 */
public class BuscaMinas {
    private char[][] mapa;
    private char[][] mapaBlanco;
    private int alto;
    private int ancho;
    private int minas;
    private char dificultad;


    /**
     * Crea el mapa del juego basado en la configuración actual.
     */
    public void crearMapa(){
        alto = getAlto();
        ancho = getAncho();
        mapa = new char[getAlto()][getAncho()];
        mapaBlanco = new char[getAlto()][getAncho()];

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = 'X';
            }
        }
        colocarMinas();
        colocarNumero();
        for (int i = 0; i < mapaBlanco.length; i++) {
            for (int j = 0; j < mapaBlanco[i].length; j++) {
                mapaBlanco[i][j] = 'X';
            }
        }
    }

    /**
     * Establece el nivel de dificultad del juego.
     * @param dificultad El nivel de dificultad: 'P' para Principiante(8x8 y 10 minas), 'I' para Intermedio(16x16 y 40 minas), 'E' para Experto(31x16 y 99 minas).
     */
    public void setDificultad(char dificultad){
        switch (dificultad) {
            case 'P':
                setAlto(8);
                setAncho(8);
                setMinas(10);
                break;
            case 'I':
                setAlto(16);
                setAncho(16);
                setMinas(40);
                break;
            case 'E':
                setAlto(16);
                setAncho(31);
                setMinas(99);
                break;
            default:
                break;
        }
    }
     /**
     * Obtiene el nivel de dificultad del juego.
     * @return El nivel de dificultad como cadena de texto.
     */
    public String getDificultad(){
        if(dificultad == 'P'){
            return "Principiante";
        }
        else if(dificultad == 'I'){
            return "Intermedio";
        }
        else if(dificultad == 'E'){
            return "Experto";
        }
        else{
            return "Dificultad no seleccionada";
        }
    }

    /**
     * Obtiene una copia del mapa del juego.
     * @return Una copia del mapa del juego.
     */
    public char[][] getMapa(){
        char[][] copiaMapa = mapa.clone();
        return copiaMapa;
    }

    /**
     * Obtiene una copia del mapa con las minas ocultas.
     * @return Una copia del mapa con las minas ocultas.
     */
    public char[][] getMapaBlanco(){
        char[][] copiaMapaBlanco = mapaBlanco.clone();
        return copiaMapaBlanco;
    }

    /**
     * Establece la altura del tablero.
     * @param alto La altura del tablero.
     */
    private void setAlto(int alto) {
        this.alto = alto;
    }
    /**
     * Obtiene la altura del tablero.
     * @return La altura del tablero.
     */
    public int getAlto(){
        return alto;
    }

    /**
     * Establece el ancho del tablero.
     * @param ancho El ancho del tablero.
     */
    private void setAncho(int ancho) {
        this.ancho = ancho;
    }
    /**
     * Obtiene el ancho del tablero.
     * @return El ancho del tablero.
     */
    public int getAncho(){
        return ancho;
    }
    /**
     * Establece el número de minas en el tablero.
     * @param minas El número de minas.
     */
    private void setMinas(int minas) {
        this.minas = minas;
    }
    /**
     * Obtiene el número de minas en el tablero.
     * @return El número de minas en el tablero.
     */
    public int getMinas(){
        return minas;
    }

    /**
     * Coloca las minas en posiciones aleatorias en el tablero.
     */
    private void colocarMinas(){
        int minasColocadas = 0;
        while(minasColocadas < getMinas()){
            int fila = (int)(Math.random() * getAlto());
            int columna = (int)(Math.random() * getAncho());
            if(mapa[fila][columna] != '*'){
                mapa[fila][columna] = '*';
                minasColocadas++;
            }
        }
    }

    /**
     * Verifica si una posición en el tablero es una mina.
     * @param fila La fila de la posición.
     * @param columna La columna de la posición.
     * @return true si la posición contiene una mina, false de lo contrario.
     */
    public boolean esMina(int fila, int columna){
        try {
            return mapa[fila][columna] == '*';
        }catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Coloca los números que indican la cantidad de minas adyacentes a cada casilla vacía.
     */
    private void colocarNumero(){
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] != '*'){
                    int minasAlrededor = contarMinasAlrededor(i, j);
                    mapa[i][j] = (char)(minasAlrededor + '0');
                }
            }
        }
    }

     /**
     * Cuenta el número de minas adyacentes a una casilla en particular.
     * @param fila La fila de la casilla.
     * @param columna La columna de la casilla.
     * @return El número de minas adyacentes a la casilla.
     */
    private char contarMinasAlrededor(int fila, int columna){
        int minasAlrededor = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < getAlto() && j >= 0 && j < getAncho() && mapa[i][j] == '*') {
                    minasAlrededor++;
                }
            }
        }
        return (char) minasAlrededor;
    }

    /**
     * Coloca banderas en la posición indicada por el jugador.
     * @param fila La fila donde se coloca la bandera.
     * @param columna La columna donde se coloca la bandera.
     */
    public void colocarBandera(int fila, int columna){
        try {
            if (mapaBlanco[fila][columna] == 'X'){
                mapaBlanco[fila][columna] = 'P';
            } else if (mapaBlanco[fila][columna] == 'P'){
                mapaBlanco[fila][columna] = 'X';
            }
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Coordenadas fuera de rango");
        }
    }

     /**
     * Descubre una casilla del tablero, revelando si hay una mina o el número de minas adyacentes.
     * @param fila La fila de la casilla a descubrir.
     * @param columna La columna de la casilla a descubrir.
     */
    public void descubrirCasilla(int fila, int columna){
        try {
            if (mapaBlanco[fila][columna] == 'X'){
                if (esMina(fila, columna)){
                    mapa[fila][columna] = '*';
                } else {
                    mapaBlanco[fila][columna] = ' ';
                    if (contarMinasAlrededor(fila, columna) == 0){
                        descubrirCasillasAlrededor(fila, columna);
                    }
                }
            }
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Coordenadas fuera de rango");
        }
    }

    /**
     * Coloca los números en el tablero que indican la cantidad de minas adyacentes a cada casilla vacía.
     */
    public void ponerNumero(){
        for(int i = 0; i < getAlto(); i++){
            for(int j = 0; j < getAncho(); j++){
                if(mapaBlanco[i][j] == ' '){
                    if(mapa[i][j] == '0'){
                        mapaBlanco[i][j] = ' ';
                    }
                    else{
                        mapaBlanco[i][j] = mapa[i][j];
                    }
                }
            }
        }
    }
    /**
     * Descubre las casillas adyacentes a una casilla vacía (sin minas adyacentes).
     * @param fila La fila de la casilla a descubrir.
     * @param columna La columna de la casilla a descubrir.
     */
    private void descubrirCasillasAlrededor(int fila, int columna){
            for (int i = fila - 1; i <= fila + 1; i++) {
                for (int j = columna - 1; j <= columna + 1; j++) {
                    if (i >= 0 && i < getAlto() && j >= 0 && j < getAncho()){
                        descubrirCasilla(i, j);
                    }
                    
                }
            }

        
    }

    /**
     * Revela la posición de las minas en el mapa.
     */
    public void revelarMapa(){
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == '*'){
                    mapaBlanco[i][j] = '*';
                }
            }
        }
    }

    /**
     * Verifica si colocar banderas marca correctamente todas las minas.
     * @return Verdadero si todas las minas están correctamente marcadas con banderas, falso en caso contrario.
     */
    private boolean comprobarBanderasVictoria(){
        int banderasCorrectas = 0;
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == 'P' && esMina(i, j)){
                    banderasCorrectas++;
                }
            }
        }
        return banderasCorrectas == getMinas();
    }

    /**
     * Verifica si el jugador ha ganado el juego.
     * @return Verdadero si el jugador ha ganado, falso en caso contrario.
     */
    private boolean comprobarVictoria(){
        int casillasDescubiertas = 0;
        for (int i = 0; i < getAlto(); i++) {
            for (int j = 0; j < getAncho(); j++) {
                if (mapaBlanco[i][j] != 'X' && mapaBlanco[i][j] != 'P'){
                    casillasDescubiertas++;
                }
            }
        }
        if (casillasDescubiertas == (getAlto()*getAncho()) - getMinas()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Verifica si el jugador ha ganado o perdido el juego.
     * @return Verdadero si el jugador ha ganado o perdido, falso en caso contrario.
     */
    public boolean victoria(){
        if(comprobarVictoria() || comprobarBanderasVictoria()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Imprime el número de minas restantes por marcar con bandera.
     */
    public void minasRestantes(){
        int minasRestantes = getMinas();
        for (int i = 0; i < mapaBlanco.length; i++) {
            for (int j = 0; j < mapaBlanco[i].length; j++) {
                if (mapaBlanco[i][j] == 'P'){
                    minasRestantes--;
                }
            }
        }
        System.out.println("Minas restantes: " + minasRestantes);
    }
}