/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Guido
 */
public class DataSource {

    private static String dbserver;
    private static int dbport;
    private static String database;
    private static String username;
    private static String password;
    private static Connection activeConn;

    public static void init() {
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        dbserver = "95.85.10.155";
        dbport = 3306;
        database = "fullhouse";
        username = "root";
        password = "test123";
    }

    public static Connection getConnection() throws SQLException {
        if (activeConn == null) {
            init();
            activeConn = createConnection();
        }

        return activeConn;
    }

    public static Connection createConnection() throws SQLException {
        String connectionString = "jdbc:mysql://" + dbserver + ":" + dbport + "/" + database;

        return DriverManager.getConnection(connectionString, username, password);
    }
}
