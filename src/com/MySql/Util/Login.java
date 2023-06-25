package com.MySql.Util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Login {
	private final SimpleStringProperty UserName;
    private final SimpleStringProperty AccountType;
    private final SimpleIntegerProperty UserID;

    public Login(int UserID,String UserName,String AccountType) {
    	this.UserName = new SimpleStringProperty(UserName);
        this.AccountType = new SimpleStringProperty(AccountType);
        this.UserID = new SimpleIntegerProperty(UserID);
    }
    
    public int getUserID() {
        return UserID.get();
    }

    public SimpleIntegerProperty UserIDProperty() {
        return UserID;
    }
    
    public String getUserName() {
        return UserName.get();
    }

    public SimpleStringProperty UserNameProperty() {
        return UserName;
    }
    
    public String getAccountType() {
        return AccountType.get();
    }

    public SimpleStringProperty AccountTypeProperty() {
        return AccountType;
    }
}
