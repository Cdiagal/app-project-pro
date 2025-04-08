package es.ies.puerto.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class Game {
    private Usuario usuario;
    private Word palabra;
    private char[] palabraOculta;
    private Set<Character> letrasUsadas = new HashSet<>();
    private int intentosRestantes;
    private final int ERRORES_MAXIMOS=6;
    private final Random random = new Random();

    /**
     * Constructor vacio.
     */
    public Game(){
    }

    /**
     * Constructor con el usuario como clave unica.
     * @param usuario
     */
    public Game(Usuario usuario){
        this.usuario = usuario;
    }

    /**
     * Constructor con usuario y palabra.
     * El resto de parametros no son externos.
     * @param usuario que juega.
     * @param palabra que se usa.
     */
    public Game(Usuario usuario, Word palabra){
        this.usuario = usuario;
        this.palabra = palabra;
        this.letrasUsadas = new HashSet<>();
        inicializarPalabra();
        this.intentosRestantes = ERRORES_MAXIMOS;
    }


    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Word getPalabra() {
        return this.palabra;
    }

    public void setPalabra(Word palabra) {
        this.palabra = palabra;
    }

    public char[] getPalabraOculta() {
        return this.palabraOculta;
    }

    public void setPalabraOculta(char[] palabraOculta) {
        this.palabraOculta = palabraOculta;
    }

    public Set<Character> getLetrasUsadas() {
        return this.letrasUsadas;
    }

    public void setLetrasUsadas(Set<Character> letrasUsadas) {
        this.letrasUsadas = letrasUsadas;
    }

    public int getIntentosRestantes() {
        return this.intentosRestantes;
    }

    public void setIntentosRestantes(int intentosRestantes) {
        this.intentosRestantes = intentosRestantes;
    }

    public int getERRORES_MAXIMOS() {
        return this.ERRORES_MAXIMOS;
    }


    @Override
    public String toString() {
        return "{" +
            " usuario='" + getUsuario() + "'" +
            ", palabra='" + getPalabra() + "'" +
            ", palabraOculta='" + getPalabraOculta() + "'" +
            ", letrasUsadas='" + getLetrasUsadas() + "'" +
            ", intentosRestantes='" + getIntentosRestantes() + "'" +
            ", MAX_ERRORES='" + getERRORES_MAXIMOS() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return Objects.equals(usuario, game.usuario) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario);
    }

    public void inicializarPalabra(){
        String texto = palabra.getTexto().toLowerCase();
        palabraOculta = texto.toCharArray();

        int parteVisible = switch (palabra.getNivel().toLowerCase()){
            case "facil" -> 40;
            case "medio" -> 20;
            case "dificil" -> 10;
            default -> 10;
        };

        
        for (int i = 0; i < palabraOculta.length; i++) {
            if(random.nextInt(100) >= parteVisible){
                palabraOculta[i] = '_';
            }
        }
    }


    /**
     * Metodo que revisa si se acierta letras en la palabra y modifica los intentos restantes.
     * @param letra de la palabra.
     * @return acierto.
     */
    public boolean adivinarLetra(char letra){
        letra = Character.toLowerCase(letra);
        if(letrasUsadas.contains(letra)){
            return false;
        }
        letrasUsadas.add(letra);

        boolean acierto = false;

        for (int i = 0; i < palabra.getTexto().length(); i++) {
            if(palabra.getTexto().toLowerCase().charAt(i) == letra){
                palabraOculta[i]=letra;
                acierto = true;
            }
        }
        if(!acierto){
            intentosRestantes--;
        }
        return acierto;
    }

    /**
     * Metodo que comprueba si el usuario ha ganado la partida
     * @return ganar la partida.
     */
    public boolean hasGanado(){
        for (char letra : palabraOculta) {
            if(letra == '_'){
                return false;
            }
        }
        System.out.println("¡Enhorabuena! ¡Has ganado la partida!");
        return true;
    }

    /**
     * Metodo que comprueba si el usuario ha perdido la partida y muestra la palabra.
     * @return perder la partida.
     */
    public boolean hasPerdido(){
        if(intentosRestantes <= 0){
            System.out.println("¡Has perdido la partida! :(, la palabra era: " + palabra.getTexto());
            return true;
        }
        return false;
    }

}
