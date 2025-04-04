package es.ies.puerto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que manipula los usuarios en funcion de lo que se necesite trabajar con los usuarios. Tiene implementado un CRUD.
 *
 * @author cdiagal
 * @version  1.0.0
 */

public class UsuarioService extends UsuarioUtilJson{
    

    public UsuarioService(){
        super();
        usuariosList = loadFile();
    }

    /**
     * Metodo que aniade un usuario nuevo a la lista.
     * @param usuario nuevo.
     * @return lista de usuarios.
     */
    public boolean create(Usuario usuario){
        if (usuario == null){
            return false;
        }
        if (usuario.getUsuarioNickName() == null || usuario.getUsuarioNickName().isEmpty()
            || usuario.getNombre() == null || usuario.getNombre().isEmpty()
            || usuario.getContrasenia() == null || usuario.getContrasenia().isEmpty()
            || usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            return false;
        }
        if (usuariosList.contains(usuario)){
            return false;
        }
        boolean insertar = usuariosList.add(usuario);
        if(insertar){
            saveFile(usuariosList);
        }
        return insertar;
    }


    /**
     * Metodo que carga la lista completa de usuarios.
     * @return List de usuarios.
     */
    public List<Usuario> loadAll(){
        return new ArrayList<>(usuariosList);
    }

    /**
     * Metodo que busca un usuario por el nickname.
     */
    public Usuario findByNickName(String usuarioNickName){
        Usuario usuarioBuscar = new Usuario(usuarioNickName);
        int posicion = usuariosList.indexOf(usuarioBuscar);
        if(posicion < 0){
            return null;
        }
        return usuariosList.get(posicion);
    }

    /**
     * Metodo que actualiza un usuario de la lista y reescribe el fichero.
     * @param usuario para actualizar.
     * @return lista de usuarios actualizada con los nuevos datos.
     */
    public boolean update(Usuario usuario){
        int posicion = usuariosList.indexOf(usuario);
        if (posicion < 0){
            return false;
        }
        usuariosList.set(posicion, usuario);
        return saveFile(usuariosList);
    }

    public boolean delete(Usuario usuario){
        if (usuario == null){
            return false;
        }
        boolean eliminar = usuariosList.remove(usuario);
        if (eliminar){
            saveFile(usuariosList);
        }
        return eliminar;
    }


}
