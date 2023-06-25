package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AdminPanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleButton AddMovieBtn;

    @FXML
    private ComboBox<?> AgeRate;

    @FXML
    private ToggleButton CreateAdminBtn;

    @FXML
    private ToggleButton CreateUserBtn;

    @FXML
    private ToggleButton DeleteMovieBtn;

    @FXML
    private ToggleButton DeleteUserBtn;

    @FXML
    private TextField Genres;

    @FXML
    private Spinner<?> IMDbRateSpinner;

    @FXML
    private Text IMDbtxt;

    @FXML
    private ToggleButton ManageUsersBtn;

    @FXML
    private TextField MovieLength;

    @FXML
    private TextField MovieLink;

    @FXML
    private ToggleButton MovieSaveBtn;

    @FXML
    private TextField Name;

    @FXML
    private TextField SearchBox;

    @FXML
    private ComboBox<?> ShowType;

    @FXML
    private TableView<?> TableView;

    @FXML
    private ToggleButton UpdateMovieBtn;

    @FXML
    private CheckBox admincheck;
    
    @FXML
    private PasswordField UserPassword;

    @FXML
    private AnchorPane formpanel;

    @FXML
    void AddMovieBtn_Clicked(ActionEvent event) {
    	DisplayMovies();
    }

    @FXML
    void CreateAdminBtn_Clicked(ActionEvent event) {
    	DisplayUsers();
    }

    @FXML
    void CreateUserBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void TableView_CellClicked(MouseEvent event) {

    }
    
    @FXML
    void DeleteMovieBtn_Clicked(ActionEvent event) {
    	DisplayMovies();
    }

    @FXML
    void DeleteUserBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void ManageUsersBtn_Clicked(ActionEvent event) {
    	DisplayUsers();
    }

    @FXML
    void MovieSaveBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void SearchBox_KeyPressed(KeyEvent event) {

    }

    @FXML
    void UpdateMovieBtn_Clicked(ActionEvent event) {
    	DisplayMovies();
    }

    @FXML
    void initialize() {
        Disableall();
    }
    private void Disableall() {
    	Name.setVisible(false);
        UserPassword.setVisible(false);
        MovieLength.setVisible(false);;
        MovieLink.setVisible(false);
        ShowType.setVisible(false);
        AgeRate.setVisible(false);
        Genres.setVisible(false);
        IMDbtxt.setVisible(false);
        IMDbRateSpinner.setVisible(false);
        CreateUserBtn.setVisible(false);
        MovieSaveBtn.setVisible(false);
        DeleteUserBtn.setVisible(false);
        admincheck.setVisible(false);
    } 
    private void DisplayMovies() {
    	Name.setVisible(true);
        UserPassword.setVisible(false);
        MovieLength.setVisible(true);;
        MovieLink.setVisible(true);
        ShowType.setVisible(true);
        AgeRate.setVisible(true);
        Genres.setVisible(true);
        IMDbtxt.setVisible(true);
        IMDbRateSpinner.setVisible(true);
        CreateUserBtn.setVisible(false);
        MovieSaveBtn.setVisible(true);
        DeleteUserBtn.setVisible(false);
        admincheck.setVisible(false);
        MovieSaveBtn.setText("Save");
        Name.setPromptText("Movie Name");
        formpanel.resize(860,468);
        LoginController.AdminPanel.setWidth(860);
    } 
    private void DisplayUsers() {
    	Name.setVisible(true);
        UserPassword.setVisible(true);
        MovieLength.setVisible(false);;
        MovieLink.setVisible(false);
        ShowType.setVisible(false);
        AgeRate.setVisible(false);
        Genres.setVisible(false);
        IMDbtxt.setVisible(false);
        IMDbRateSpinner.setVisible(false);
        CreateUserBtn.setVisible(true);
        MovieSaveBtn.setVisible(false);
        DeleteUserBtn.setVisible(true);
        admincheck.setVisible(true);
        Name.setPromptText("User Name");
        formpanel.resize(610,468);
        LoginController.AdminPanel.setWidth(610);
    } 
}
