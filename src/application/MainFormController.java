package application;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import com.MySql.Util.DatabaseUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;
import java.sql.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
    public class MainFormController {
    	private static final String DB_URL = "jdbc:mysql://localhost:3306/streamingplatformdb";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "mysql";
        
    	public MainFormController() throws SQLException {
    		con = DatabaseUtil.getConnection();
    	}
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private ToggleButton CategoriesBtn;

        @FXML
        private ToggleButton MoviesBtn;

        @FXML
        private ImageView ExitBtn;

        @FXML
        private ToggleButton HomeBtn;

        @FXML
        private TextField SearchBox;

        @FXML
        private ImageView SearchBtn;

        @FXML
        private ToggleButton TVshowsBtn;

        @FXML
        private ToggleButton Top10Btn;

        @FXML
        private ImageView UserIcon;

        @FXML
        private Label UserNametxt;

        @FXML
        private ToggleButton WatchlistBtn;

        @FXML
        private TableView<Movie> MoviesTable;
        
        @FXML
        private TableColumn<Movie, String> movieNameColumn;

        @FXML
        private TableColumn<Movie, String> showTypeColumn;

        @FXML
        private TableColumn<Movie, Double> imdbRatingColumn;

        @FXML
        private TableColumn<Movie, String> genresColumn;

        @FXML
        private TableColumn<Movie, String> ageRatingColumn;

        @FXML
        private TableColumn<Movie, String> lengthColumn;

        @FXML
        private TableColumn<Movie, Void> movieLinkColumn;
        
        
        Connection con=null;
        PreparedStatement cmd=null;
        ResultSet Output=null;
        String sql;

        private ObservableList<Movie> fetchMovieDataFromDatabase() {
            ObservableList<Movie> movieList = FXCollections.observableArrayList();
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM movies");
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return movieList;
        }
        
        @FXML
        void initialize() {
        	// Create the TableColumns
            /*TableColumn<Movie, String> movieNameColumn = new TableColumn<>("Movie Name");
            TableColumn<Movie, String> showTypeColumn = new TableColumn<>("Show Type");
            TableColumn<Movie, Integer> releaseDateColumn = new TableColumn<>("Release Date");
            TableColumn<Movie, Double> imdbRatingColumn = new TableColumn<>("IMDb Rating");
            TableColumn<Movie, String> genresColumn = new TableColumn<>("Genres");
            TableColumn<Movie, String> ageRatingColumn = new TableColumn<>("Age Rating");
            TableColumn<Movie, String> lengthColumn = new TableColumn<>("Length");
            TableColumn<Movie, Void> movieLinkColumn = new TableColumn<>("Movie Link");*/

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

            // Add the columns to the TableView
            /*MoviesTable.getColumns().addAll(movieNameColumn, showTypeColumn, imdbRatingColumn,
                    genresColumn, ageRatingColumn, lengthColumn, movieLinkColumn);*/

            // Fetch data from the database and populate the TableView
            ObservableList<Movie> movieList = fetchMovieDataFromDatabase();
            MoviesTable.setItems(movieList);
        }
        
        @FXML
        void ExitBtn_Clicked(MouseEvent event) {
        	LoginController.MainMenu.hide();
        	LoginController.Login.show();
        }
        
        @FXML
        void CategoriesBtn_Clicked(ActionEvent event) {

        }

        @FXML
        void CategoriesBtn_HoverIn(MouseEvent event) {

        }

        @FXML
        void CategoriesBtn_HoverOut(MouseEvent event) {

        }

        @FXML
        void ExitBtn_HoverIn(MouseEvent event) {

        }

        @FXML
        void ExitBtn_HoverOut(MouseEvent event) {

        }

        @FXML
        void HomeBtn_Clicked(ActionEvent event) {

        }

        @FXML
        void HomeBtn_HoverIn(MouseEvent event) {

        }

        @FXML
        void HomeBtn_HoverOut(MouseEvent event) {

        }

        @FXML
        void MoviesBtn_Clicked(ActionEvent event) {

        }

        @FXML
        void MoviesBtn_HoverIn(MouseEvent event) {

        }

        @FXML
        void MoviesBtn_HoverOut(MouseEvent event) {

        }

        @FXML
        void SearchBox_KeyPressed(KeyEvent event) {

        }

        @FXML
        void SearchBtn_Clicked(MouseEvent event) {

        }

        @FXML
        void SearchBtn_HoverIn(MouseEvent event) {

        }

        @FXML
        void SearchBtn_HoverOut(MouseEvent event) {

        }

        @FXML
        void TVshowsBtn_Clicked(ActionEvent event) {

        }

        @FXML
        void TVshowsBtn_HoverIn(MouseEvent event) {

        }

        @FXML
        void TVshowsBtn_HoverOut(MouseEvent event) {

        }

        @FXML
        void Top10Btn_Clicked(ActionEvent event) {

        }

        @FXML
        void Top10Btn_HoverIn(MouseEvent event) {

        }

        @FXML
        void Top10Btn_HoverOut(MouseEvent event) {

        }

        @FXML
        void UserIcon_Clicked(MouseEvent event) {

        }

        @FXML
        void WatchlistBtn_Clicked(ActionEvent event) {

        }

        @FXML
        void WatchlistBtn_Hover(MouseEvent event) {

        }
        
        public static class Movie {
                private final SimpleStringProperty movieName;
                private final SimpleStringProperty showType;
                private final DoubleProperty imdbRating;
                private final SimpleStringProperty genres;
                private final SimpleStringProperty ageRating;
                private final SimpleStringProperty length;
                private final SimpleStringProperty movieLink;

                public Movie(String movieName, String showType, double imdbRating, String genres,
                             String ageRating, String length, String movieLink) {
                    this.movieName = new SimpleStringProperty(movieName);
                    this.showType = new SimpleStringProperty(showType);
                    this.imdbRating = new SimpleDoubleProperty(imdbRating);
                    this.genres = new SimpleStringProperty(genres);
                    this.ageRating = new SimpleStringProperty(ageRating);
                    this.length = new SimpleStringProperty(length);
                    this.movieLink = new SimpleStringProperty(movieLink);
                }

                public String getMovieName() {
                    return movieName.get();
                }

                public SimpleStringProperty movieNameProperty() {
                    return movieName;
                }

                public String getShowType() {
                    return showType.get();
                }

                public SimpleStringProperty showTypeProperty() {
                    return showType;
                }

                public double getImdbRating() {
                    return imdbRating.get();
                }

                public DoubleProperty imdbRatingProperty() {
                    return imdbRating;
                }

                public String getGenres() {
                    return genres.get();
                }

                public SimpleStringProperty genresProperty() {
                    return genres;
                }

                public String getAgeRating() {
                    return ageRating.get();
                }

                public SimpleStringProperty ageRatingProperty() {
                    return ageRating;
                }

                public String getLength() {
                    return length.get();
                }

                public SimpleStringProperty lengthProperty() {
                    return length;
                }

                public String getMovieLink() {
                    return movieLink.get();
                }

                public SimpleStringProperty movieLinkProperty() {
                    return movieLink;
                }
            }
    }
