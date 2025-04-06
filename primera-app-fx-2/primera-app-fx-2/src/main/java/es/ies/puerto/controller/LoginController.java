package es.ies.puerto.controller;

import java.io.IOException;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Usuario;
import es.ies.puerto.model.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Clase que controla todas las funciones que contiene la ventana 'Login'.
 *
 * @author cdiagal
 * @version 1.0.0
 */

public class LoginController extends AbstractController {

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    private ComboBox comboLanguages;

    @FXML
    private Text userTextUsuario;

    @FXML
    private TextField textFieldUsuario;

    @FXML
    private Text userTextPassword;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Text textFieldMensaje;

    @FXML
    private Button acceptButton;

    @FXML
    private Button buttonRegister;

    @FXML
    private Button buttonRecoverPassword;

    @FXML
    private Button usersButton;

    /**
     * Metodo que inicializa el cambio de idioma en el ComboBox.
     */
    @FXML
    public void initialize() {
        comboLanguages.getItems().add("español");
        comboLanguages.getItems().add("english");
        comboLanguages.getItems().add("français");

        comboLanguages.setValue("español");
        changeLanguaje();
    }

    /**
     * Funcion que cambia el idioma de las etiquetas y objetos de la ventana
     */
    @FXML
    public void changeLanguaje() {
        String idiomaElegido = comboLanguages.getValue().toString();
        AbstractController.setIdiomaActual(idiomaElegido);
        setPropertiesLanguaje(loadLanguage("languaje", comboLanguages.getValue().toString()));

        userTextUsuario.setText(getPropertiesLanguaje().getProperty("userTextUsuario"));
        textFieldUsuario.setPromptText(getPropertiesLanguaje().getProperty("promptTextUsuario"));
        userTextPassword.setText(getPropertiesLanguaje().getProperty("userTextPassword"));
        comboLanguages.setPromptText(getPropertiesLanguaje().getProperty("comboLanguages"));
        textFieldPassword.setPromptText(getPropertiesLanguaje().getProperty("promptTextPassword"));
        acceptButton.setText(getPropertiesLanguaje().getProperty("acceptButton"));
        textFieldMensaje.setText(getPropertiesLanguaje().getProperty("textFieldMensaje"));
        buttonRegister.setText(getPropertiesLanguaje().getProperty("buttonRegister"));
        buttonRecoverPassword.setText(getPropertiesLanguaje().getProperty("buttonRecoverPassword"));
    }

    /**
     * Metodo que abre la ventana 'Datos de usuario' después de hacer uso de
     * 'validarDatosLogin()' y que esté todo validado.
     * Hace uso del boton 'Aceptar' en la interfaz.
     */
    @FXML
    protected void onLoginButtonClick() {
        try {
            Usuario usuarioLogin = validarDatosLogin();

            if (usuarioLogin != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("userData.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 500, 700);

                UserDataController userDataController = fxmlLoader.getController();
                userDataController.setUsuario(usuarioLogin);

                Stage stage = (Stage) acceptButton.getScene().getWindow();
                stage.centerOnScreen();
                stage.setTitle("Datos de usuario");
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException e) {
            textFieldMensaje.setText("Error al cargar la página Datos de usuario");
            e.printStackTrace();
        }
    }

    /**
     * Funcion que valida todos los datos posibles para que pueda acceder un nuevo
     * usuario con sus credenciales.
     * 
     * @return nuevo login.
     */
    private Usuario validarDatosLogin() {
        if (textFieldUsuario == null || textFieldUsuario.getText().isEmpty() ||
            textFieldPassword == null || textFieldPassword.getText().isEmpty()) {
            textFieldMensaje.setText(getPropertiesLanguaje().getProperty("textFieldMensaje"));
            return null;
        }

        Usuario usuarioLogin = usuarioService.findByNickName(textFieldUsuario.getText());
        if (usuarioLogin == null) {
            textFieldMensaje.setText(getPropertiesLanguaje().getProperty("textFieldMensaje_errorUser"));
            return null;
        }

        if (!usuarioLogin.getContrasenia().equals(textFieldPassword.getText())) {
            textFieldMensaje.setText(getPropertiesLanguaje().getProperty("textFieldMensaje_errorPassword"));
            return null;
        }

        textFieldMensaje.setText("¡Usuario validado correctamente!");
        return usuarioLogin;
    }

    /**
     * Metodo que abre la pagina 'Registro' para poder registrar un usuario.
     * Hace uso del boton 'Registrar' en la interfaz.
     */
    @FXML
    protected void openRegisterClick() {

        try {
            Stage stage = (Stage) buttonRegister.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 548, 907);

            RegisterController registerController = fxmlLoader.getController();
            registerController.setPropertiesLanguaje(this.getPropertiesLanguaje());//Cambia el idioma de una ventana a otra accediendo a la clase abstracta.

            stage.centerOnScreen();
            stage.setTitle("Registro");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            textFieldMensaje.setText("Error al cargar la página de Registro.");
            e.printStackTrace();
        }

    }

    /**
     * Metodo que abre la pagina 'Recuperar contrasenia' para poder recuperar el
     * password.
     * Hace uso del boton 'Recuperar contrasenia' en la interfaz.
     */
    @FXML
    protected void openRecoverPasswordClick() {

        try {
            Stage stage = (Stage) buttonRecoverPassword.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("recoverPassword.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            stage.setTitle("Recuperador de contraseña");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            textFieldMensaje.setText("Error al cargar la página de Recuperación de contraseña.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void openUsersList(){
        try {
            Stage stage = (Stage) usersButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("userList.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400,600);
            stage.setTitle("Usuarios");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}