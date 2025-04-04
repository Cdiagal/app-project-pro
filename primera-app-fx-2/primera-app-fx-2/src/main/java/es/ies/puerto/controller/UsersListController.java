package es.ies.puerto.controller;


import java.util.List;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.model.Usuario;
import es.ies.puerto.model.UsuarioServiceDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UsersListController {
    @FXML
    protected AnchorPane anchorPaneUsers;

    @FXML
    protected Pane updatePane;

    @FXML
    protected Text textUsersBigLabel;

    @FXML
    protected ListView listUsersView;

    @FXML
    protected Button exitButton;

    @FXML
    public void initialize(){
        UsuarioServiceDB usuarioServiceDB = new UsuarioServiceDB();
        List<Usuario> usuarios = usuarioServiceDB.loadFile();

        for (Usuario usuario : usuarios) {
            listUsersView.getItems().add(usuario.getUsuarioNickName() + "," + usuario.getEmail());
        }
    }

    @FXML
    public void openLogin(){
        try {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 544, 621);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
