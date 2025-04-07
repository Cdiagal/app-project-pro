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
            "INSERT INTO usuarios (user, email, password, puntos, idNivel ) values (?,?,?,?,?,?)");
            sentencia.setString(1, newUser.getUsuarioNickName());
            sentencia.setString(2, newUser.getEmail());
            sentencia.setString(3, newUser.getContrasenia());
            sentencia.setInt(4, newUser.getPuntos());
            sentencia.setInt(5, newUser.getIdNivel());
            sentencia.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos." + e.getMessage());
            return false;
        } finally {
            cerrar();
        }
    }


    public boolean comprobarUsuario (String usuarioNickName){
        boolean existe = false;
        try {
            conectar();
            String sql = "SELECT COUNT(*) FROM usuarios WHERE user = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, usuarioNickName);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()){
                existe = true;
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        return existe;
    }

    public boolean comprobarEmail (String email){
        boolean existe = false;
        try {
            conectar();
            String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()){
                existe = true;
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        return existe;
    }
    
    
}
