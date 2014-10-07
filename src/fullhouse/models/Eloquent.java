/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.models;

import fullhouse.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guido
 */
abstract class Eloquent<T extends Eloquent<T>> {

    private String table;

    abstract T getModel();

    public Eloquent() {
        this.table = getModel().getClass().getSimpleName().toLowerCase();
    }

    public ArrayList getColumns() throws SQLException {
        Connection conn = DataSource.getConnection();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM " + this.table);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        ArrayList<String> columns = new ArrayList<>();
        for (int i = 2; i < columnCount + 1; i++) {
            String columnName = rsmd.getColumnName(i);
            columns.add(columnName);
        }

        return columns;
    }

    public void save() {
        try {
            Connection conn = DataSource.getConnection();
            //PreparedStatement stat = conn.prepareStatement("INSERT INTO");
            
            Method[] methods = getModel().getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().indexOf("get") == 0) {
                    String regex = "([a-z])([A-Z]+)";
                    String replacement = "$1_$2";
                    String tableName = m.getName().replaceAll(regex, replacement).toLowerCase().replace("get_", "");
                    if (getColumns().contains(tableName)) {
                        System.out.println(tableName + "=" + m.invoke(getModel()));
                        if(m.getReturnType().equals(Integer.TYPE)) {
                            
                        }
                    }
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException e) {
            e.printStackTrace();
        }
    }
}
