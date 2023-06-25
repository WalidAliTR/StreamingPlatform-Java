package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.MySql.Util.DatabaseUtil;
import com.MySql.Util.Login;
import com.MySql.Util.Movie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class AdminPanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleButton AddMovieBtn;

    @FXML
    private ComboBox<String> AgeRate;

    @FXML
    private ToggleButton ChangeAdminProfileBtn;

    @FXML
    private ToggleButton CreateUserBtn;

    @FXML
    private ToggleButton DeleteMovieBtn;

    @FXML
    private ToggleButton DeleteUserBtn;

    @FXML
    private TextField Genres;

    @FXML
    private Spinner<Double> IMDbRateSpinner;

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
    private ComboBox<String> ShowType;

    @FXML
    private ToggleButton UpdateMovieBtn;

    @FXML
    private CheckBox admincheck;
    
    @FXML
    private PasswordField UserPassword;

    @FXML
    private AnchorPane formpanel;

    @FXML
    private AnchorPane tableviewpanel;
    
    @FXML
    void AddMovieBtn_Clicked(ActionEvent event) {
    	ShowMovieList("select * from movies");      
    	DisplayMovies();
    }

    @FXML
    void ChangeAdminProfileBtn_Clicked(ActionEvent event) {
    	LoginController.UserPanel.show();
    }

    @FXML
    void CreateUserBtn_Clicked(ActionEvent event) {
    }
    
    @FXML
    void DeleteMovieBtn_Clicked(ActionEvent event) {
    	ShowMovieList("select * from movies");      
    	DisplayMovies();
    }

    @FXML
    void DeleteUserBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void ManageUsersBtn_Clicked(ActionEvent event) {
    	ShowLoginList("select * from login");      
    	DisplayUsers();
    }

    @FXML
    void MovieSaveBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void SearchBox_KeyPressed(KeyEvent event) {
    	if(event.isConsumed())
        	ShowMovieList("select * from movies where MovieName like '%"+SearchBox.getText()+"%'");    
    }

    @FXML
    void UpdateMovieBtn_Clicked(ActionEvent event) {
    	ShowMovieList("select * from movies");      
    	DisplayMovies();
    }

    @FXML
    void initialize() {
        Disableall();
    }
    
    private ObservableList<Movie> fetchMovieDataFromDatabase(String query) {
        ObservableList<Movie> movieList = FXCollections.observableArrayList();
        try (Connection connection = DatabaseUtil.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                String movieName = resultSet.getString("MovieName");
                String showType = resultSet.getString("ShowType");
                double imdbRating = resultSet.getDouble("IMDbRating");
                String genres = resultSet.getString("Genres");
                String ageRating = resultSet.getString("AgeRating");
                String length = resultSet.getString("Length");
                String movieLink = resultSet.getString("MovieLink");
                
                Movie movie = new Movie(movieName, showType, imdbRating, genres, ageRating, length, movieLink);
                movieList.add(movie);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return movieList;
    }
    
    void ShowMovieList(String query) {
    	TableView<Movie> tableView = new TableView<>();
    	tableviewpanel.getChildren().add(tableView);
    	AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        
    	// Create the TableColumns
    	TableColumn<Movie, String> movieNameColumn = new TableColumn<>("Movie Name");
        TableColumn<Movie, String> showTypeColumn = new TableColumn<>("Show Type");
        TableColumn<Movie, Double> imdbRatingColumn = new TableColumn<>("IMDb Rating");
        TableColumn<Movie, String> genresColumn = new TableColumn<>("Genres");
        TableColumn<Movie, String> ageRatingColumn = new TableColumn<>("Age Rating");
        TableColumn<Movie, String> lengthColumn = new TableColumn<>("Length");
        TableColumn<Movie, Void> movieLinkColumn = new TableColumn<>("Movie Link");
    	// Set cell value factories to extract data from Movie objects
        movieNameColumn.setCellValueFactory(data -> data.getValue().movieNameProperty());
        showTypeColumn.setCellValueFactory(data -> data.getValue().showTypeProperty());
        imdbRatingColumn.setCellValueFactory(data -> data.getValue().imdbRatingProperty().asObject());
        genresColumn.setCellValueFactory(data -> data.getValue().genresProperty());
        ageRatingColumn.setCellValueFactory(data -> data.getValue().ageRatingProperty());
        lengthColumn.setCellValueFactory(data -> data.getValue().lengthProperty());

        // Create a cell factory for the Movie Link column to display it as a Button
        Callback<TableColumn<Movie, Void>, TableCell<Movie, Void>> buttonCellFactory = new Callback<>() {
            @Override
            public TableCell<Movie, Void> call(TableColumn<Movie, Void> param) {
                return new TableCell<>() {
                    private final Button linkButton = new Button("Open");

                    {
                        linkButton.setOnMouseClicked(event -> {
                            Movie movie = getTableView().getItems().get(getIndex());
                            String movieLink = movie.getMovieLink();
                            // TODO: Handle opening the movie link
                            System.out.println("Opening movie link: " + movieLink);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(linkButton);
                        }
                    }
                };
            }
        };

        movieLinkColumn.setCellFactory(buttonCellFactory);
        tableView.getColumns().clear();

        tableView.getColumns().addAll(movieNameColumn, showTypeColumn, imdbRatingColumn,
                genresColumn, ageRatingColumn, lengthColumn, movieLinkColumn);
        // Fetch data from the database and populate the TableView
        ObservableList<Movie> movieList = fetchMovieDataFromDatabase(query);
        tableView.setItems(movieList);
    }
///////////////////////////////////////////////////////////////////
    
    private ObservableList<Login> fetchLoginDataFromDatabase(String query) {
        ObservableList<Login> LoginList = FXCollections.observableArrayList();
        try (Connection connection = DatabaseUtil.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                String UserName = resultSet.getString("UserName");
                String AccountType = resultSet.getString("AccountType");
                int UserID = resultSet.getInt("ID");
                
                Login login = new Login(UserID, UserName, AccountType);
                LoginList.add(login);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return LoginList;
    }
    
    void ShowLoginList(String query){
    	TableView<Login> tableView = new TableView<>();
    	tableviewpanel.getChildren().add(tableView);
    	AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        
    	// Create the TableColumns
    	TableColumn<Login, Integer> IDColumn = new TableColumn<>("User ID");
        TableColumn<Login, String> UserNameColumn = new TableColumn<>("USer Name");
        TableColumn<Login, String> AccountTypeColumn = new TableColumn<>("Account Type");
        
    	// Set cell value factories to extract data from Movie objects
        IDColumn.setCellValueFactory(data -> data.getValue().UserIDProperty().asObject());
        UserNameColumn.setCellValueFactory(data -> data.getValue().UserNameProperty());
        AccountTypeColumn.setCellValueFactory(data -> data.getValue().AccountTypeProperty());
        
        tableView.getColumns().clear();

        tableView.getColumns().addAll(IDColumn,UserNameColumn,AccountTypeColumn);
        // Fetch data from the database and populate the TableView
        ObservableList<Login> movieList = fetchLoginDataFromDatabase(query);
        tableView.setItems(movieList);
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
