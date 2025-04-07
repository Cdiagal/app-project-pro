package es.ies.puerto.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase abstracta que aloja los metodos que trabajan con ficheros con formato ".json".
 * En este caso tenemos la ubicacion del fichero y los metodos de lectura y escritura del mismo.
 *
 * @author cdiagal
 * @version 1.0.0
 */


public abstract class UsuarioUtilJson {
    ObjectMapper objectMapper;
    File file;
    String path = "src/main/resources/usuariosList.json";

    public List<Usuario> usuariosList;

    /**
     * Constructor que inicializa el fichero y el objectmapper.
     */

    public UsuarioUtilJson(){
        file = new File(path);
        objectMapper = new ObjectMapper();

        if(!file.exists()){
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                objectMapper.writeValue(file, new ArrayList<Usuario>());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Metodo que lee el fichero.
     * @return Lista con usuarios.
     */
    protected List<Usuario> loadFile(){
        try {
            if(!file.exists()){
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Usuario>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Metodo que escribe el fichero.
     * @param usuariosList lista.
     * @return lista con usuarios.
     */
    protected boolean saveFile(List<Usuario> usuariosList){
        try {
            objectMapper.writeValue(file, usuariosList);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    
}
