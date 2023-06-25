package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class UserPanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleButton CreateUserBtn;

    @FXML
    private ToggleButton DeleteUserBtn;

    @FXML
    private TextField MovieLength;

    @FXML
    private TextField Name;

    @FXML
    private PasswordField UserPassword;

    @FXML
    private PasswordField UserPassword1;

    @FXML
    private AnchorPane formpanel;

    @FXML
    void CreateUserBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void DeleteUserBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        
    }

}
