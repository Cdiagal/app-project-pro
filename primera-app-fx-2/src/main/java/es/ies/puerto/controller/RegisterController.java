package es.ies.puerto.controller;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Usuario;
import es.ies.puerto.model.service.UsuarioService;
import es.ies.puerto.model.service.UsuarioServiceDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Clase que controla todo lo relacionado con el registro de un nuevo usuario.
 *
 * @author cdiagal
 * @version 1.0.0
 */

public class RegisterController extends AbstractController {
    
    private final UsuarioServiceDB usuarioServiceDB = new UsuarioServiceDB();

    @FXML
    private Text textRegisterBigLabel;

    @FXML
    private Text userTextUsuario;

    @FXML
    private TextField textFieldUsuario;

    @FXML
    private Text textUserAdvise;

    @FXML
    private Text userTextPassword;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Text textRepeatPassword;

    @FXML
    private PasswordField textFieldRepeatPassword;

    @FXML
    private Text textAdviseMessagePassword;

    @FXML
    private Text nameTextLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Text textNameRegisterAdvise;

    @FXML
    private Text textEmailLabel;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private Text textRepeatEmail;

    @FXML
    private TextField textFieldRepeatEmail;

    @FXML
    private Text textAdviseEmail;

    @FXML
    private Button buttonRegister;

    @FXML
    private Text textAdviseRegister;

    @FXML
    private Button exitButton;



    /**
     * Funcion que tras hacer click en el boton 'Registrar' y  validar los datos con 'validarDatos()',
     * lanza un mensaje de confirmación.
     * @throws SQLException 
     */
    @FXML
    private void onClickRegister() throws SQLException{

        if(validarDatos()){
            String username = textFieldUsuario.getText();
            String email = textFieldEmail.getText();
            UsuarioServiceDB usuarioServiceDB = new UsuarioServiceDB();

            if(usuarioServiceDB.comprobarUsuario(username)){
                textUserAdvise.setText("El usuario " + "'" + username + "'" + " ya está registrado");
                return;
            }
            if(usuarioServiceDB.comprobarEmail(email)){
                textAdviseEmail.setText("El email " + "'" + email + "'" + " ya está registrado.");
                return;
            }
            Usuario newUser = new Usuario(textFieldUsuario.getText(),textFieldPassword.getText(),
                                            nameTextField.getText(), textFieldEmail.getText());

            UsuarioServiceDB usuarioService = new UsuarioServiceDB();
            boolean insertado = usuarioService.addUsuario(newUser);
            if (insertado){
                textAdviseRegister.setText("¡Usuario registrado correctamente!");
            } else {
                textAdviseRegister.setText("¡Error!. El usuario ya existe.");
            }
        }
    }

    /**
     * Metodo que cambia de la pantalla de Registro a Login haciendo click en el boton 'Salir'.
     */
    @FXML
    protected void onClickExitRegisterButton(){

        try {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 544 , 621);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            textAdviseRegister.setText("Error al cargar la pantalla de login");
            e.printStackTrace();
        }
    }


    /**
     * Funcion que valida los datos indicados y lanza mensajes en funcion de los resultados esperados.
     */
    
    private boolean validarDatos(){
    


        if(textFieldUsuario.getText().isEmpty()){
            textUserAdvise.setText(getPropertiesLanguaje().getProperty("textUserAdvise"));
            return false;
        }

        if(textFieldPassword == null || textFieldRepeatPassword == null
            || textFieldRepeatPassword.getText().isEmpty() || textFieldPassword.getText().isEmpty()){
                textAdviseMessagePassword.setText(getPropertiesLanguaje().getProperty("textAdviseMessagePassword_null"));
                return false;
        }

        if(!textFieldPassword.getText().equals(textFieldRepeatPassword.getText())){
            textAdviseMessagePassword.setText(getPropertiesLanguaje().getProperty("textAdviseMessagePassword_match"));
            return false;
        } else {
            textAdviseMessagePassword.setText("");
        }

        if(nameTextField.getText() == null || nameTextField.getText().isEmpty()){
            textNameRegisterAdvise.setText(getPropertiesLanguaje().getProperty("textNameRegisterAdvise"));
            return false;
        } else {
            textNameRegisterAdvise.setText("");
        }
        
        if(textFieldEmail.getText() == null || textFieldRepeatEmail.getText() == null
            || textFieldEmail.getText().isEmpty() || textFieldRepeatEmail.getText().isEmpty()){
                textAdviseEmail.setText(getPropertiesLanguaje().getProperty("¡Debe rellenar el campo con un email!"));
                return false;
        }

        if(!textFieldEmail.getText().equals(textFieldRepeatEmail.getText())){
            textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_match"));
            return false;
        } else {
            textAdviseEmail.setText("");
        }
        
        if(!emailCorrecto(textFieldEmail.getText()) || !emailCorrecto(textFieldRepeatEmail.getText())){
            textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_valid"));
            return false;
        } else {
            textAdviseEmail.setText("");
        }
        return true;
    }

    /**
     * Funcion que comprueba que el email introducido cumple con el formato establecido para ser un email.
     * @param email del usuario
     * @return validacion de formato.
     */
    private boolean emailCorrecto(String email){
        String regex = "^[A-Za-z0-9%+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

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

        textRegisterBigLabel.setText(getPropertiesLanguaje().getProperty("textRegisterBigLabel"));
        userTextUsuario.setText(getPropertiesLanguaje().getProperty("userTextUsuario"));
        textFieldUsuario.setPromptText(getPropertiesLanguaje().getProperty("promptTextUsuario"));
        textUserAdvise.setText(getPropertiesLanguaje().getProperty("textUserAdvise"));
        userTextPassword.setText(getPropertiesLanguaje().getProperty("userTextPassword"));
        textFieldPassword.setPromptText(getPropertiesLanguaje().getProperty("promptTextPassword"));
        textRepeatPassword.setText(getPropertiesLanguaje().getProperty("textRepeatPassword"));
        textFieldRepeatPassword.setPromptText(getPropertiesLanguaje().getProperty("promptTextRepeatPassword"));
        textAdviseMessagePassword.setText(getPropertiesLanguaje().getProperty("textAdviseMessagePassword_null"));
        textAdviseMessagePassword.setText(getPropertiesLanguaje().getProperty("textAdviseMessagePassword_match"));
        nameTextLabel.setText(getPropertiesLanguaje().getProperty("nameTextLabel"));
        nameTextField.setPromptText(getPropertiesLanguaje().getProperty("promptTextName"));
        textNameRegisterAdvise.setText(getPropertiesLanguaje().getProperty("textNameRegisterAdvise"));
        textEmailLabel.setText(getPropertiesLanguaje().getProperty("textEmailLabel"));
        textFieldEmail.setPromptText(getPropertiesLanguaje().getProperty("promptTextFieldEmail"));
        textRepeatEmail.setText(getPropertiesLanguaje().getProperty("textRepeatEmail"));
        textFieldRepeatEmail.setPromptText(getPropertiesLanguaje().getProperty("promptTextRepeatEmail"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_empty"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_match"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_valid"));
        buttonRegister.setText(getPropertiesLanguaje().getProperty("buttonRegister"));
        exitButton.setText(getPropertiesLanguaje().getProperty("exitButton"));
        }
    }

}
