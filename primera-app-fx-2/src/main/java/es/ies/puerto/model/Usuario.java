package es.ies.puerto.model;

import java.util.Objects;

/**
 * Clase Usuario que contiene las propiedades del usuario.
 * 
 * @author cdiagal
 * @version 1.0.0
 */

public class Usuario {
    private String usuarioNickName;
    private String contrasenia;
    private String nombre;
    private String email;
    private int puntos;
    private int idNivel;
    private String nivel;

    /**
     * Constructor vacio.
     * Constructor con las propiedades que tiene la tabla usuarios.
     */
    public Usuario(String usuarioNickName, String contrasenia, String email, int puntos, int idNivel){
        this.usuarioNickName = usuarioNickName;
        this.contrasenia = contrasenia;
        this.email = email;
        this.puntos = puntos;
        this.idNivel = idNivel;
    }

    /**
     * Constructor para registrar.
     * @param usuarioNickName
     * @param contrasenia
     * @param nombre
     * @param email
     */
    public Usuario(String usuarioNickName, String contrasenia, String nombre, String email){
        this.usuarioNickName = usuarioNickName;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.email = email;
        this.puntos = 0;
        this.idNivel = 1;
        this.obtenerNivel();
    }

    /**
     * Constructor que contiene el usuario.
     * @param usuario nickname unico del usuario.
     */
    public Usuario(String usuarioNickName){
        this.usuarioNickName = usuarioNickName;
    }

    /**
     * Constructor para mostrar la informacion del usuario en userData, despues del loguearse
     * @param usuarioNickName
     * @param email
     * @param idNivel
     */
    public Usuario(String usuarioNickName, String contrasenia, String email, int idNivel){
        this.usuarioNickName = usuarioNickName;
        this.contrasenia = contrasenia;
        this.email = email;
        this.idNivel = idNivel;
    }


    /**
     * Constructor con todas las propiedades del usuario.
     * @param usuario nickname unico del usuario.
     * @param contrasenia del usuario.
     * @param nombre del usuario.
     * @param email del usuario.
     */
    public Usuario(String usuarioNickName, String contrasenia, String nombre, String email, int puntos, int idNivel, String nivel){
        this.usuarioNickName  = usuarioNickName;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.email = email;
        this.puntos = puntos;
        this.idNivel = idNivel;
        this.nivel = nivel != null ? nivel : obtenerNivel();
        
    }

    /**
     * Getters y setters.
     * @return informacion de las propiedades de la clase.
     */
    public String getUsuarioNickName() {
        return this.usuarioNickName;
    }

    public void setUsuarioNickName(String usuarioNickName) {
        this.usuarioNickName = usuarioNickName;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNivel() {
        return nivel;
    }


    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }


    /**
     * Funcion que incrementa la cantidad de puntos.
     * @param cantidad
     */
    public void incrementarPuntos(int cantidad){
        this.puntos += cantidad;
        actualizarNivel();
    }

    /**
     * Funcion que actualiza el nivel en funcion a la cantidad de puntos que el usuario tenga acumulados.
     */
    public void actualizarNivel(){
        if(puntos < 25){
            nivel = "facil";
        } else if (puntos < 50) {
            nivel = "medio";
        } else {
            nivel = "dificil";
        }
    }

    /**
     * Metodo que obtiene nombre del nivel segun el valor atribuido en idNivel.
     */
    public String obtenerNivel(){
        String nivel ;
        switch (idNivel) {
            case 1:
                nivel = "facil";
                break;
            case 2:
                nivel = "medio";
                break;
            case 3:
                nivel = "dificil";
                break;
            default:
                nivel = "desconocido";
                break;
        }
        return nivel;
    }


    /**
     * Metodo toString().
     */
    @Override
    public String toString() {
        return "Nickname: " + usuarioNickName + "Contraseña: " +  contrasenia + "Nombre: " +  nombre +  "Email: " + email
                + "Puntos: " + puntos + "Id nivel: " + idNivel + "Nivel : " + nivel;
    }




    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(usuarioNickName, usuario.usuarioNickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioNickName);
    }
    
}


