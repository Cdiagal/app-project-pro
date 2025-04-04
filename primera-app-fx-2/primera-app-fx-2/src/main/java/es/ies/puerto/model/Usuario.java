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

    /**
     * Constructor vacio.
     */
    public Usuario(){}

    /**
     * Constructor que contiene el usuario.
     * @param usuario nickname unico del usuario.
     */
    public Usuario(String usuarioNickName){
        this.usuarioNickName = usuarioNickName;
    }


    /**
     * Constructor con todas las propiedades del usuario.
     * @param usuario nickname unico del usuario.
     * @param contrasenia del usuario.
     * @param nombre del usuario.
     * @param email del usuario.
     */
    public Usuario(String usuarioNickName, String contrasenia, String nombre, String email){
        this.usuarioNickName  = usuarioNickName;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.email = email;
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

    /**
     * Metodo toString().
     */
    @Override
    public String toString() {
        return "Nickname: " + usuarioNickName + "Contraseña: " +  contrasenia + "Nombre: " +  nombre +  "Email: " + email;
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
