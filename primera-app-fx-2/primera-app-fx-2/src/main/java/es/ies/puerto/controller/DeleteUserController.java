package es.ies.puerto.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Usuario;
import es.ies.puerto.model.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeleteUserController extends AbstractController{
    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    protected Text deleteUSerBigLabel;

    @FXML
    protected Text deleteUserTextLabel;

    @FXML
    protected TextField textFieldEmail;

    @FXML
    protected Text textRepeatEmail;

    @FXML
    protected TextField textFieldRepeatEmail;

    @FXML
    protected Text textAdviseEmail;

    @FXML
    protected Button deleteUserButton;

    @FXML
    protected Text deleteUserTextAdvise;

    @FXML
    protected Button exitButton;
    
    /**
     * Metodo que inicializa el cambio de idioma en el ComboBox.
     */
    @FXML
    public void initialize(){
        changeLanguaje();
    }

    /**
     * Funcion que cambia el idioma de las etiquetas y objetos de la ventana
     */
    @FXML
    public void changeLanguaje() {
        String languaje = AbstractController.getIdiomaActual();

        if(getPropertiesLanguaje() == null){
            setPropertiesLanguaje(loadLanguage("languaje", languaje));
        }
        if(getPropertiesLanguaje() != null){

        deleteUSerBigLabel.setText(getPropertiesLanguaje().getProperty("deleteUSerBigLabel"));
        deleteUserTextLabel.setText(getPropertiesLanguaje().getProperty("deleteUserTextLabel"));
        textFieldEmail.setPromptText(getPropertiesLanguaje().getProperty("prompTextFieldEmail"));
        textRepeatEmail.setText(getPropertiesLanguaje().getProperty("textRepeatEmail"));
        textFieldRepeatEmail.setPromptText(getPropertiesLanguaje().getProperty("promptTextRepeatEmail"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_empty"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_match"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_valid"));
        deleteUserButton.setText(getPropertiesLanguaje().getProperty("deleteUserButton"));
        exitButton.setText(getPropertiesLanguaje().getProperty("exitButton"));
        }
    }

    @FXML
    private void onClickDeleteUser(){
        if(validarDatos()){
            Usuario usuarioEliminar = usuarioService.findByNickName(textFieldEmail.getText());

            if(usuarioEliminar != null){
            boolean eliminado = usuarioService.delete(usuarioEliminar);
                if (eliminado){
                    deleteUserTextAdvise.setText("¡Usuario eliminado correctamente!");
                } else {
                    deleteUserTextAdvise.setText("¡Error al eliminar el usuario!");
                }
            }else {
                deleteUserTextAdvise.setText("El usuario no existe.");
            }
        }
    }

    @FXML
    protected void onClickExitDeleteUser(){

        try {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("updateUserData.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 548 , 955);
            stage.setTitle("Modificar datos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            deleteUserTextAdvise.setText("Error al cargar la pantalla de Modificar datos.");
            e.printStackTrace();
        }
    }


    private boolean validarDatos(){
        if (textFieldEmail.getText() == null || textFieldRepeatEmail.getText() == null
            || textFieldEmail.getText().isEmpty() || textFieldRepeatEmail.getText().isEmpty()){
                deleteUserTextAdvise.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_empty"));
            return false;
        }

        if (!textFieldEmail.getText().equals(textFieldRepeatEmail.getText())){
            deleteUserTextAdvise.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_match"));
            return false;
        }

        if (!emailValido(textFieldEmail.getText()) || !emailValido(textFieldRepeatEmail.getText())){
            deleteUserTextAdvise.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_valid"));
            return false;
        }
        return true;
    }

    private boolean emailValido(String email){
        String regex = "^[A-Za-z0-9%+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
