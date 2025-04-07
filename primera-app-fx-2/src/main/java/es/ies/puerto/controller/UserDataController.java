package es.ies.puerto.controller;
import java.io.IOException;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Clase que controla la informacion del usuario despues de loguearse.
 * 
 * @author cdiagal
 * @version 1.0.0
 */


public class UserDataController extends AbstractController{
    @FXML
    private Text textUserDataBigLabel;

    @FXML
    private Text userTextUsuario;
    
    @FXML
    private TextField userDataUserTextField;

    @FXML
    private Text userTextPassword;

    @FXML
    private PasswordField userDataPasswordField;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private Text levelTextLabel;

    @FXML
    private TextField userDataLevelTextField;

    @FXML
    private Text textEmailLabel;

    @FXML
    private TextField userDataEmailTextField;

    @FXML
    private Text adviseUserDataText;
    
    @FXML
    private Button updateUserDataButton;

    @FXML
    private Button playButton;

    @FXML
    private Button exitButton;



    private Usuario usuario;

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        usuarioData();
    }

    public void usuarioData(){
    if(usuario != null){
        userDataUserTextField.setText(usuario.getUsuarioNickName());
        userDataPasswordField.setText(usuario.getContrasenia());
        userDataEmailTextField.setText(usuario.getEmail());
        userDataLevelTextField.setText(usuario.getNivel());
    }
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
        textUserDataBigLabel.setText(getPropertiesLanguaje().getProperty("textUserDataBigLabel"));
        userTextUsuario.setText(getPropertiesLanguaje().getProperty("userTextUsuario"));
        userTextPassword.setText(getPropertiesLanguaje().getProperty("userTextPassword"));
        showPasswordCheckBox.setText(getPropertiesLanguaje().getProperty("showPasswordCheckBox"));
        nameTextLabel.setText(getPropertiesLanguaje().getProperty("nameTextLabel"));
        textEmailLabel.setText(getPropertiesLanguaje().getProperty("textEmailLabel"));
        updateUserDataButton.setText(getPropertiesLanguaje().getProperty("updateUserDataButton"));
        exitButton.setText(getPropertiesLanguaje().getProperty("exitButton"));
        }
    }
    
    /**
     * Funcion que abre la página UpdateUserData al pulsar el boton 'Actualizar'.
     */
    @FXML
    protected void onClickOpenUpdateUserData(){
        try {
            Stage stage = (Stage) updateUserDataButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("updateUserData.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 548 , 955);
            stage.setTitle("Actualizar Usuario");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            adviseUserDataText.setText("Error al cargar la página Actualizar usuario.");
            e.printStackTrace();
        }
    }

    /**
     * Funcion que abre la página Login al pulsar el boton 'Salir'.
     */
    @FXML
    protected void onClickExitUserData(){
        try {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 544 , 621);
            stage.setTitle("Datos de Usuario");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            adviseUserDataText.setText("Error al cargar la página Login");
            e.printStackTrace();
        }
    }
}
