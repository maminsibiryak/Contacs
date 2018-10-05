package sample.db;

import java.sql.*;

public class DBConn {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");

        conn = DriverManager.getConnection("jdbc:sqlite:UserContacts.s3db");

        System.out.println("База Подключена!");
    }

    public static void CreateDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'Contacts' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'number' NUMERIC );");

        System.out.println("Таблица создана или уже существует.");
    }


}
