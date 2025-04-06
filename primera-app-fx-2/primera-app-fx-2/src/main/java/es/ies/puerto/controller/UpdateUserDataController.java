package es.ies.puerto.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Usuario;
import es.ies.puerto.model.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateUserDataController extends AbstractController{
    
    private final UsuarioService usuarioService = new UsuarioService();
    @FXML
    private Text updateUserDataBigLabel;
    @FXML
    private Text newNickNameTextLabel;
    @FXML
    private TextField newNickNameTextField;

    @FXML
    private Text newNickNameTextAdvise;

    @FXML
    private Text newPasswordTextLabel;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Text textRepeatPassword;

    @FXML
    private PasswordField repeatNewPasswordField;

    @FXML
    private Text textAdviseMessagePassword;

    @FXML
    private Text updateNameTextLabel;

    @FXML
    private TextField updateNewNameTextField;

    @FXML
    private Text newNameTextAdvise;

    @FXML
    private Text newEmailLabel;

    @FXML
    private TextField newEmailTextField;

    @FXML
    private Text repeatNewEmailLabel;

    @FXML
    private TextField repeatNewEmailTextField;
    @FXML
    private Text textAdviseEmail;

    @FXML
    private Text updateEmailTextAdvise;

    @FXML
    private Button updateUserButton;

    @FXML
    private Text updateUserTextAdvise;

    @FXML
    private Button deleteUserButton;

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

        updateUserDataBigLabel.setText(getPropertiesLanguaje().getProperty("updateUserDataBigLabel"));
        newNickNameTextLabel.setText(getPropertiesLanguaje().getProperty("newNickNameTextLabel"));
        newNickNameTextField.setPromptText(getPropertiesLanguaje().getProperty("prompTextNewNickName"));
        newNickNameTextAdvise.setText(getPropertiesLanguaje().getProperty("newNickNameTextAdvise"));
        newPasswordTextLabel.setText(getPropertiesLanguaje().getProperty("newPasswordTextLabel"));
        newPasswordField.setPromptText(getPropertiesLanguaje().getProperty("promptTextnewPassword"));
        textRepeatPassword.setText(getPropertiesLanguaje().getProperty("textRepeatPassword"));
        repeatNewPasswordField.setPromptText(getPropertiesLanguaje().getProperty("prompTextRepeatNewPassword"));
        textAdviseMessagePassword.setText(getPropertiesLanguaje().getProperty("textAdviseMessagePassword_null"));
        textAdviseMessagePassword.setText(getPropertiesLanguaje().getProperty("textAdviseMessagePassword_match"));
        updateNameTextLabel.setText(getPropertiesLanguaje().getProperty("updateNameTextLabel"));
        updateNewNameTextField.setPromptText(getPropertiesLanguaje().getProperty("promptTextupdateNewName"));
        newNameTextAdvise.setText(getPropertiesLanguaje().getProperty("newNameTextAdvise"));
        newEmailLabel.setText(getPropertiesLanguaje().getProperty("newEmailLabel"));
        newEmailTextField.setPromptText(getPropertiesLanguaje().getProperty("prompTextnewEmail"));
        repeatNewEmailLabel.setText(getPropertiesLanguaje().getProperty("repeatNewEmailLabel"));
        repeatNewEmailTextField.setPromptText(getPropertiesLanguaje().getProperty("promptTextRepeatEmail"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_empty"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_match"));
        textAdviseEmail.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_valid"));
        updateUserButton.setText(getPropertiesLanguaje().getProperty("updateUserButton"));
        deleteUserButton.setText(getPropertiesLanguaje().getProperty("deleteUserButton"));
        exitButton.setText(getPropertiesLanguaje().getProperty("exitButton"));
        }
    }

    @FXML
    protected void onClickUpdateUser(){
        if(validarDatosUpdate()){
            Usuario usuarioActualizado = new Usuario(
                    newNickNameTextField.getText(), newPasswordField.getText(),
                    updateNewNameTextField.getText(), newEmailTextField.getText());
            boolean actualizado = usuarioService.update(usuarioActualizado);
            if (actualizado){
                updateUserTextAdvise.setText("¡El usuario se ha actualizado correctamente!");
            } else {
                updateUserTextAdvise.setText("¡Error al actualizar el usuario!");
            }
        }
    }

    @FXML
    protected void onClickDeleteUser(){
        try {
            Stage stage = (Stage) deleteUserButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("deleteUser.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            stage.setTitle("Eliminar usuario");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            updateUserTextAdvise.setText("Error al cargar la pantalla de eliminar usuario.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void onClickUpdateExitButton(){

        try {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("userData.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500 , 728);
            stage.setTitle("Mi primera app-fx");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            updateUserTextAdvise.setText("Error al cargar la pantalla de ");
            e.printStackTrace();
        }
    }

    public boolean validarDatosUpdate(){
        if(newNickNameTextField.getText() == null || newNickNameTextField.getText().isEmpty() ){
            newNickNameTextAdvise.setText(getPropertiesLanguaje().getProperty("newNickNameTextAdvise"));
            return false;
        }

        if(newPasswordField.getText() == null || repeatNewPasswordField.getText() == null ||
            newPasswordField.getText().isEmpty() || repeatNewPasswordField.getText().isEmpty()){
                textAdviseMessagePassword.setText(getPropertiesLanguaje().getProperty("textAdviseMessagePassword_null"));
                return false;
            }

        if(!newPasswordField.getText().equals(repeatNewPasswordField.getText())){
            textAdviseMessagePassword.setText(getPropertiesLanguaje().getProperty("textAdviseMessagePassword_match"));
            return false;
        } else {
            textAdviseMessagePassword.setText("");
        }

        if(updateNewNameTextField.getText() == null || updateNewNameTextField.getText().isEmpty()){
            newNameTextAdvise.setText(getPropertiesLanguaje().getProperty("newNameTextAdvise"));
            return false;
        }

        if(newEmailTextField.getText() == null || repeatNewEmailTextField.getText() == null
            || newEmailTextField.getText().isEmpty() || repeatNewEmailTextField.getText().isEmpty()){
            updateEmailTextAdvise.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_empty"));
            return false;
        }

        if(!newEmailTextField.getText().equals(repeatNewEmailTextField.getText())){
            updateEmailTextAdvise.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_match"));
            return false;
        } else {
            updateEmailTextAdvise.setText("");
        }

        if(!emailValido(newEmailTextField.getText()) || !emailValido(repeatNewEmailTextField.getText())){
            updateEmailTextAdvise.setText(getPropertiesLanguaje().getProperty("textAdviseEmail_valid"));
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
