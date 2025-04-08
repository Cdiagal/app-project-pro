package es.ies.puerto.model.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Usuario;

/**
 * Clase que manipula los usuarios en funcion de lo que se necesite trabajar con los usuarios. Tiene implementado un CRUD.
 *
 * @author cdiagal
 * @version  1.0.0
 */

public class UsuarioServiceDB extends AbstractController{
    
    public UsuarioServiceDB(){
        super();
        
    }
    /**
     * Metodo que lee el fichero.
     * @return Lista con usuarios.
     */
    public List<Usuario> loadFile(){
        List<Usuario> usuarios = new ArrayList<>();
        try {
        String sql = "SELECT * from usuarios";
        conectar();
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        ResultSet cursor = preparedStatement.executeQuery();
        while (cursor.next()) {
            String usuarioNickName = cursor.getString("user");
            String email = cursor.getString("email");
            String contrasenia = cursor.getString("password");
            int puntos = cursor.getInt("puntos");
            int idNivel = cursor.getInt("id_nivel");
            Usuario usuario = new Usuario(usuarioNickName, email, contrasenia, puntos, idNivel);
            usuarios.add(usuario);
        }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return usuarios;
    }


    public Usuario findUserByNickName(String nickName) {
        Usuario usuario = null;
        try {
            conectar();
            String sql = "SELECT * FROM usuarios WHERE user = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, nickName);
            ResultSet cursor = preparedStatement.executeQuery();
    
            if (cursor.next()) {
                String usuarioNickName = cursor.getString("user");
                String email = cursor.getString("email");
                String contrasenia = cursor.getString("password");
                int idNivel = cursor.getInt("id_nivel");
                usuario = new Usuario(usuarioNickName, contrasenia, email, idNivel);
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
    
        return usuario;
    }
    

    /**
     * Metodo que añade un usuario a la base de datos.
     * @param newUser
     * @return nuevo usuario
     * @throws SQLException
     */
    public boolean addUsuario(Usuario newUser) throws SQLException{
        try {
            conectar();
            PreparedStatement sentencia = getConnection().prepareStatement(
            "INSERT INTO usuarios (user, nombre, email, password) values (?,?,?,?)");
            sentencia.setString(1, newUser.getUsuarioNickName());
            sentencia.setString(2, newUser.getNombre());
            sentencia.setString(3, newUser.getEmail());
            sentencia.setString(4, newUser.getContrasenia());
            sentencia.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos." + e.getMessage());
            return false;
        } finally {
            cerrar();
        }
    }

    /**
     * Metodo que comprueba si el usuario existe o no.
     */
    public boolean comprobarUsuario(String usuarioNickName){
        boolean existe = false;
        try {
            conectar();
            String sql = "SELECT COUNT(*) AS total FROM usuarios WHERE user = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, usuarioNickName);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()){
                existe = resultado.getInt("total") > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return existe;
    }
    
    /**
     * Metodo que comprueba si el email existe o no.
     */
    public boolean comprobarEmail(String email){
        boolean existe = false;
        try {
            conectar();
            String sql = "SELECT COUNT(*) AS total FROM usuarios WHERE email = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()){
                existe = resultado.getInt("total") > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return existe;
    }

   
    
    
    
}
