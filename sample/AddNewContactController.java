package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.db.DBConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddNewContactController {
    public static Statement statmt;
    public static Connection conn;

    @FXML
    private Button buttonAdd;
    @FXML
    private TextField userName;

    @FXML
    private TextField phoneNumber;

    @FXML
    public void addToDb() throws SQLException, ClassNotFoundException {

        DBConn dbConn = new DBConn();
        dbConn.Conn();

        conn = DBConn.conn;
        statmt = conn.createStatement();
        String userNameStr = userName.getText();
        String phoneNumberStr = phoneNumber.getText();


        statmt.execute("INSERT INTO 'Contacts' ('name', 'number') VALUES ('" + userNameStr + "','" + phoneNumberStr + "'); ");


        System.out.println("Таблица заполнена");
    }


}
