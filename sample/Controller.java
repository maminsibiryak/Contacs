package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.db.DBConn;
import sample.db.Person;

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Controller {
    public static ResultSet resSet;
    public static Statement statmt;
    public static Connection conn;


    private ObservableList<Person> usersData = FXCollections.observableArrayList();

    @FXML
    private javafx.scene.control.Button refreshButton;

    @FXML
    private TableView<Person> tableUsers;

    @FXML
    private TableColumn<Person, Integer> idColumn;
    @FXML
    private TableColumn<Person, String> userNameColumn;
    @FXML
    private TableColumn<Person, String> phoneNumberColumn;

    @FXML
    private MenuItem addNewContactId;
    @FXML
    private javafx.scene.control.TextField deleteId;

    @FXML
    public void openNewWindowOfAddingNewContact() {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddNewContactWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 176, 179));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public static ArrayList<Person> ReadDB() throws ClassNotFoundException, SQLException {
        ArrayList<Person> people = new ArrayList<>();
        DBConn dbConn = new DBConn();
        dbConn.Conn();
        conn = DBConn.conn;
        statmt = conn.createStatement();

        resSet = statmt.executeQuery("SELECT * FROM Contacts");

        while (resSet.next()) {
            int id = resSet.getInt("id");
            String name = resSet.getString("name");
            String phone = resSet.getString("number");
            people.add(new Person(id, name, phone));
        }

        System.out.println("Таблица выведена");
        return people;
    }

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        tableUsers.getItems().clear();
        for (int i = 0; i < ReadDB().size(); i++) {
            usersData.add(new Person(ReadDB().get(i).getId(), ReadDB().get(i).getUserName(), ReadDB().get(i).getPhoneNumber()));
        }
        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        // заполняем таблицу данными
        tableUsers.setItems(usersData);
    }

    @FXML
    public void refresh() throws SQLException, ClassNotFoundException {

        initialize();
    }

    @FXML
    public void  delete() throws SQLException, ClassNotFoundException {
        DBConn dbConn = new DBConn();
        dbConn.Conn();
        conn = DBConn.conn;
        statmt = conn.createStatement();
        String tempId = deleteId.getText();
        System.out.println(tempId);
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM Contacts WHERE id = ('" + tempId + "');");
        preparedStatement.executeUpdate();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Contact deleted");
        alert.showAndWait();
        System.out.println(System.getProperty("user.dir"));

        refresh();
    }
}
