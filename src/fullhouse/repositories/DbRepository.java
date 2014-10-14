/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * @param <T>
 */
public abstract class DbRepository<T> {

    public abstract T getModel();

    public String getInsertString() {
        StringBuilder columnsString = new StringBuilder();
        StringBuilder valuesString = new StringBuilder();

        ArrayList<String> columns = getColumnNames();

        for (int i = 0; i < columns.size(); i++) {
            columnsString.append(columns.get(i));
            valuesString.append("?");
            if (i != columns.size() - 1) {
                columnsString.append(", ");
                valuesString.append(", ");
            }
        }

        String insertString = "INSERT INTO " + getTable() + " (" + columnsString.toString() + ") VALUES (" + valuesString + ")";

        return insertString;
    }

    public String getUpdateString() {
        String queryString = "";

        ArrayList<String> columns = getColumnNames();

        for (int i = 0; i < columns.size(); i++) {
            queryString += columns.get(i) + " = ?";
            if (i != columns.size() - 1) {
                queryString += ", ";
            }
        }

        String updateString = "UPDATE " + getTable() + " SET " + queryString + " WHERE id = ?";

        return updateString;
    }

    public String getTable() {
        return getModel().getClass().getSimpleName().toLowerCase();
    }

    public ArrayList<String> getColumnNames() {
        ArrayList<String> columns = new ArrayList<>();

        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM " + getTable() + " LIMIT 0,1");
            ResultSet rs = stat.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for (int i = 2; i < columnCount + 1; i++) {
                String columnName = rsmd.getColumnName(i);
                columns.add(columnName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return columns;
    }

    public HashMap<Integer, String> getColumns() {
        HashMap<Integer, String> columnsMap = new HashMap<>();
        for (int i = 0; i < getColumnNames().size(); i++) {
            columnsMap.put(i + 1, getColumnNames().get(i));
        }
        return columnsMap;
    }
}
