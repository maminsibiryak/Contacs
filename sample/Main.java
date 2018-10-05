package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.db.DBConn;
import sample.db.Person;

import java.sql.SQLException;

public class Main extends Application {
    @FXML
    private TableView userTable;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Contacts");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        DBConn dbConn = new DBConn();
        dbConn.Conn();



    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);

    }
}
