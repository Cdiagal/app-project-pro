package es.ies.puerto.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Clase RecoverPasswordController, que controla las acciones y campos relacionados
 * con la recuperacion de la contrasenia del ususario.
 * 
 * @author cdiagal
 * @version 1.0.0
 */

public class RecoverPasswordController extends AbstractController{
    
    @FXML
    private Text textRecoverPasswordBigLabel;

    @FXML
    private Text textEmailLabel;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private Button acceptButton;

    @FXML
    private Text textAdviseEmail;

    @FXML
    private Button exitButton;

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

        textRecoverPasswordBigLabel.setText(getPropertiesLanguaje().getProperty("textRecoverPasswordBigLabel"));
        textEmailLabel.setText(getPropertiesLanguaje().getProperty("textEmailLabel"));
        textEmailLabel.setText(getPropertiesLanguaje().getProperty("textEmailLabel"));
        textFieldEmail.setPromptText(getPropertiesLanguaje().getProperty("prompTextFieldEmail"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_empty"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_match"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_valid"));
        acceptButton.setText(getPropertiesLanguaje().getProperty("acceptButton"));
        exitButton.setText(getPropertiesLanguaje().getProperty("exitButton"));
        }
    }

    /**
     * Hace uso de validarDatos() para asegurar que se cumplen los requisitos y posteriormente 
     * ejecuta openLoginClick() para volver a la ventana Login.
     */
    @FXML
    protected void onClickRecoverPassword(){
        if(validarDatos()){
            textAdviseEmail.setText("Se ha enviado una nueva contraseña al correo");
            openLoginClick();
        }
    }

    /**
     * Metodo que abre la ventana Login.
     */
    @FXML
    protected void openLoginClick(){
        try {
            Stage stage = (Stage) acceptButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500,500);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            textAdviseEmail.setText("Error al cargar la página de login");
            e.printStackTrace();
        }
    }

    
    /**
     * Metodo que permite al usuario salir de la ventana Recuperador de contrasenia y volver a la ventana Login.
     */
    @FXML
    protected void onClickExitRecoveryPassword(){
        try {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 544,621);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            textAdviseEmail.setText("Error al cargar la página de login");
            e.printStackTrace();
        }
    }

    /**
     * Valida y asegura que se cumplen las condiciones para poder recuperar la contrasenia.
     * @return true/false.
     */
    private boolean validarDatos(){
        if(textFieldEmail.getText().isEmpty() || textFieldEmail.getText() == null){
            textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_empty"));
            return false;
        } else {
            textAdviseEmail.setText("");
        }

        if(!emailValido(textFieldEmail.getText())){
            textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_valid"));
            return false;
        }
        return true;
    }

    /**
     * Metodo que asegura un correcto formato de email.
     * @param email
     * @return formato email valido
     */
    private boolean emailValido(String email){
        String regex = "^[A-Za-z0-9%+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
