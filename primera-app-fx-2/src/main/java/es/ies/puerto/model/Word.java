package es.ies.puerto.model;
import java.util.Objects;
/**
 * Clase que controla las palabras del juego con su nivel.
 * @author cdiagal
 * @version 1.0.0
 */


public class Word {

    private int id;
    private String texto;
    private int idNivel;
    private String nivel;

    /**
     * Constructor vacío de la clase.
     */
    public Word (){

    }

    /**
     * Constructor con el id de la palabra.
     * @param id de la palabra.
     */
    public Word(int id){
        this.id = id;
    }

    /**
     * Constructor con todos los objetos de la clase.
     * @param id de la palabra.
     * @param texto de la palabra.
     * @param idNivel de la palabra.
     * @param nivel de la palabra a usar.
     */
    public Word(int id, String texto, int idNivel, String nivel){
        this.id = id;
        this.texto = texto;
        this.idNivel = idNivel;
        this.nivel = nivel;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getIdNivel() {
        return this.idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public String getNivel() {
        return this.nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }


    @Override
    public String toString() {
        return "id: " + id + "texto: " + texto + "idNivel: " + idNivel + "nivel: " + nivel;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Word)) {
            return false;
        }
        Word word = (Word) o;
        return id == word.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
