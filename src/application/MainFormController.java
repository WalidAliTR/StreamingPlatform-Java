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
import com.MySql.Util.Movie;
import javafx.scene.control.*;
import java.sql.*;
import javafx.util.Callback;

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

        private ObservableList<Movie> fetchMovieDataFromDatabase(String query) {
            ObservableList<Movie> movieList = FXCollections.observableArrayList();
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
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

            // Fetch data from the database and populate the TableView
            ObservableList<Movie> movieList = fetchMovieDataFromDatabase(query);
            MoviesTable.setItems(movieList);
        }
        
        @FXML
        void initialize() {
        	ShowMovieList("select * from movies");      
        }
        
        @FXML
        void ExitBtn_Clicked(MouseEvent event) {
        	LoginController.MainMenu.hide();
        	LoginController.Login.show();
        }
        
        @FXML
        void HomeBtn_Clicked(ActionEvent event) {
        	ShowMovieList("select * from movies");      
        }

        @FXML
        void MoviesBtn_Clicked(ActionEvent event) {
        	ShowMovieList("select * from movies where ShowType= 'Movie'");      
        }

        @FXML
        void SearchBox_KeyPressed(KeyEvent event) {
        	if(event.isConsumed())
        	ShowMovieList("select * from movies where MovieName like '%"+SearchBox.getText()+"%'");      
        }

        @FXML
        void TVshowsBtn_Clicked(ActionEvent event) {
        	ShowMovieList("select * from movies where ShowType= 'TV-Show'");      
        }

        @FXML
        void Top10Btn_Clicked(ActionEvent event) {
        	ShowMovieList("select * from movies order by IMDbRating desc limit 5");      
        }

        @FXML
        void UserIcon_Clicked(MouseEvent event) {

        }

        @FXML
        void WatchlistBtn_Clicked(ActionEvent event) {

        }
    }
