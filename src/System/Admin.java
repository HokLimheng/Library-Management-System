package System;

import java.sql.Connection;

public class Admin {

    private String USERNAME;
    private String PASSWORD;

    public Admin (String USERNAME, String PASSWORD){
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public String getUSERNAME(){
        return USERNAME;
    }

    public String getPASSWORD(){
        return PASSWORD;
    }

    @Override
    public String toString() {
        return "Book{" +
                ", Username='" + USERNAME + '\'' +
                ", Password=" + PASSWORD +
                '}';
    }
}
