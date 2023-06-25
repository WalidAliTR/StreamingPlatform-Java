package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.MySql.Util.DatabaseUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;

public class UserPanelController {
	public UserPanelController() throws SQLException {
		con = DatabaseUtil.getConnection();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleButton SaveChangesBtn;

    @FXML
    private ToggleButton DeleteUserBtn;

    @FXML
    private TextField UserPassword;

    @FXML
    private TextField Name;

    @FXML
    private PasswordField NewPassword;

    @FXML
    private AnchorPane formpanel;
    
    Connection con=null;
    PreparedStatement cmd=null;
    String sql;
    
    @FXML
    void SaveChangesBtn_Clicked(ActionEvent event) {
    	if(Name.getText().length()<4) {
    		
    		if(NewPassword.getText().length()<5) {
    			Alert alert = new Alert(AlertType.ERROR);
    	    	alert.setContentText("Your New Password is Not The Same or less than five character! Please Check them and Try Again");
    	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
    	    	alert.getButtonTypes().setAll(btn);
    	    	alert.showAndWait();
    	    	NewPassword.setText(""); 
    		}
    		else {
    			sql="select * from login where UserName='"+LoginController.User+"' and Password='"+UserPassword.getText()+"' ";
    	    	try {
    	    		cmd = con.prepareStatement(sql);
    	    	    ResultSet Output=cmd.executeQuery();
    	    	    if(!Output.next()) {
    	    	    	Alert alert = new Alert(AlertType.ERROR);
    	    	    	alert.setContentText("The Entered Password is invaild, Please Try Again!!");
    	    	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
    	    	    	alert.getButtonTypes().setAll(btn);
    	    	    	alert.showAndWait();
    	    	    	UserPassword.setText("");
    	    	    }
    	    	    else {
    	    	    	sql="update login set Password='"+NewPassword.getText()+"' where UserName='"+LoginController.User+"'";
    	        		cmd = con.prepareStatement(sql);
    	        	    cmd.execute();
    	        	    Alert alert = new Alert(AlertType.CONFIRMATION);
    	    	    	alert.setContentText("The Password Has Changed Successfully!");
    	    	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
    	    	    	alert.getButtonTypes().setAll(btn);
    	    	    	alert.showAndWait();
    	    	    	
    	    	    }
    	    	}
    	    	catch(Exception ex) {
    	    		Alert alert = new Alert(AlertType.ERROR);
        	    	alert.setContentText(ex.getMessage());
        	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
        	    	alert.getButtonTypes().setAll(btn);
        	    	alert.showAndWait();    	    	}
    		}
    	}
    	/////////////////////////////////////////////////
    	else {
    		sql="select * from login where UserName='"+LoginController.User+"' and Password='"+UserPassword.getText()+"' ";
	    	try {
	    		cmd = con.prepareStatement(sql);
	    	    ResultSet Output=cmd.executeQuery();
	    	    if(!Output.next()) {
	    	    	Alert alert = new Alert(AlertType.ERROR);
	    	    	alert.setContentText("The Entered Password is invaild, Please Try Again!!");
	    	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
	    	    	alert.getButtonTypes().setAll(btn);
	    	    	alert.showAndWait();
	    	    	UserPassword.setText("");
	    	    }
	    	    else {
	    	    	if( NewPassword.getText().length()>4){
	    	    		sql="update login set Password='"+NewPassword.getText()+"',UserName='"+Name.getText()+"' where UserName='"+LoginController.User+"'";
		        		cmd = con.prepareStatement(sql);
		        	    cmd.execute();
		        	    Alert alert = new Alert(AlertType.CONFIRMATION);
		    	    	alert.setContentText("The Account Data Has Been Changed Successfully!");
		    	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
		    	    	alert.getButtonTypes().setAll(btn);
		    	    	alert.showAndWait();
		    	    	LoginController.User=Name.getText();
	    	    	}
	    	    	else{
	    	    		sql="update login set UserName='"+Name.getText()+"' where UserName='"+LoginController.User+"'";
		        		cmd = con.prepareStatement(sql);
		        	    cmd.execute();
		        	    Alert alert = new Alert(AlertType.CONFIRMATION);
		    	    	alert.setContentText("The User Name Changed Successfully!");
		    	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
		    	    	alert.getButtonTypes().setAll(btn);
		    	    	alert.showAndWait();
		    	    	LoginController.User=Name.getText();
	    	    	}
	    	    }
    		
    		}
    	    	catch(Exception ex) {
    	    		Alert alert = new Alert(AlertType.ERROR);
        	    	alert.setContentText(ex.getMessage());
        	    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
        	    	alert.getButtonTypes().setAll(btn);
        	    	alert.showAndWait();
    	    	}
    		}
    	}

    @FXML
    void DeleteUserBtn_Clicked(ActionEvent event) {
    	try{sql="delete from login where UserName='"+LoginController.User+"'";
		cmd = con.prepareStatement(sql);
	    cmd.execute();
	    Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Your Account Has Been Deleted Successfully!");
    	ButtonType btn = new ButtonType("Ok",ButtonData.CANCEL_CLOSE);
    	alert.getButtonTypes().setAll(btn);
    	alert.showAndWait();
    	LoginController.UserPanel.hide();
    	LoginController.AdminPanel.hide();
    	LoginController.MainMenu.hide();
    	LoginController.Login.show();
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
        
    }

}
