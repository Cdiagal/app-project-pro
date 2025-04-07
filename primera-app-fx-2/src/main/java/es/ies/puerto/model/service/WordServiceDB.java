package es.ies.puerto.model.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Word;

/**
 * Clase de servicio que gestiona la tabla de palabras almacenadas en la base de datos.
 * @author cdiagal
 * @version 1.0.0
 */

public class WordServiceDB extends AbstractController{
    

    /**
     * Metodo que obtiene todas las palabras de la base de datos.
     * @return lista de palabras.
     */
    public List<Word> getAllWords(){
        List<Word> palabras = new ArrayList<>();

        try {
            conectar();
            String sql = "SELECT * FROM palabras p JOIN niveles n ON p.id_nivel = n.id";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            ResultSet cursor = preparedStatement.executeQuery();

            while (cursor.next()) { 
                int id = cursor.getInt("id");
                String palabra = cursor.getString("palabra");
                int idNivel = cursor.getInt("id_nivel");
                String nivel = cursor.getString("nivel");

                palabras.add(new Word(id, nivel, idNivel, nivel));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return palabras;
    }


    /**
     * Metodo que obtiene palabras por nivel de dificultad.
     * @return Lista de palabras por nivel.
     */
    public List<Word> getWordsByLevel(int idNivel){
        List<Word> palabras = new ArrayList<>();

        try {
            conectar();
            String sql = "SELECT p.id, p.palabra, p.id_nivel, n.nivel" +
                        "FROM palabras JOIN niveles n ON p.id_nivel = n.id WHERE p.id_nivel = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idNivel);
            ResultSet cursor = preparedStatement.executeQuery();

            while (cursor.next()) {
                int id = cursor.getInt("id");
                String palabra = cursor.getString("palabra");
                int id_nivel = cursor.getInt("id_nivel");
                String n_nivel = cursor.getString("nivel");

                palabras.add(new Word(id, palabra, id_nivel, n_nivel));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return palabras;
    }

    /**
     * Metodo que elige una palabra aleatoria de la lista del nivel indicado.
     * @param idNivel nivel de dificultad.
     * @return palabra aleatoria.
     */
    public Word randomWordByLevel(int idNivel){
        List<Word> listaAleatoria = getWordsByLevel(idNivel);
        if (listaAleatoria.isEmpty()){
            return null;
        }

        Random random = new Random();
        return listaAleatoria.get(random.nextInt(listaAleatoria.size()));
    }

}
