package application;
import com.MySql.Util.DatabaseUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;
import java.sql.*;

public class LoginController {
	static Stage Login = new Stage();
	static Stage MainMenu = new Stage();
	static Stage AdminPanel = new Stage();
	static Stage UserPanel = new Stage();

	public LoginController() throws SQLException {
		con = DatabaseUtil.getConnection();
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginBtn;

    @FXML
    private CheckBox adminchkbox;
    
    @FXML
    private Hyperlink NewAccountBtn;

    @FXML
    private PasswordField PassBox;

    @FXML
    private TextField UserBox;

    Connection con=null;
    PreparedStatement cmd=null;
    String sql;
    public static String User,AccountType;
    
    @FXML
    void adminchkbox_Clicked(ActionEvent event) {

    }
    
    @FXML
    void LoginBtn_Click(MouseEvent event) {
    	if(!adminchkbox.isSelected()) {sql="select * from login where UserName=? and Password=? and AccountType='User'";    	 
    	AccountType="User";}
    	else {sql="select * from login where UserName=? and Password=? and AccountType='Admin'";
    	AccountType="Admin";}
    	try {
    		cmd = con.prepareStatement(sql);
    		cmd.setString(1, UserBox.getText());
    		cmd.setString(2, PassBox.getText());
    	    ResultSet Output=cmd.executeQuery();
    	    if(!Output.next()) {
    	    	Alert alert = new Alert(AlertType.ERROR);
    	    	alert.setContentText("The Entered Data is invaild, Please Try Again!!");
    	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
    	    	alert.getButtonTypes().setAll(btn);
    	    	alert.showAndWait();
    	    }
    	    else {
    	    	User=Output.getNString("UserName");
        		MainMenu.show();
    	    	Login.hide();
    	    	UserBox.setText(""); PassBox.setText("");
    	    }
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    }

    @FXML
    void NewAccountBtn_Click(ActionEvent event) {
		sql="insert into login (UserName,Password) values (?,?)";
    	try {
    		if(UserBox.getText().length()<4 || PassBox.getText().length()<5) {
    			Alert alert = new Alert(AlertType.ERROR);
    	    	alert.setContentText("Your Password Or User Name Is Shorter Than Has To Be, Please Check Them And Try Again!");
    	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
    	    	alert.getButtonTypes().setAll(btn);
    	    	alert.showAndWait();
    		}
    		else {
    		cmd = con.prepareStatement(sql);
    		cmd.setString(1, UserBox.getText());
    		cmd.setString(2, PassBox.getText());
    	    cmd.execute();
    	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setContentText("The New Account Has Been Creeated Successfully!");
	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
	    	alert.getButtonTypes().setAll(btn);
	    	alert.showAndWait();
	    	}
    		UserBox.setText(""); PassBox.setText("");
    	}
    	catch(Exception ex) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setContentText(ex.getMessage());
	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
	    	alert.getButtonTypes().setAll(btn);
	    	alert.showAndWait();
    	}
    }

    @FXML
    void initialize() {
    	try {
			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("MainForm.fxml"));
			Scene scene = new Scene(pane1);
			MainMenu.setScene(scene);
			//////////////////////////////////////////////
			 pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
			 scene = new Scene(pane1);
			AdminPanel.setScene(scene);
			//////////////////////////////////////////////
			pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
			scene = new Scene(pane1);
			UserPanel.setScene(scene);
    	}
    	catch(Exception ex){
    		
    	}
    }

}
